package com.issart.alice.wiffy.rest;

import javax.sql.DataSource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.issart.alice.di.PersistenceModule;
import com.issart.alice.di.ServiceModule;
import com.issart.alice.wiffy.rest.dto.request.AliceRequest;
import com.issart.alice.wiffy.rest.dto.response.AliceResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.sql2o.Sql2o;

public class AliceRestVertX extends AbstractVerticle {

    private WiffyApplication wiffyApplication;
    private final static Logger LOGGER = Logger.getLogger(AliceRestVertX.class);

    public static void main(String[] args) {
        Runner.runExample(AliceRestVertX.class);
    }

    @Override
    public void start() {
        Injector injector = Guice.createInjector(new ServiceModule(), new PersistenceModule());
        wiffyApplication = injector.getInstance(WiffyApplication.class);

        initDatabase(injector.getInstance(Sql2o.class).getDataSource());

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post("/rest/wiffy").handler(this::handleWiffyRequest);

        vertx.createHttpServer().requestHandler(router::accept).listen(8095);
    }

    public void handleWiffyRequest(RoutingContext routingContext) {
        String body = routingContext.getBodyAsString();
        LOGGER.debug(body);

        AliceRequest request = Json.decodeValue(body, AliceRequest.class);
        HttpServerResponse serverResponse = routingContext.response();
        AliceResponse response = wiffyApplication.handleAliceRequest(request);
        serverResponse.setChunked(true);
        serverResponse.write(Json.encode(response));
        serverResponse.end();
    }

    private void initDatabase(DataSource ds) {
        Flyway flyway = Flyway.configure()
            .dataSource(ds)
            .load();
        flyway.migrate();
    }
}

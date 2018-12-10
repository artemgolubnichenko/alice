package com.issart.alice.rest;

import java.util.ResourceBundle;
import javax.sql.DataSource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.issart.alice.di.PersistenceModule;
import com.issart.alice.di.ServiceModule;
import com.issart.alice.exchange.ExchangeApplication;
import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.util.Runner;
import com.issart.alice.wiffy.WiffyApplication;
import com.issart.alice.rest.dto.response.AliceResponse;
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
    private ExchangeApplication exchangeApplication;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config");;
    private final static Logger LOGGER = Logger.getLogger(AliceRestVertX.class);

    public static void main(String[] args) {
        Runner.runExample(AliceRestVertX.class);
    }

    @Override
    public void start() {
        Injector injector = Guice.createInjector(new ServiceModule(), new PersistenceModule());
        wiffyApplication = injector.getInstance(WiffyApplication.class);
        exchangeApplication = injector.getInstance(ExchangeApplication.class);

        initDatabase(injector.getInstance(Sql2o.class).getDataSource());

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post("/rest/wiffy").handler(this::handleWiffyRequest);
        router.post("/rest/exchange").handler(this::handleExchangeRequest);

        vertx.createHttpServer().requestHandler(router::accept).listen(
            Integer.valueOf(RESOURCE_BUNDLE.getString("server.port"))
        );
    }

    public void handleWiffyRequest(RoutingContext routingContext) {
        String body = routingContext.getBodyAsString();
        LOGGER.info(body);

        AliceRequest request = Json.decodeValue(body, AliceRequest.class);
        HttpServerResponse serverResponse = routingContext.response();
        AliceResponse response = wiffyApplication.handleAliceRequest(request);
        serverResponse.setChunked(true);
        serverResponse.write(Json.encode(response));
        serverResponse.end();
    }

    public void handleExchangeRequest(RoutingContext routingContext) {
        String body = routingContext.getBodyAsString();
        LOGGER.info(body);

        AliceRequest request = Json.decodeValue(body, AliceRequest.class);
        HttpServerResponse serverResponse = routingContext.response();
        AliceResponse response = exchangeApplication.handleAliceRequest(request);
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

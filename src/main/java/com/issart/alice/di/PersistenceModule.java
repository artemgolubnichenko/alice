package com.issart.alice.di;

import java.util.ResourceBundle;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.issart.alice.exchange.service.repository.IRepository;
import com.issart.alice.exchange.service.repository.Repository;
import com.issart.alice.wiffy.db.IWiffyDao;
import com.issart.alice.wiffy.db.impl.WiffyDaoImpl;
import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        Sql2o sql2o = new Sql2o(rb.getString("db.url"), rb.getString("db.user"),
            rb.getString("db.password"), new PostgresQuirks());
        bind(Sql2o.class).toInstance(sql2o);
        bind(IWiffyDao.class).to(WiffyDaoImpl.class).in(Singleton.class);
        bind(IRepository.class).to(Repository.class).in(Singleton.class);
    }
}

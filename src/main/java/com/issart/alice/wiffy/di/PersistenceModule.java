package com.issart.alice.wiffy.di;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.issart.alice.wiffy.db.IWiffyDao;
import com.issart.alice.wiffy.db.impl.WiffyDaoImpl;
import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/alice",
            "postgres", "postgres", new PostgresQuirks());
        bind(Sql2o.class).toInstance(sql2o);
        bind(IWiffyDao.class).to(WiffyDaoImpl.class).in(Singleton.class);
    }
}

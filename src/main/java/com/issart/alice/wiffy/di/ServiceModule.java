package com.issart.alice.wiffy.di;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.issart.alice.wiffy.service.IWiffyService;
import com.issart.alice.wiffy.service.impl.WiffyServiceImpl;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IWiffyService.class).to(WiffyServiceImpl.class).in(Singleton.class);
    }
}

package com.issart.alice.di;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.issart.alice.exchange.service.IExchangeCommandHandleService;
import com.issart.alice.exchange.service.impl.ExchangeCommandHandleServiceImpl;
import com.issart.alice.wiffy.service.IWiffyService;
import com.issart.alice.wiffy.service.impl.WiffyServiceImpl;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IWiffyService.class).to(WiffyServiceImpl.class).in(Singleton.class);
        bind(IExchangeCommandHandleService.class).to(ExchangeCommandHandleServiceImpl.class).in(Singleton.class);
    }
}

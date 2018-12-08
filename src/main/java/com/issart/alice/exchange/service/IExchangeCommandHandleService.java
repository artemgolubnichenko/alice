package com.issart.alice.exchange.service;

import com.issart.alice.exchange.command.ExchangeCommand;
import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;

public interface IExchangeCommandHandleService {

    ExchangeInfo handle(ExchangeCommand command, Exchange type) throws ExchangeSkillException;
}

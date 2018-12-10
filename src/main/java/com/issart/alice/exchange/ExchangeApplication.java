package com.issart.alice.exchange;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import com.github.moneytostr.MoneyToStr;
import com.google.inject.Inject;
import com.issart.alice.common.Application;
import com.issart.alice.exchange.command.ExchangeCommand;
import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.rest.dto.response.AliceReplicas;
import com.issart.alice.exchange.service.IExchangeCommandHandleService;
import com.issart.alice.exchange.service.repository.IRepository;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;
import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.rest.dto.response.AliceResponse;
import com.issart.alice.rest.dto.response.Response;
import org.apache.log4j.Logger;

import static com.issart.alice.exchange.rest.dto.response.AliceReplicas.EXCHANGE_ANSWER_MSG;
import static java.util.concurrent.TimeUnit.MINUTES;

public class ExchangeApplication extends Application {

    @Inject
    private IExchangeCommandHandleService service;
    @Inject
    private IRepository repository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final static Logger LOGGER = Logger.getLogger(ExchangeApplication.class);


    @Inject
    public ExchangeApplication(IRepository repository) {
        int i = 0;
        this.repository = repository;
        scheduler.scheduleAtFixedRate(() -> {
            repository.pull();
            LOGGER.info("Pulling..");
        }, 0, 1, MINUTES);
    }

    @Override
    protected AliceResponse processRequest(AliceRequest request) throws ExchangeSkillException {
        AliceResponse response = new AliceResponse();
        if (newSession && (rawCommand.isEmpty()
            || request.getRequest().getNlu().getTokens().isEmpty())) {
            response.setResponse(new Response(AliceReplicas.INITIAL_MSG));
        } else {
            ExchangeCommand command = ExchangeCommand.parseCommand(rawCommand);
            Exchange type = ExchangeCommand.getExchangeType(command, rawCommand);
            ExchangeInfo info = service.handle(command, type);
            String change = info.getDiff() > 0 ? "вырос" : "упал";
            String diff = "";
            if(command == ExchangeCommand.GET_CURRENCY) {
                MoneyToStr moneyToStr = new MoneyToStr(MoneyToStr.Currency.RUR,
                    MoneyToStr.Language.RUS, MoneyToStr.Pennies.NUMBER);
                diff = moneyToStr.convert((double)Math.abs(info.getDiff()));
                if(info.getDiff() < 0.0f) {
                    diff = diff.replaceAll("ноль рублей", "");
                }
            } else if(command == ExchangeCommand.GET_INDEX) {
                String percent = new BigDecimal(Math.abs(info.getPercent()))
                    .setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                diff = percent + " %";
            }
            String text = String.format(EXCHANGE_ANSWER_MSG, command.getName(),
                info.getCurrent(), info.getCurrency(), change, diff);
            response.setResponse(new Response(text));
            response.getResponse().setEndSession(true);
        }
        return response;
    }
}

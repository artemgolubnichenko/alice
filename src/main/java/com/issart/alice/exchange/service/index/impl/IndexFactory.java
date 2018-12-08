package com.issart.alice.exchange.service.index.impl;

import com.issart.alice.exchange.type.Index;

public class IndexFactory {

    public static BaseIndex getIndex(Index index) {
        switch (index) {
            case LSE: {
                return new LSEIndex();
            }
            case NYSE: {
                return new NYSEIndex();
            }
            case RTSI: {
                return new RTSIndex();
            }
            case IMOEX: {
                return new IMOEXIndex();
            }
            case NASDAQ: {
                return new NASDAQIndex();
            }
        }
        throw new RuntimeException("Не поддерживаемый индекс");
    }
}

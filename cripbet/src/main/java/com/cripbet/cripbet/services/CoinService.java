package com.cripbet.cripbet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cripbet.cripbet.CoinGeckoClient;

import java.util.List;
import java.util.Map;


@Service
public class CoinService {

   private final CoinGeckoClient coinGeckoClient;

    @Autowired
    public CoinService(CoinGeckoClient coinGeckoClient) {
        this.coinGeckoClient = coinGeckoClient;
    }

    public List<Map<String, Object>> getTopCoinsInUSD(int limit) {
        return coinGeckoClient.getTopCoins("usd", "market_cap_desc", limit, 1);
    }

    public Map<String, Object> getCoinPrice(String coin, String currency) {
        return coinGeckoClient.getCoinPrice(coin, currency);
    }

    public List<Map<String, Object>> getCoins(String currency, String order, int perPage, int page) {
        return coinGeckoClient.getTopCoins(currency, order, perPage, page);
    }
}

package com.cripbet.cripbet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "coinGeckoClient", url = "https://api.coingecko.com/api/v3")
public interface CoinGeckoClient {

    @GetMapping("/coins/markets")
    List<Map<String, Object>> getTopCoins(
        @RequestParam("vs_currency") String vsCurrency,
        @RequestParam("order") String order,
        @RequestParam("per_page") int perPage,
        @RequestParam("page") int page);


    @GetMapping("/simple/price")
        Map<String, Object> getCoinPrice(
        @RequestParam("ids") String ids,
        @RequestParam("vs_currencies") String vsCurrencies);
    
}

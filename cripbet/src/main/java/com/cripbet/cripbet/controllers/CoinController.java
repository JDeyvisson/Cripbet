package com.cripbet.cripbet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cripbet.cripbet.services.CoinService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/coins")
@Tag(name = "Coin Controller", description = "Endpoints para buscar informações de criptomoedas")
public class CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService cryptoService) {
        this.coinService = cryptoService;
    }

    @GetMapping("/top-coins")
    @Operation(summary = "Obter top 3 criptomoedas em dolar", description = "Retorna as 3 criptomoedas mais valiosas em dolar americano.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna as 3 criptomoedas mais valiosas com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public List<Map<String, Object>> getTop3CoinsInUSD() {
        return coinService.getTopCoinsInUSD(3);
    }



    @GetMapping("/price/{coin}/{currency}")
    @Operation(summary = "Obter preço de uma criptomoeda", description = "Retorna o preço de uma criptomoeda em uma moeda específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o preço da criptomoeda com sucesso."),
        @ApiResponse(responseCode = "400", description = "ID ou moeda inválidos."),
        @ApiResponse(responseCode = "404", description = "Criptomoeda não encontrada."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public Map<String, Object> getCoinPrice(
                @Parameter(description = "ID da criptomoeda (ex: bitcoin)") @PathVariable String coin,
                @Parameter(description = "Moeda para conversão (ex: usd)") @PathVariable String currency) {
            return coinService.getCoinPrice(coin, currency);
        }


    
        
    @GetMapping
    @Operation(summary = "Listar criptomoedas com filtros e ordenação", description = "Permite filtrar por moeda, ordenar por métricas específicas e aplicar paginação.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso."),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public List<Map<String, Object>> getCoins(
            @Parameter(description = "Moeda para conversão (ex: usd, eur)") @RequestParam(value = "currency", required = false, defaultValue = "usd") String currency,
            @Parameter(description = "Ordenação (ex: market_cap_desc, market_cap_asc)") @RequestParam(value = "order", required = false, defaultValue = "market_cap_desc") String order,
            @Parameter(description = "Quantidade por página") @RequestParam(value = "per_page", required = false, defaultValue = "10") int perPage,
            @Parameter(description = "Número da página") @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        // Validação simples para os parâmetros
        if (!order.matches("market_cap_desc|market_cap_asc|volume_desc|volume_asc")) {
            throw new IllegalArgumentException("Order inválido. Use: market_cap_desc, market_cap_asc, volume_desc ou volume_asc.");
        }

        return coinService.getCoins(currency, order, perPage, page);
    }



}

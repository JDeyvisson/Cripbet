package com.cripbet.cripbet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api")
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

    @GetMapping("/price/{crypto}/{currency}")
    @Operation(summary = "Obter preço de uma criptomoeda", description = "Retorna o preço de uma criptomoeda em uma moeda específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o preço da criptomoeda com sucesso."),
        @ApiResponse(responseCode = "400", description = "ID ou moeda inválidos."),
        @ApiResponse(responseCode = "404", description = "Criptomoeda não encontrada."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    public Map<String, Object> getCryptoPrice(
                @Parameter(description = "ID da criptomoeda (ex: bitcoin)") @PathVariable String crypto,
                @Parameter(description = "Moeda para conversão (ex: usd)") @PathVariable String currency) {
            return coinService.getCryptoPrice(crypto, currency);
        }
}

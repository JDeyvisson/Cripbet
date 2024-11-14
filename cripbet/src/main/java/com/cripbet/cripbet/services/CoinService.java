package com.cripbet.cripbet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cripbet.cripbet.models.Coin;
import com.cripbet.cripbet.repositories.CoinRepository;

@Service
public class CoinService {
    
    @Autowired
    private CoinRepository coinRepository;

    public Coin findById(Long id){

        return coinRepository.findById(id).orElseThrow(() -> new RuntimeException("moeda nao encontrada"));

    }

    public void createCoin(Coin coin){

        coin = this.coinRepository.save(coin);

    }

    public void delete(Long id){

        findById(id);
        this.coinRepository.deleteById(id);

    }


}

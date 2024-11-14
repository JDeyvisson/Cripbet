package com.cripbet.cripbet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cripbet.cripbet.models.Coin;
import com.cripbet.cripbet.services.CoinService;

@RestController
@RequestMapping("/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @GetMapping("/{id}")
    public ResponseEntity<Coin> findById(@PathVariable Long id){
        
        Coin coin = this.coinService.findById(id);
        return ResponseEntity.ok().body(coin);

    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Coin coin){

        this.coinService.createCoin(coin);
         return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Coin> delete(@PathVariable Long id){

        this.coinService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}

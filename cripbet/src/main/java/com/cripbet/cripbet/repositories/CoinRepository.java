package com.cripbet.cripbet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cripbet.cripbet.models.Coin;

public interface CoinRepository extends JpaRepository<Coin, Long>{
    


}

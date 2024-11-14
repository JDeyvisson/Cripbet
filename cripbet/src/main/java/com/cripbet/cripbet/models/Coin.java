package com.cripbet.cripbet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coin")
public class Coin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float priceUS;
    

    public Coin(){

    }

    public Coin(Long id, String name, Float priceUS) {
        this.id = id;
        this.name = name;
        this.priceUS = priceUS;
    }

    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getPriceUS() {
        return priceUS;
    }
    public void setPriceUS(Float priceUS) {
        this.priceUS = priceUS;
    }


    
}

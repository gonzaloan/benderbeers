package tech.nullpointerexception.bender.dto;

import tech.nullpointerexception.bender.util.UtilConstants;

import javax.validation.constraints.NotNull;


public class BeerDto {

    private Integer id;
    private String name;
    private String brewery;
    private String country;
    private Double price;
    private String currency;

    public BeerDto(Integer id, String name, String brewery, String country, Double price, String currency) {
        this.id = id;
        this.name = name;
        this.brewery = brewery;
        this.country = country;
        this.price = price;
        this.currency = currency;
    }

    public BeerDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}

package tech.nullpointerexception.bender.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeerPriceDto {

    @JsonProperty("Total Price")
    private final Double totalPrice;

    private BeerPriceDto(BeerPriceDtoBuilder builder){
        this.totalPrice=builder.totalPrice;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public static class BeerPriceDtoBuilder{
        private Double totalPrice;

        public BeerPriceDtoBuilder(Double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public BeerPriceDtoBuilder totalPrice(Double totalPrice){
            this.totalPrice = totalPrice;
            return this;
        }

        public BeerPriceDto build(){
            BeerPriceDto beerPriceDto = new BeerPriceDto(this);
            return beerPriceDto;
        }
    }
}

package tech.nullpointerexception.bender.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerPriceDto {
    //por si se extiende más adelante
    @JsonProperty("Total Price")
    private Double totalPrice;
}

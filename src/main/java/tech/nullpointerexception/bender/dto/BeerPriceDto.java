package tech.nullpointerexception.bender.dto;

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
    private Double totalPrice;
}

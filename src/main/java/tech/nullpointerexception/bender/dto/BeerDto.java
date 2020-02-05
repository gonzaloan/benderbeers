package tech.nullpointerexception.bender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.nullpointerexception.bender.util.UtilConstants;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto {

    @NotNull(message = UtilConstants.ID_CANT_BE_NULL)
    private Integer id;
    @NotNull(message = UtilConstants.NAME_CANT_BE_NULL)
    private String name;
    @NotNull(message = UtilConstants.BREWERY_CANT_BE_NULL)
    private String brewery;
    @NotNull(message = UtilConstants.COUNTRY_CANT_BE_NULL)
    private String country;
    @NotNull(message = UtilConstants.PRICE_CANT_BE_NULL)
    private Double price;
    @NotNull(message = UtilConstants.CURRENCY_CANT_BE_NULL)
    private String currency;
}

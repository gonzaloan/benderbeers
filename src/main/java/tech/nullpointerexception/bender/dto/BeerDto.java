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

    private Integer id;
    private String name;
    private String brewery;
    private String country;
    private Double price;
    private String currency;
}

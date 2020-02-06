package tech.nullpointerexception.bender.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyTransformationDto {

    private Boolean success;
    private String terms;
    private String privacy;
    private Double result;
}

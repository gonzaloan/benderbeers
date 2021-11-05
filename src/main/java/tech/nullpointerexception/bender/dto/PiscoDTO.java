package tech.nullpointerexception.bender.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class PiscoDTO {
    private UUID id;
    private String name;
    private String brand;
}

package tech.nullpointerexception.bender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Beer {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String brewery;
    private String country;
    private Double price;
    private String currency;

}

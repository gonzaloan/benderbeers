package tech.nullpointerexception.bender.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.dto.BeerPriceDto;
import tech.nullpointerexception.bender.service.BeerService;
import tech.nullpointerexception.bender.util.UtilConstants;
import tech.nullpointerexception.bender.utils.TestConstants;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private BeerService beerService;

    @Test
    void getAllBeers() throws Exception {

        Mockito.when(beerService.getAllBeers()).thenReturn(TestConstants.GET_BEER_LIST());

        mockMvc.perform(MockMvcRequestBuilders.get("/beers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].Name", is(TestConstants.GET_BEER_LIST().get(0).getName())))
                .andDo(print());
    }

    @Test
    void getAllBeers_fails() throws Exception {

        Mockito.when(beerService.getAllBeers()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/beers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andDo(print());
    }


    @Test
    void getBeerById() throws Exception {
        Mockito.when(beerService.getBeerById(anyInt())).thenReturn(TestConstants.GET_BEER_LIST().get(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/beers/{beerID}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.Name", is(TestConstants.GET_BEER_LIST().get(0).getName())))
                .andDo(print());

    }

    @Test
    void postBeer() throws Exception {

        BeerDto beerDto = new BeerDto();
        beerDto.setName("Cerveza JUNIT");
        beerDto.setBrewery("Spring Test Cervezas");
        beerDto.setPrice(1900.0);
        beerDto.setId(5);
        beerDto.setCountry("USA");
        beerDto.setCurrency("CLP");

        Mockito.when(beerService.createBeer(any())).thenReturn(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/beers")
                .content(objectMapper.writeValueAsString(beerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    void postBeer_test_constraints() throws Exception {
        //it should throw an error due to missing fields
        BeerDto beerDto = new BeerDto();
        beerDto.setName("Cerveza JUNIT");
        beerDto.setBrewery("Spring Test Cervezas");
        beerDto.setPrice(1900.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/beers")
                .content(objectMapper.writeValueAsString(beerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print());

    }

    @Test
    void postBeer_when_is_service_fails() throws Exception {
        Mockito.when(beerService.createBeer(any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/beers")
                .content(objectMapper.writeValueAsString(TestConstants.GET_NEW_BEER()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andDo(print());

    }

}
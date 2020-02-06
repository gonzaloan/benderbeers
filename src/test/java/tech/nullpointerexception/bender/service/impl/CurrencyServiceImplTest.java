package tech.nullpointerexception.bender.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import tech.nullpointerexception.bender.dto.CurrencyTransformationDto;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CurrencyServiceImplTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    void convertBetweenCurrency() {

        ReflectionTestUtils.setField(currencyService, "accessKey", "129317273727273277123");
        ReflectionTestUtils.setField(currencyService, "baseUrl", "http://api.currencylayer.com/convert");

        ResponseEntity<CurrencyTransformationDto> responseEntity = new ResponseEntity<>(CurrencyTransformationDto.builder().success(Boolean.TRUE).result(2990.0).build(), HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
                Matchers.anyString(),
                Matchers.any(HttpMethod.class),
                Matchers.<HttpEntity<?>> any(),
                Matchers.<Class<CurrencyTransformationDto>> any())).thenReturn(responseEntity);

        Double converter = currencyService.convertBetweenCurrency("USD", "CLP", 1.0);

        System.out.println("===============> " + converter);


    }
}
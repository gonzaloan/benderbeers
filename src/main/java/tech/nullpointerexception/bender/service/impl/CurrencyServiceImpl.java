package tech.nullpointerexception.bender.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.nullpointerexception.bender.dto.CurrencyTransformationDto;
import tech.nullpointerexception.bender.service.CurrencyService;

import java.util.Collections;
import java.util.Objects;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${currency.accesskey}")
    private String accessKey;
    @Value("${currency.baseurl}")
    private String baseUrl;

    @Override
    public Double convertBetweenCurrency(String from, String to, Double value) {

        try {
            log.info("Convirtiendo Currency {} a {}: {}", from, to, value);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            CurrencyTransformationDto response = restTemplate.exchange(baseUrl + "?access_key=" + accessKey + "&from=" + from + "&to=" + to + "&amount=" + value, HttpMethod.GET, entity, CurrencyTransformationDto.class).getBody();

            Objects.requireNonNull(response);
            return response.getResult();

        } catch (Exception ex) {
            log.error("Error al convertir currency: {}", ex);
            return null;


        }

    }
}

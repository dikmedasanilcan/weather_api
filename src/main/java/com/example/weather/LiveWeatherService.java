package com.example.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class LiveWeatherService {

    //
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&APPID={78c5f01d919d6f0d7f25bb245dab3664}&units=metric";

    @Value("78c5f01d919d6f0d7f25bb245dab3664")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public LiveWeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    //uses RestTemplate to make a REST call tot the OpenWeatherAPI
    public CurrentWeather getCurrentWeather(String city) {
        URI url = new UriTemplate(WEATHER_URL).expand(city, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return convert(response);
    }

    //uses ObjectMapper to do a translation to CurrentWeather POJO
    private CurrentWeather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new CurrentWeather(root.path("weather").get(0).path("main").asText(),
                    BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("feels_like").asDouble()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
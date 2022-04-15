package com.example.weather;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StubWeatherService {

    public CurrentWeather getCurrentWeather(String city) {
        //returns our default values
        return new CurrentWeather("Clear", BigDecimal.ONE, BigDecimal.ZERO);
    }
}

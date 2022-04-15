package com.example.weather;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CurrentWeather implements Serializable {

    //POJO class. Information that we wanted to get are identified in here.

    private String description;
    private BigDecimal temperature;
    private BigDecimal feltTemperature;

    //getters, constructors, equals, and hashcode

    public String getDescription() {
        return description;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public BigDecimal getFeltTemperature() {
        return feltTemperature;
    }

    public CurrentWeather(String description, BigDecimal temperature, BigDecimal feltTemperature) {
        this.description = description;
        this.temperature = temperature;
        this.feltTemperature = feltTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentWeather that = (CurrentWeather) o;
        return Objects.equals(description, that.description) && Objects.equals(temperature, that.temperature) && Objects.equals(feltTemperature, that.feltTemperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, temperature, feltTemperature);
    }

}

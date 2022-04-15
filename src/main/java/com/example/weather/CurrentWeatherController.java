package com.example.weather;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class CurrentWeatherController {

    //returns the stub CurrentWeather POJO class
    private final StubWeatherService stubWeatherService;
    //uses our OpenWeatherMap API to get the actual weather
    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("")
    public String getCurrentWeather(Model model) {
        //boolean condition that switches between StubWeatherService and LiveWeatherService
        if (true) {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("Istanbul"));
        } else {
            model.addAttribute("currentWeather", stubWeatherService.getCurrentWeather("Istanbul"));
        }
        return "current-weather";
    }

    
}

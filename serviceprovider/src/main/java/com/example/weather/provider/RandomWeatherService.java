package com.example.weather.provider;

import com.example.weather.WeatherService;
import com.example.weather.WeatherProvider;

import java.util.Random;

@WeatherProvider("Random")
public class RandomWeatherService implements WeatherService {
    private static final String[] forecasts = {
            "soligt", "regnigt", "snöigt", "stormigt", "dimma"
    };

    public String getProviderName() { return "Random Weather"; }
    public String getForecast(String city) {
        Random random = new Random();
        String weather = forecasts[random.nextInt(forecasts.length)];
        return "Random: Det är " + weather + " i " + city;
    }
}

package com.example.weather.provider;

import com.example.weather.WeatherService;
import com.example.weather.WeatherProvider;

import java.util.Random;

@WeatherProvider("Random")
public class RandomWeatherService implements WeatherService {
    private static final String[] forecasts = {
            "Sunny", "rainy", "snowy", "stormy", "foggy"
    };

    public String getProviderName() { return "Random Weather"; }
    public String getForecast(String city) {
        Random random = new Random();
        String weather = forecasts[random.nextInt(forecasts.length)];
        return "Random: It is " + weather + " in " + city;
    }
}

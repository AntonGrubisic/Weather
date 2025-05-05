package com.example.weather.provider;

import com.example.weather.WeatherService;
import com.example.weather.WeatherProvider;

@WeatherProvider("SMHI")
public class SmhiWeatherService implements WeatherService {
    public String getProviderName() { return "SMHI"; }
    public String getForecast(String city) {
        return "SMHI: Sunny in " + city + " today!";
    }
}

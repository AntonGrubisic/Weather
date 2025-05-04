package com.example.weather;

public interface WeatherService {
    String getProviderName();
    String getForecast(String city);
}

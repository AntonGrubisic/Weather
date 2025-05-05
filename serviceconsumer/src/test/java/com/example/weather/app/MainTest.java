package com.example.weather.app;

import com.example.weather.WeatherService;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testGetForecastForValidChoice() {
        var mockService = new WeatherService() {
            public String getForecast(String city) {
                return "Sunny in " + city;
            }
            public String getProviderName() {
                return "MockProvider";
            }
        };
        String result = Main.getForecastForCity("London", 1, List.of(mockService));
        assertEquals("Sunny in London", result);
    }

    @Test
    void testGetForecastForInvalidChoice() {
        var mockService = new WeatherService() {
            public String getForecast(String city) {
                return "Sunny";
            }
            public String getProviderName() {
                return "MockProvider";
            }
        };
        String result = Main.getForecastForCity("London", 2, List.of(mockService));
        assertEquals("Invalid choice!", result);
    }

    @Test
    void testGetForecastWithNoProviders() {
        String result = Main.getForecastForCity("London", 1, List.of());
        assertEquals("No weather services found!", result);
    }
}

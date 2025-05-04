package com.example.weather;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WeatherProvider {
    String value();
}

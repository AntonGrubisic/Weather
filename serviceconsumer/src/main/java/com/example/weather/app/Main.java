package com.example.weather.app;

import com.example.weather.WeatherService;
import com.example.weather.WeatherProvider;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌤️ Välkommen till Weather Wizard 🌤️");
        System.out.print("Ange stad: ");
        String city = scanner.nextLine();

        List<WeatherService> providers = new ArrayList<>();
        ServiceLoader.load(WeatherService.class).forEach(providers::add);

        if (providers.isEmpty()) {
            System.out.println("Inga vädertjänster hittades!");
            return;
        }

        System.out.println("\nVälj vädertjänst:");
        for (int i = 0; i < providers.size(); i++) {
            WeatherService service = providers.get(i);
            WeatherProvider annotation = service.getClass().getAnnotation(WeatherProvider.class);
            String name = annotation != null ? annotation.value() : service.getProviderName();
            System.out.println((i + 1) + ". " + name);
        }

        System.out.print("Ditt val: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > providers.size()) {
            System.out.println("Ogiltigt val.");
            return;
        }

        WeatherService selected = providers.get(choice - 1);
        System.out.println("\n" + selected.getForecast(city));
    }
}

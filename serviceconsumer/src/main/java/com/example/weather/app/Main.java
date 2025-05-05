package com.example.weather.app;

import java.util.*;
import java.util.logging.Logger;
import com.example.weather.WeatherProvider;
import com.example.weather.WeatherService;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("🌤️ Welcome to Weather Wizard 🌤️");
            logger.info("\nEnter a city:");
            String city = scanner.nextLine();

            var providers = ServiceLoader.load(WeatherService.class)
                    .stream()
                    .map(ServiceLoader.Provider::get)
                    .toList();

            if (providers.isEmpty()) {
                logger.warning("No weather services found!");
                return;
            }

            logger.info("\nChoose your weather service:");
            for (int i = 0; i < providers.size(); i++) {
                var service = providers.get(i);
                var annotation = service.getClass().getAnnotation(WeatherProvider.class);
                String name = annotation != null ? annotation.value() : service.getProviderName();
                logger.info((i + 1) + ". " + name);
            }

            logger.info("Your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.warning("Invalid input.");
                continue;
            }

            String forecast = getForecastForCity(city, choice, providers);
            logger.info("\n" + forecast);

            logger.info("\nDo you want to check another city? (yes/no)");
            String again = scanner.nextLine().trim().toLowerCase();
            if (!again.equals("yes")) {
                logger.info("Goodbye!");
                break;
            }
        }
    }

    public static String getForecastForCity(String city, int choice, List<WeatherService> providers) {
        if (providers.isEmpty()) {
            return "No weather services found!";
        }
        if (choice < 1 || choice > providers.size()) {
            return "Invalid choice!";
        }
        var selected = providers.get(choice - 1);
        return selected.getForecast(city);
    }
}

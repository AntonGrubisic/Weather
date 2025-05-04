package com.example.weather.app;

import java.util.ServiceLoader;
import java.util.Scanner;
import java.util.logging.Logger;
import com.example.weather.WeatherProvider;
import com.example.weather.WeatherService;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        logger.info("\uD83C\uDF24\uFE0F Välkommen till Weather Wizard \uD83C\uDF24\uFE0F");
        logger.info("\nAnge stad: ");
        String city = scanner.nextLine();

        var providers = ServiceLoader.load(WeatherService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        if (providers.isEmpty()) {
            logger.warning("Inga vädertjänster hittades!");
            return;
        }

        logger.info("\nVälj vädertjänst:");
        for (int i = 0; i < providers.size(); i++) {
            var service = providers.get(i);
            var annotation = service.getClass().getAnnotation(WeatherProvider.class);
            String name = annotation != null ? annotation.value() : service.getProviderName();
            logger.info((i + 1) + ". " + name);
        }

        logger.info("Ditt val: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > providers.size()) {
            logger.warning("Ogiltigt val.");
            return;
        }

        var selected = providers.get(choice - 1);
        logger.info("\n" + selected.getForecast(city));
    }
}

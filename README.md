Weather Wizard 🌦️

📦 Beskrivning

Weather Wizard är en Java-konsolapplikation som använder Java SPI för att dynamiskt ladda vädertjänster.  
Användaren kan ange stad och välja mellan flera väderproviders.

---

✅ Funktioner

- Java SPI med `WeatherService`-interface
- Två providers: `SmhiWeatherService`, `RandomWeatherService`
- Annotation `@WeatherProvider` används för att namnge providers
- Maven multimodulprojekt (`weather`, `service`, `serviceprovider`, `serviceconsumer`)
- Fat jar byggs med Maven assembly plugin
- Docker-image bygger och kör appen

---

🔨 Bygg och kör

Med docker:
docker build -t weather-app .
docker run -it weather-app

Lokalt:
```bash
mvn clean package
java -jar serviceconsumer/target/serviceconsumer-1.0-SNAPSHOT-jar-with-dependencies.jar

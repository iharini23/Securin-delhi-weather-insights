package com.securin.DelhiWeatherService.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.securin.DelhiWeatherService.repository.WeatherRecordRepository;

@Component
public class CsvImportRunner implements CommandLineRunner {

    private final WeatherCsvImportService importService;
    private final WeatherRecordRepository repository;
    private final Environment env;

    public CsvImportRunner(WeatherCsvImportService importService,
                           WeatherRecordRepository repository,
                           Environment env) {
        this.importService = importService;
        this.repository = repository;
        this.env = env;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Mongo URI (env) = " + env.getProperty("spring.data.mongodb.uri"));
        System.out.println("Mongo DB  (env) = " + env.getProperty("spring.data.mongodb.database"));

        if (repository.count() > 0) {
            System.out.println("Weather data already exists. Skipping CSV import.");
            return;
        }

        int saved = importService.importFromClasspath("data/testset.csv");
        System.out.println("CSV import completed. Records inserted: " + saved);
    }
}
package com.securin.DelhiWeatherService.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.securin.DelhiWeatherService.entity.WeatherRecord;
import com.securin.DelhiWeatherService.repository.WeatherRecordRepository;

@Service
public class WeatherCsvImportService {

    private final WeatherRecordRepository repo;

    public WeatherCsvImportService(WeatherRecordRepository repo) {
        this.repo = repo;
    }

    public int importFromClasspath(String classpathCsvPath) throws Exception {

        System.out.println("Trying to load CSV from classpath: " + classpathCsvPath);

        InputStream is = getClass().getClassLoader().getResourceAsStream(classpathCsvPath);
        if (is == null) {
            System.out.println("CSV NOT FOUND at: " + classpathCsvPath);
            throw new IllegalArgumentException("CSV not found in classpath: " + classpathCsvPath);
        }

        CsvWeatherRowMapper mapper = new CsvWeatherRowMapper();
        int saved = 0;
        int totalRowsRead = 0;
        int skippedRows = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVReader reader = new CSVReader(br)) {

            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("CSV HEADER is null (empty file?)");
                return 0;
            }
            System.out.println("CSV header columns: " + header.length);

            final int BATCH_SIZE = 5000;
            List<WeatherRecord> batch = new ArrayList<>(BATCH_SIZE);

            String[] row;
            while ((row = reader.readNext()) != null) {
                totalRowsRead++;

                if (totalRowsRead <= 3) {
                    System.out.println("Sample row[0] datetime_utc = " + row[0] + " | columns=" + row.length);
                }

                if (row.length < 20) {
                    skippedRows++;
                    continue;
                }

                WeatherRecord record = mapper.map(row);

                if (record.getDatetimeUtc() == null) {
                    skippedRows++;
                    continue;
                }

                batch.add(record);

                if (batch.size() >= BATCH_SIZE) {
                    repo.saveAll(batch);
                    saved += batch.size();
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                repo.saveAll(batch);
                saved += batch.size();
            }
        }

        System.out.println("TOTAL ROWS READ: " + totalRowsRead);
        System.out.println("TOTAL ROWS SKIPPED: " + skippedRows);
        System.out.println("TOTAL SAVED: " + saved);

        return saved;
    }

}
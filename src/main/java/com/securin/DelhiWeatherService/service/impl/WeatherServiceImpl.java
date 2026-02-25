package com.securin.DelhiWeatherService.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.securin.DelhiWeatherService.dto.MonthlyTemperatureStatsDto;
import com.securin.DelhiWeatherService.dto.WeatherDetailsDto;
import com.securin.DelhiWeatherService.entity.WeatherRecord;
import com.securin.DelhiWeatherService.repository.WeatherRecordRepository;
import com.securin.DelhiWeatherService.service.WeatherService;
import com.securin.DelhiWeatherService.util.BadRequestException;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRecordRepository weatherRecordRepository;

    public WeatherServiceImpl(WeatherRecordRepository weatherRecordRepository) {
        this.weatherRecordRepository = weatherRecordRepository;
    }

    @Override
    public List<WeatherDetailsDto> getWeatherByDate(LocalDate date) {
        List<WeatherRecord> records = weatherRecordRepository.findByYearAndMonthAndDay(
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth()
        );
        return toWeatherDetailsDtoList(records);
    }

    @Override
    public List<WeatherDetailsDto> getWeatherByMonth(int year, int month) {
        validateMonth(month);
        List<WeatherRecord> records = weatherRecordRepository.findByYearAndMonth(year, month);
        return toWeatherDetailsDtoList(records);
    }

    @Override
    public List<MonthlyTemperatureStatsDto> getMonthlyTemperatureStats(int year) {
        List<MonthlyTemperatureStatsDto> result = new ArrayList<>(12);

        for (int month = 1; month <= 12; month++) {
            List<WeatherRecord> records = weatherRecordRepository.findByYearAndMonth(year, month);

            List<Double> temps = extractValidTemperatures(records);

            if (temps.isEmpty()) {
                result.add(new MonthlyTemperatureStatsDto(year, month, null, null, null));
                continue;
            }

            Collections.sort(temps);

            Double min = temps.get(0);
            Double max = temps.get(temps.size() - 1);
            Double median = computeMedianSorted(temps);

            result.add(new MonthlyTemperatureStatsDto(year, month, min, median, max));
        }

        result.sort(Comparator.comparingInt(MonthlyTemperatureStatsDto::getMonth));
        return result;
    }

    private List<WeatherDetailsDto> toWeatherDetailsDtoList(List<WeatherRecord> records) {
        List<WeatherDetailsDto> dtos = new ArrayList<>();
        if (records == null || records.isEmpty()) return dtos;

        for (WeatherRecord r : records) {
            if (r == null) continue;

            WeatherDetailsDto dto = new WeatherDetailsDto();
            dto.setDatetimeUtc(r.getDatetimeUtc());
            dto.setCondition(r.getConds());
            dto.setTemperature(r.getTempm());
            dto.setHumidity(r.getHum());
            dto.setPressure(r.getPressurem());

            dtos.add(dto);
        }

        dtos.sort(Comparator.comparing(WeatherDetailsDto::getDatetimeUtc,
                Comparator.nullsLast(Comparator.naturalOrder())));
        return dtos;
    }

    private List<Double> extractValidTemperatures(List<WeatherRecord> records) {
        List<Double> temps = new ArrayList<>();
        if (records == null || records.isEmpty()) return temps;

        for (WeatherRecord r : records) {
            if (r == null) continue;
            Double t = r.getTempm();
            if (t != null) temps.add(t);
        }
        return temps;
    }

    private Double computeMedianSorted(List<Double> sortedTemps) {
        int n = sortedTemps.size();
        if (n == 0) return null;

        if (n % 2 == 1) {
            return sortedTemps.get(n / 2);
        } else {
            double a = sortedTemps.get((n / 2) - 1);
            double b = sortedTemps.get(n / 2);
            return (a + b) / 2.0;
        }
    }

    private void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new BadRequestException("Month must be between 1 and 12");
        }
    }
}
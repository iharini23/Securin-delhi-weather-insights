package com.securin.DelhiWeatherService.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.securin.DelhiWeatherService.entity.WeatherRecord;

public interface WeatherRecordRepository extends MongoRepository<WeatherRecord, String> {

    List<WeatherRecord> findByDatetimeUtcBetween(LocalDateTime start, LocalDateTime end);

    List<WeatherRecord> findByDatetimeUtcBetween(LocalDateTime start, LocalDateTime end, Sort sort);

    List<WeatherRecord> findByYearAndMonth(int year, int month);

    List<WeatherRecord> findByYearAndMonthAndDay(int year, int month, int day);
}
package com.securin.DelhiWeatherService.service;

import java.time.LocalDate;
import java.util.List;

import com.securin.DelhiWeatherService.dto.MonthlyTemperatureStatsDto;
import com.securin.DelhiWeatherService.dto.WeatherDetailsDto;

public interface WeatherService {

    List<WeatherDetailsDto> getWeatherByDate(LocalDate date);

    
    List<WeatherDetailsDto> getWeatherByMonth(int year, int month);

   
    List<MonthlyTemperatureStatsDto> getMonthlyTemperatureStats(int year);
}
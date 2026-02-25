package com.securin.DelhiWeatherService.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.securin.DelhiWeatherService.dto.MonthlyTemperatureStatsDto;
import com.securin.DelhiWeatherService.dto.WeatherDetailsDto;
import com.securin.DelhiWeatherService.service.WeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/weather")
@Tag(name = "Weather API", description = "APIs for Delhi weather data (CSV -> MongoDB Atlas)")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Get weather details by date",
            description = "Returns weather condition, temperature, humidity, and pressure for the given date.")
    @GetMapping("/by-date")
    public List<WeatherDetailsDto> getByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return weatherService.getWeatherByDate(date);
    }

    @Operation(summary = "Get weather details by month",
            description = "Returns weather condition, temperature, humidity, and pressure for the given year and month.")
    @GetMapping("/by-month")
    public List<WeatherDetailsDto> getByMonth(
            @RequestParam("year") int year,
            @RequestParam("month") int month
    ) {
        return weatherService.getWeatherByMonth(year, month);
    }

    @Operation(summary = "Get monthly temperature stats for a year",
            description = "Returns min, median, and max temperature for each month of the given year.")
    @GetMapping("/temperature-stats")
    public List<MonthlyTemperatureStatsDto> getTemperatureStats(
            @RequestParam("year") int year
    ) {
        return weatherService.getMonthlyTemperatureStats(year);
    }
}
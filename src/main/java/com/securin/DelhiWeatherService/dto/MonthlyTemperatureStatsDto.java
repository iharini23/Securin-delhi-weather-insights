package com.securin.DelhiWeatherService.dto;

public class MonthlyTemperatureStatsDto {

    private int year;
    private int month;
    private Double minTemperature;
    private Double medianTemperature;
    private Double maxTemperature;

    public MonthlyTemperatureStatsDto() {
    }

    public MonthlyTemperatureStatsDto(int year, int month,
                                      Double minTemperature,
                                      Double medianTemperature,
                                      Double maxTemperature) {
        this.year = year;
        this.month = month;
        this.minTemperature = minTemperature;
        this.medianTemperature = medianTemperature;
        this.maxTemperature = maxTemperature;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMedianTemperature() {
        return medianTemperature;
    }

    public void setMedianTemperature(Double medianTemperature) {
        this.medianTemperature = medianTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
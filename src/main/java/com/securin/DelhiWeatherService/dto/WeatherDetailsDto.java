package com.securin.DelhiWeatherService.dto;
import java.time.LocalDateTime;

public class WeatherDetailsDto {

    private LocalDateTime datetimeUtc;
    private String condition;
    private Double temperature;
    private Integer humidity;
    private Double pressure;

    public WeatherDetailsDto() {
    }

    public WeatherDetailsDto(LocalDateTime datetimeUtc, String condition,
                             Double temperature, Integer humidity, Double pressure) {
        this.datetimeUtc = datetimeUtc;
        this.condition = condition;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public LocalDateTime getDatetimeUtc() {
        return datetimeUtc;
    }

    public void setDatetimeUtc(LocalDateTime datetimeUtc) {
        this.datetimeUtc = datetimeUtc;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
}
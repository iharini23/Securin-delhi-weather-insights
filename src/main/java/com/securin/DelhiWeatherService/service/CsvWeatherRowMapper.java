package com.securin.DelhiWeatherService.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.securin.DelhiWeatherService.entity.WeatherRecord;

public class CsvWeatherRowMapper {

    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

    public WeatherRecord map(String[] row) {

        WeatherRecord wr = new WeatherRecord();

        LocalDateTime dt = parseDateTime(row[0]);
        wr.setDatetimeUtc(dt);

        if (dt != null) {
            wr.setYear(dt.getYear());
            wr.setMonth(dt.getMonthValue());
            wr.setDay(dt.getDayOfMonth());
        }

        wr.setConds(s(row[1]));
        wr.setDewptm(d(row[2]));
        wr.setFog(i(row[3]));
        wr.setHail(i(row[4]));
        wr.setHeatindexm(d(row[5]));
        wr.setHum(i(row[6]));
        wr.setPrecipm(d(row[7]));
        wr.setPressurem(d(row[8]));
        wr.setRain(i(row[9]));
        wr.setSnow(i(row[10]));
        wr.setTempm(d(row[11]));
        wr.setThunder(i(row[12]));
        wr.setTornado(i(row[13]));
        wr.setVism(d(row[14]));
        wr.setWdird(i(row[15]));
        wr.setWdire(s(row[16]));
        wr.setWgustm(d(row[17]));
        wr.setWindchillm(d(row[18]));
        wr.setWspdm(d(row[19]));

        return wr;
    }

    private static LocalDateTime parseDateTime(String v) {
        String t = s(v);
        if (t == null) return null;

        try {
            return LocalDateTime.parse(t, DT_FMT);
        } catch (Exception e) {
            return null;
        }
    }

    private static String s(String v) {
        if (v == null) return null;
        String t = v.trim();
        if (t.isEmpty() || "NA".equalsIgnoreCase(t) || "N/A".equalsIgnoreCase(t) || "null".equalsIgnoreCase(t)) {
            return null;
        }
        return t;
    }

    private static Integer i(String v) {
        String t = s(v);
        if (t == null) return null;
        try {
            return Integer.valueOf(t);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private static Double d(String v) {
        String t = s(v);
        if (t == null) return null;
        try {
            return Double.valueOf(t);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
package com.sep.controller;

import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;
import com.sep.service.TemperatureRecordService;

import java.sql.SQLException;
import java.util.List;

public class TemperatureRecordController {
    private final TemperatureRecordService tempRecordService = new TemperatureRecordService();

    public TemperatureRecord add(TemperatureType type, String value, String langCode) throws SQLException {
        return tempRecordService.add(type, Double.parseDouble(value), langCode);
    }

    public List<TemperatureRecord> getAll(String langCode) throws SQLException {
        return tempRecordService.getAll(langCode);
    }

    public List<TemperatureType> getTypes(String langCode) throws SQLException {
        return tempRecordService.getTypes(langCode);
    }
}

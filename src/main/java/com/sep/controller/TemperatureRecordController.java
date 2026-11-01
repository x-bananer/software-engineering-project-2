package com.sep.controller;

import com.sep.model.TemperatureRecord;
import com.sep.model.TemperatureType;
import com.sep.service.TemperatureRecordService;

import java.sql.SQLException;
import java.util.List;

public class TemperatureRecordController {
    private final TemperatureRecordService service = new TemperatureRecordService();

    public TemperatureRecord add(TemperatureType type, String value) throws SQLException {
        return service.add(type, Double.parseDouble(value));
    }

    public List<TemperatureRecord> getAll() throws SQLException {
        return service.getAll();
    }

    public List<TemperatureType> getTypes() throws SQLException {
        return service.getTypes();
    }
}

package com.sep.controller;

import com.sep.model.TimeRecord;
import com.sep.model.TimeRecordType;
import com.sep.service.TimeRecordService;

import java.sql.SQLException;
import java.util.List;

public class TimeRecordController {
    private final TimeRecordService timeRecordService = new TimeRecordService();

    public TimeRecord add(String speed, String distance) throws SQLException {
        return timeRecordService.add(Double.parseDouble(speed), Double.parseDouble(distance));
    }

    public List<TimeRecord> getAll() throws SQLException {
        return timeRecordService.getAll();
    }

    public List<TimeRecordType> getTypes(String langCode) throws SQLException {
        return timeRecordService.getTypes(langCode);
    }
}

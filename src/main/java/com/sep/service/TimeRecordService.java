package com.sep.service;

import com.sep.dao.TimeRecordDAO;
import com.sep.dao.TimeRecordTypeDAO;
import com.sep.model.TimeRecord;
import com.sep.model.TimeRecordType;

import java.sql.SQLException;
import java.util.List;

public class TimeRecordService {
	private final TimeRecordDAO dao = new TimeRecordDAO();
	private final TimeRecordTypeDAO typeDAO = new TimeRecordTypeDAO();

	public TimeRecord add(double speed, double distance) throws SQLException {
		TimeRecord record = new TimeRecord(speed, distance, calculateTime(speed, distance));
		dao.add(record);
		return record;
	}

	public double calculateTime(double speed, double distance) {
		return distance / speed;
	}

	public List<TimeRecord> getAll() throws SQLException {
		return dao.getAll();
	}

	public List<TimeRecordType> getTypes(String langCode) throws SQLException {
		return typeDAO.getAll(langCode);
	}
}

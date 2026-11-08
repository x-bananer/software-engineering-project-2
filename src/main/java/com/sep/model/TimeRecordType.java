package com.sep.model;

public class TimeRecordType {
	private final String fieldName;
	private final String unit;
	private final String description;

	public TimeRecordType(String fieldName, String unit, String description) {
		this.fieldName = fieldName;
		this.unit = unit;
		this.description = description;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getUnit() {
		return unit;
	}

	public String getDescription() {
		return description;
	}
}

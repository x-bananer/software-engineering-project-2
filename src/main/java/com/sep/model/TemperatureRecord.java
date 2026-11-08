package com.sep.model;

public class TemperatureRecord {
	private final TemperatureType tempType;
	private final double fromTemp;
	private final double toTemp;

	public TemperatureRecord(TemperatureType tempType, double fromTemp, double toTemp) {
		this.tempType = tempType;
		this.fromTemp = fromTemp;
		this.toTemp = toTemp;
	}

	public TemperatureType getTempType() {
		return tempType;
	}

	public String getFrom() {
		return fromTemp + " " + fromUnit();
	}

	public String getTo() {
		return toTemp + " " + toUnit();
	}

	public double getFromTemp() {
		return fromTemp;
	}

	public double getToTemp() {
		return toTemp;
	}

	private String fromUnit() {
		return tempType.getFromSign();
	}

	private String toUnit() {
		return tempType.getToSign();
	}
}

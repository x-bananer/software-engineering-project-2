package com.sep.model;

public class TemperatureRecord {
	private final TemperatureType temperatureType;
	private final double fromTemperature;
	private final double toTemperature;

	public TemperatureRecord(TemperatureType temperatureType, double fromTemperature, double toTemperature) {
		this.temperatureType = temperatureType;
		this.fromTemperature = fromTemperature;
		this.toTemperature = toTemperature;
	}

	public String getType() {
		return temperatureType.getKey();
	}

	public TemperatureType getTemperatureType() {
		return temperatureType;
	}

	public String getFrom() {
		return fromTemperature + " " + fromUnit();
	}

	public String getTo() {
		return toTemperature + " " + toUnit();
	}

	public double getFromTemperature() {
		return fromTemperature;
	}

	public double getToTemperature() {
		return toTemperature;
	}

	private String fromUnit() {
		return temperatureType.getFromSign();
	}

	private String toUnit() {
		return temperatureType.getToSign();
	}
}

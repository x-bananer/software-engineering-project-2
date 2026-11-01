package com.sep.model;

public class TimeRecord {
	private final double speed;
	private final double distance;
	private final double time;

	public TimeRecord(double speed, double distance, double time) {
		this.speed = speed;
		this.distance = distance;
		this.time = time;
	}

	public String getSpeed() {
		return speed + " km/h";
	}

	public String getDistance() {
		return distance + " km";
	}

	public String getTime() {
		return time + " h";
	}

	public double getSpeedValue() {
		return speed;
	}

	public double getDistanceValue() {
		return distance;
	}

	public double getTimeValue() {
		return time;
	}
}

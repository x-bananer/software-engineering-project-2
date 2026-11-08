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

	public double getSpeed() {
		return speed;
	}

	public double getDistance() {
		return distance;
	}

	public double getTime() {
		return time;
	}
}

package com.sep.model;

public class TemperatureType {
    private final int id;
    private final String key;
	private final String name;
    private final String fromSign;
    private final String toSign;

    public TemperatureType(int id, String key, String name, String fromSign, String toSign) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.fromSign = fromSign;
        this.toSign = toSign;
    }

     public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getFromSign() {
        return fromSign;
    }

    public String getToSign() {
        return toSign;
    }
}

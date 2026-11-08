package com.sep.model;

public class TemperatureType {
    private final int id;
    private final String key;
	private final String name;
    private final String description;
    private final String fromSign;
    private final String toSign;

    public TemperatureType(int id, String key, String name, String description, String fromSign, String toSign) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public String getFromSign() {
        return fromSign;
    }

    public String getToSign() {
        return toSign;
    }
}

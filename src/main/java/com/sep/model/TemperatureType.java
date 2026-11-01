package com.sep.model;

public class TemperatureType {
    private final int id;
    private final String key;

    public TemperatureType(int id, String key) {
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return key;
    }
}

package com.sep.model;

public class TemperatureType {
    private final int id;
    private final String name;

    public TemperatureType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

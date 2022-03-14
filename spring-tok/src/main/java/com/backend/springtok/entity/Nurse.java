package com.backend.springtok.entity;

public class Nurse implements Staff {

    public void assist() {
        System.out.println("nurse is assisting");
    }

    @Override
    public String toString() {
        return "Nurse{}";
    }
}

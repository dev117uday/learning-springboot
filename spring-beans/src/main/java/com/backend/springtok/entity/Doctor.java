package com.backend.springtok.entity;

public class Doctor implements Staff {

    private String qualification;
    private Nurse nurse;

    public Doctor(String qualification) {
        this.qualification = qualification;
    }

    public void assist() {
        System.out.println("doctor is assisting");
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "qualification='" + qualification + '\'' +
                ", nurse=" + nurse +
                '}';
    }
}

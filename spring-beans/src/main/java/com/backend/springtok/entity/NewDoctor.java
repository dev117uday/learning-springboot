package com.backend.springtok.entity;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Scope(scopeName = "singleton")
public class NewDoctor implements Staff, BeanNameAware {

    private String qualification;

    public void assist() {
        System.out.println("new doctor is assisting");
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "NewDoctor{" +
                "qualification='" + qualification + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("call for setBeanName");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("post construct called");
    }
}

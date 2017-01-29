package com.example.shaloin.seventhassignmentc;

/**
 * Created by shaloin on 28/1/17.
 */

public class Employee {
    int id;
    String employeeName,employeeAge;
    byte[] employeeImage;

    public Employee(){

    }

    public Employee(int id, String employeeName, String employeeAge, byte[] employeeImage) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeImage = employeeImage;
    }

    public Employee(String employeeName, String employeeAge, byte[] employeeImage) {
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeImage = employeeImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public byte[] getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(byte[] employeeImage) {
        this.employeeImage = employeeImage;
    }
}

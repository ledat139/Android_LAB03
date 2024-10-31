package com.example.letiendat_22520214_lab03.student;

public class Student {
    private int id;
    private String name;
    private String sex;
    private String birthDay;
    private String address;
    private String faculty;

    public Student() {
        this.name = "";
        this.sex = "";
        this.birthDay = "";
        this.faculty = "";
        this.address = "";
    }

    public Student(String name, String sex, String birthDay, String faculty, String address) {
        this.name = name;
        this.sex = sex;
        this.birthDay = birthDay;
        this.faculty = faculty;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}

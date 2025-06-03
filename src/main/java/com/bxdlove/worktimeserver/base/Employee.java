package com.bxdlove.worktimeserver.base;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public class Employee {
    @NotNull
    @JsonbProperty("id")
    private String id;

    @JsonbProperty("birthday")
    private Date birthday;

    @JsonbProperty("email")
    private String email;

    @JsonbProperty("first-name")
    private String firstName;

    @JsonbProperty("password")
    private String password;

    @JsonbProperty("second-name")
    private String secondName;

    @JsonbProperty("weekly-hours")
    private int weeklyHours;

    public Employee() {
    }

    public Employee(String id, Date birthday, String email, String firstName, String password, String secondName, int weeklyHours) {
        this.id = id;
        this.birthday = birthday;
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.secondName = secondName;
        this.weeklyHours = weeklyHours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
}

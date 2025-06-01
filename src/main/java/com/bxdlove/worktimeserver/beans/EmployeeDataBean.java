package com.bxdlove.worktimeserver.beans;

import com.bxdlove.worktimeserver.base.Employee;
import com.bxdlove.worktimeserver.utils.EmployeeAdminUtils;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class EmployeeDataBean implements Serializable {
    private List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = EmployeeAdminUtils.getEmployees();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

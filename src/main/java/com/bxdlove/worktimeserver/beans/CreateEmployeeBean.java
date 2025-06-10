package com.bxdlove.worktimeserver.beans;

import com.bxdlove.worktimeserver.api.security.PasswordValidator;
import com.bxdlove.worktimeserver.base.Employee;
import com.bxdlove.worktimeserver.utils.EmployeeAdminUtils;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CreateEmployeeBean {
    @Inject
    EmployeeDataBean employeeDataBean;

    private Employee employee;

    @PostConstruct
    public void init() {
        employee = new Employee();
    }

    public void createEmployee() {
        if (!PasswordValidator.validatePassword(employee.getPassword())) {
            return;
        }

        EmployeeAdminUtils.createEmployee(employee);
        employeeDataBean.getEmployees().add(employee);
        employee = new Employee();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

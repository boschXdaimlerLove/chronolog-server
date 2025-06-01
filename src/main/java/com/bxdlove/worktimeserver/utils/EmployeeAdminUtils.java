package com.bxdlove.worktimeserver.utils;

import com.bxdlove.worktimeserver.base.Employee;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains utilities to manage employees.
 *
 * @author Gregor Gottschewski
 */
public class EmployeeAdminUtils {

    /**
     * @return a list with all employees.
     */
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            mongoClient.getDatabase("worktime_server")
                    .getCollection("employee")
                    .find()
                    .forEach(entry -> employees.add(new Employee(entry.get("_id", ObjectId.class).toString(),
                            entry.get("birthday", Date.class),
                            entry.get("email", String.class),
                            entry.get("first_name", String.class),
                            entry.get("password", String.class),
                            entry.get("second_name", String.class),
                            entry.get("weekly_hours", Integer.class))));
        }

        return employees;
    }

    /**
     * Stores specified employee in the database.
     *
     * @param employee the employee to store
     */
    public static void createEmployee(Employee employee) {
        Document document = new Document()
                .append("birthday", employee.getBirthday())
                .append("email", employee.getEmail())
                .append("first_name", employee.getFirstName())
                .append("second_name", employee.getSecondName())
                .append("password", employee.getPassword())
                .append("weekly_hours", employee.getWeeklyHours());

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            mongoClient.getDatabase("worktime_server")
                    .getCollection("employee")
                    .insertOne(document);
        }
    }
}

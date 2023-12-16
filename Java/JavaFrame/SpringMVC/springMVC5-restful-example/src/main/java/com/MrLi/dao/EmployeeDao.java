package com.MrLi.dao;

import com.MrLi.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrLi on 2022/02/23/17:53
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;
    private static Integer initId = 107;
    static {
        employees = new HashMap<>();
        employees.put(101, new Employee(101, "smith", "smith@qq.com", 1));
        employees.put(102, new Employee(102, "tony", "tony@qq.com", 1));
        employees.put(103, new Employee(103, "amy", "amy@qq.com", 0));
        employees.put(104, new Employee(104, "any", "any@qq.com", 0));
        employees.put(105, new Employee(105, "dog", "dog@qq.com", 1));
        employees.put(106, new Employee(106, "cat", "cat@qq.com", 0));
    }

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee get(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }
}
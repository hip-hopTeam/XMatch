package com.zsq.service;

import com.zsq.model.Department;

import java.util.List;


/**
 * @author _Lines
 * Created by _Lines on 2017/11/9.
 */
public interface DepartmentService {
    /**
     * 添加部门
     * @param dep
     * @return
     */
    public int addDepartment(Department dep);

    /**
     * 更新部门
     * @param dep
     * @return
     */
    public int updateDepartment(Department dep);

    public Department getDepartment(long departmentId);

    public List<Department> getAll();

    public Department getDepartmentByDepName(String depName);

}

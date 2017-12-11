package com.zsq.service;

import com.zsq.model.ChildDepartment;
import com.zsq.model.Department;

import java.util.List;
import java.util.Map;


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

    public List<Department> getAllDepartments(int page, int rows);

    public Department getDepartmentByDepName(String depName);

    /**
     * 通过部门id查找子部门
     * @param depId
     * @return
     */
    public Map<String, Object> getChildDepartmentByDepId(long depId);

    /**
     * 查找所有子部门
     */
    public List<ChildDepartment> getAllChildDep();

    /**
     * 添加子部门
     */
    public int addChildDepartment(ChildDepartment childDepartment);

    /**
     * 更新子部门
     */
    public int updateChildDepartment(ChildDepartment childDepartment);


    /**
     * 删除子部门
     */
    public int deleteChildDepartment(long childDepartmentId);
}

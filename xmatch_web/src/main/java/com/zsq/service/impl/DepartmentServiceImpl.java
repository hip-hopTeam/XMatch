package com.zsq.service.impl;

import com.zsq.model.Department;
import com.zsq.model.DepartmentRepository;
import com.zsq.model.UserRepository;
import com.zsq.service.DepartmentService;
import com.zsq.util.LsyResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author _Lines
 * Created by _Lines on 2017/11/9.
 */
@Service
@EnableTransactionManagement
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository repository;

    @Override
    public int addDepartment(Department dep) {
        Department isExitDepartment = repository.findDepartmentByDepName(dep.getDepName());
        if(isExitDepartment != null && isExitDepartment.getDepartmentId() >= 0) {
            return LsyResultCode.Companion.getDEP_EXIT();
        }
        dep.setCreatTime(System.currentTimeMillis());
        repository.save(dep);
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int updateDepartment(Department dep) {
        Department resDep = repository.findOne(dep.getDepartmentId());
        String depName = resDep.getDepName();
        BeanUtils.copyProperties(dep,resDep);
        //resDep
        return 0;
    }

    @Override
    public Department getDepartment(long departmentId) {
        Department department = repository.findOne(departmentId);
        return null;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = repository.getAll();
        return departments;
    }

    @Override
    public Department getDepartmentByDepName(String depName) {
        Department department = repository.findDepartmentByDepName(depName);
        return null;
    }


}

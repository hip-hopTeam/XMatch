package com.zsq.service.impl;

import com.zsq.model.*;
import com.zsq.service.DepartmentService;
import com.zsq.util.LsyResultCode;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    ChildDepartmentRepository childDepartmentRepository;

    @Autowired
    DepManagerRepository depManagerRepository;

    @Override
    public int addDepartment(Department dep) {
        Department isExitDepartment = repository.findDepartmentByDepName(dep.getDepName());
        if(isExitDepartment != null && isExitDepartment.getDepartmentId() >= 0) {
            return LsyResultCode.Companion.getDEP_EXIT();
        }
        dep.setCreatTime(System.currentTimeMillis());
        dep.setState(1);
        repository.save(dep);
        DepManager depManager = depManagerRepository.findOne(dep.getDepManagerId());
        depManager.setDepartmentId(dep.getDepartmentId());
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int updateDepartment(Department dep) {
        Department resDep = repository.findOne(dep.getDepartmentId());
        if(resDep == null) {
            return LsyResultCode.Companion.getDEP_NOT_EXIT();
        }
        resDep.setDepSummary(dep.getDepSummary());
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public Department getDepartment(long departmentId) {
        Department department = repository.findOne(departmentId);
        return department;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = repository.getAll();
        return departments;
    }

    @Override
    public Department getDepartmentByDepName(String depName) {
        Department department = repository.findDepartmentByDepName(depName);
        return department;
    }

    @Override
    public Map<String, Object> getChildDepartmentByDepId(long depId) {
        Map<String, Object> result = new HashMap<>();
        Department department = repository.findOne(depId);
        if(department==null) {
            result.put("code", WyyResultCode.Companion.getDEP_NOT_EXIST());
            return result;
        }
        List<ChildDepartment> childDepartments = childDepartmentRepository.getByDepartmentId(depId);
        result.put("code", WyyResultCode.Companion.getSUCCESS());
        result.put("childDepartments", childDepartments);
        return result;
    }

    @Override
    public List<ChildDepartment> getAllChildDep() {
        return childDepartmentRepository.getAll();
    }

    @Override
    public int addChildDepartment(ChildDepartment childDepartment) {
        Department department = repository.findOne(childDepartment.getDepartmentId());
        if(department==null) {
            return WyyResultCode.Companion.getDEP_NOT_EXIST();
        }
        ChildDepartment isExistChildDep = childDepartmentRepository.getByDepartmentIdAndName(childDepartment.getDepartmentId(),
                childDepartment.getName());
        if(isExistChildDep!=null&&isExistChildDep.getChildDepartmentId()>=0) {
            return WyyResultCode.Companion.getCHILD_DEP_EXIST();
        }
        childDepartmentRepository.save(childDepartment);
        department.setChildDepNum(department.getChildDepNum()+1);
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int updateChildDepartment(ChildDepartment childDepartment) {
        Department department = repository.findOne(childDepartment.getDepartmentId());
        if(department==null) {
            return WyyResultCode.Companion.getDEP_NOT_EXIST();
        }
        ChildDepartment isExistChildDep = childDepartmentRepository.getByChildDepartmentId(childDepartment.getChildDepartmentId());
        if(isExistChildDep==null) {
            return WyyResultCode.Companion.getCHILD_DEP_NOT_EXIST();
        }
        isExistChildDep.setEmail(childDepartment.getEmail());
        isExistChildDep.setPhone(childDepartment.getPhone());
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteChildDepartment(long childDepartmentId) {
        ChildDepartment childDepartment = childDepartmentRepository.findOne(childDepartmentId);
        if (childDepartment == null) {
            return WyyResultCode.Companion.getCHILD_DEP_NOT_EXIST();
        }
        Department department = repository.findOne(childDepartment.getDepartmentId());
        if(department!=null) {
            department.setChildDepNum(department.getChildDepNum()-1);
        }
        childDepartmentRepository.delete(childDepartmentId);
        return WyyResultCode.Companion.getSUCCESS();
    }


}

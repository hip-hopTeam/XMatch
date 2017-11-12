package com.zsq.controller;

import com.zsq.model.Department;
import com.zsq.service.DepartmentService;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ObjectMessage;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author _Lines
 * Created by _Lines on 2017/11/9.
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/add")
    public BaseMessage addDepartment(@RequestBody Department dep) {
        BaseMessage message = new BaseMessage();
        message.code = departmentService.addDepartment(dep);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/findAll")
    public ObjectMessage findAll() {
        ObjectMessage message = new ObjectMessage();
        List<Department> departments = departmentService.getAll();
        message.code = WyyResultCode.Companion.getSUCCESS();
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        message.object = departments;
        return message;
    }

    @RequestMapping("/update")
    public ObjectMessage updateDepartment(@RequestBody Department dep) {
        ObjectMessage message = new ObjectMessage();
        int result = departmentService.updateDepartment(dep);
        message.code = result;
        message.result = LsyResultCode.Companion.getMap().get(result);
        return message;
    }

    @RequestMapping("/get")
    public ObjectMessage getDepartment(@RequestParam("departmentId") long departmentId) {
        ObjectMessage message = new ObjectMessage();
        Department dep = departmentService.getDepartment(departmentId);
        if(dep == null) {
            message.code = LsyResultCode.Companion.getDEP_NOT_EXIT();
            message.result = LsyResultCode.Companion.getMap().get(message.code);
            return message;
        }
        message.code = LsyResultCode.Companion.getSUCCESS();
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        message.object =  dep;

        return message;
    }


}

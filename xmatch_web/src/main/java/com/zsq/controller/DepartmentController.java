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

   // @RequestMapping("/update")


}

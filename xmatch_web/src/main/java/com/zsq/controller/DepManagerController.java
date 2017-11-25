package com.zsq.controller;

import com.zsq.dto.DepManagerDto;
import com.zsq.model.DepManager;
import com.zsq.model.Department;
import com.zsq.service.*;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ObjectMessage;
import com.zsq.util.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by _Lines on 2017/11/10.
 */
@RestController
@RequestMapping("/api/depManager")
public class DepManagerController {
    @Autowired
    DepManagerService depManagerService;
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/add")
    public BaseMessage addDepManager(@RequestBody DepManager depManager) {
        BaseMessage message = new BaseMessage();
        message.code = depManagerService.addDepManager(depManager);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/avator/add")
    public BaseMessage addDepManagerAvator(
            @RequestParam("depManagerId") long depManagerId
            , @RequestParam("avator")MultipartFile file,
            HttpServletRequest request) {
        BaseMessage message = new BaseMessage();

        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String prefix=file.getOriginalFilename().replaceAll(".*(\\.(.*))","$1");
        String url="/avator_dep/"+depManagerId+prefix;
        File avator = new File(rootPath+"/static"+url);
        if (!avator.getParentFile().exists()) {
            avator.getParentFile().mkdirs();
        }
        try {
            file.transferTo(avator);
        } catch (IOException e) {
            message.code = LsyResultCode.Companion.getERROR();
            message.result= LsyResultCode.Companion.getMap().get(message.code);
            e.printStackTrace();
            return message;
        }
        message.code = depManagerService.addDepManagerAvator(depManagerId, url);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }


    @RequestMapping("/update")
    public ObjectMessage updateDepManager(@RequestBody DepManager depManager) {
        ObjectMessage message = new ObjectMessage();
        message.code = depManagerService.updateDepManager(depManager);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }
    
    @RequestMapping("/get")
    public ObjectMessage getUser(@RequestParam("depManagerId") long depManagerId) {
        ObjectMessage message = new ObjectMessage();
        DepManager depManager = depManagerService.getDepManager(depManagerId);
        if (depManager == null) {
            message.code = LsyResultCode.Companion.getDEPMANAGER_NOT_EXIST();
            message.result = LsyResultCode.Companion.getMap().get(message.code);
            return message;
        }
        DepManagerDto depManagerDto = new DepManagerDto();
        BeanUtils.copyProperties(depManager, depManagerDto);
        if (depManager.getDepartmentId() != 0) {
            Department department = departmentService.getDepartment(depManager.getDepartmentId());
            BeanUtils.copyProperties(department, depManagerDto);
        }
        message.code = LsyResultCode.Companion.getSUCCESS();
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        message.object = depManagerDto;
        return message;
    }

    @RequestMapping("/login")
    public ObjectMessage depManagerLogin(@RequestParam("depManagerAccount") String depManagerAccount,
                                         @RequestParam("password") String password) {
        ObjectMessage message= new ObjectMessage();
        Map<String,Object> result = depManagerService.depMangerLogin(depManagerAccount,password);
        message.code = (int) result.get("code");
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        if(message.code == LsyResultCode.Companion.getSUCCESS()) {
            DepManager depManager = (DepManager) result.get("depManager");
            DepManagerDto depManagerDto = new DepManagerDto();
            if (depManager.getDepartmentId() != 0) {
                Department department = departmentService.getDepartment(depManager.getDepartmentId());
                BeanUtils.copyProperties(department, depManagerDto);
            }
            BeanUtils.copyProperties(depManager,depManagerDto);
            message.object = depManagerDto;
        }
        return message;
    }
}

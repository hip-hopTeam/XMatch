package com.zsq.controller;

import com.zsq.model.DepartmentAlbum;
import com.zsq.service.DepartmentAlbumService;
import com.zsq.util.BaseMessage;
import com.zsq.util.LsyResultCode;
import com.zsq.util.ObjectMessage;
import com.zsq.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 
 * Created by coderqiang on 2017/11/14.
 */

@RestController
@RequestMapping("/api/album/image")
public class DepartmentAlbumController {

    @Autowired
    DepartmentAlbumService departmentAlbumService;
    
    @RequestMapping("/add")
    public ObjectMessage addAlbum(@RequestBody DepartmentAlbum departmentAlbum) {
        ObjectMessage message = new ObjectMessage();
        try {
            Map<String,Object> result=departmentAlbumService.addAlbum(departmentAlbum);
            message.code = (int) result.get("code");
            message.result = ResultCode.Companion.getMap().get(message.code);
            message.object=result.get("result");
        } catch (Exception e) {
            e.printStackTrace();
            message.code = ResultCode.Companion.getERROR();
            message.result = ResultCode.Companion.getMap().get(message.code);
        }
        return message;
    }

    @RequestMapping("/upload")
    public BaseMessage uploadAlbum( 
            @RequestParam("departmentAlbumId") long departmentAlbumId
            , @RequestParam("file")MultipartFile file,
                                    HttpServletRequest request) {
        BaseMessage message = new BaseMessage();
        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String prefix=file.getOriginalFilename().replaceAll(".*(\\.(.*))","$1");
        String url="/album/"+departmentAlbumId+prefix;
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
        message.code = departmentAlbumService.uploadAlbumImage(departmentAlbumId, url);
        message.result = LsyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/get_activity")
    public ObjectMessage getAlbumByActivity(
            @RequestParam(value = "activityId")long activityId) {
        ObjectMessage message = new ObjectMessage();
        try {
            List<DepartmentAlbum> albums = departmentAlbumService.getAlbumByActivity(activityId);
            message.code = ResultCode.Companion.getSUCCESS();
            message.result = ResultCode.Companion.getMap().get(message.code);
            message.object = albums;
        } catch (Exception e) {
            e.printStackTrace();
            message.code = ResultCode.Companion.getERROR();
            message.result = ResultCode.Companion.getMap().get(message.code);
        }
        return message;
    }


    @RequestMapping("/get_dep")
    public ObjectMessage getAlbumByDep(
            @RequestParam(value = "depId")long depId) {
        ObjectMessage message = new ObjectMessage();
        try {
            List<DepartmentAlbum> albums = departmentAlbumService.getAlbumByDepId(depId);
            message.code = ResultCode.Companion.getSUCCESS();
            message.result = ResultCode.Companion.getMap().get(message.code);
            message.object = albums;
        } catch (Exception e) {
            e.printStackTrace();
            message.code = ResultCode.Companion.getERROR();
            message.result = ResultCode.Companion.getMap().get(message.code);
        }
        return message;
    }


}

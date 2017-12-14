package com.zsq.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.zsq.model.Course;
import com.zsq.service.CourseService;
import com.zsq.util.BaseMessage;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ${wyy} on 2017/12/13/013.
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping("/add")
    public BaseMessage addCourse(@RequestBody Course course) {
        BaseMessage message = new BaseMessage();
        message.code = courseService.addCourse(course);
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        return message;
    }

    @RequestMapping("/adds")
    public BaseMessage addCourses(@RequestBody List<Course> courses) {
        BaseMessage message = new BaseMessage();
        message.code = courseService.addCourses(courses);
        message.result = WyyResultCode.Companion.getMap().get(message.code);
        return message;
    }



}
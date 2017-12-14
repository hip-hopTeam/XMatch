package com.zsq.service.impl;

import com.zsq.model.Course;
import com.zsq.model.CourseRepository;
import com.zsq.model.User;
import com.zsq.model.UserRepository;
import com.zsq.service.CourseService;
import com.zsq.util.ResultCode;
import com.zsq.model.*;
import com.zsq.service.CourseService;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${wyy} on 2017/12/13/013.
 */
@Service
@EnableTransactionManagement
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepMemberRepository depMemberRepository;

    @Override
    public int addCourse(Course course) {
        User isExistUser = userRepository.findUsersByUserId(course.getUserId());
        if(isExistUser == null) {
            return WyyResultCode.Companion.getUSER_NOT_EXIST();
        }
        courseRepository.save(course);
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int addCourses(List<Course> courses) {
        courseRepository.save(courses);
        return ResultCode.Companion.getSUCCESS();
    }

    @Override
    public int deleteCourse(long courseId) {
        Course isExistCourse = courseRepository.findByCourseId(courseId);
        if(isExistCourse == null) {
            return WyyResultCode.Companion.getCOURSE_NOT_EXIST();
        }
        courseRepository.delete(isExistCourse);
        return WyyResultCode.Companion.getSUCCESS();
    }

    @Override
    public Map<String, Object> getCourseByUserId(long userId) {
        User isExistUser = userRepository.findUsersByUserId(userId);
        Map<String, Object> result = new HashMap<>();
        if(isExistUser == null) {
            result.put("code", WyyResultCode.Companion.getUSER_NOT_EXIST());
            return result;
        }
        List<Course> courses = courseRepository.findByUserId(userId);
        result.put("code", WyyResultCode.Companion.getSUCCESS());
        result.put("courses", courses);
        return result;
    }

    @Override
    public Map<String, Object> getCourseByDepId(long depId) {
        Department isExistDep = departmentRepository.getByDepartmentId(depId);
        Map<String, Object> result = new HashMap<>();
        if(isExistDep == null) {
            result.put("code", WyyResultCode.Companion.getDEP_NOT_EXIST());
            return result;
        }
        List<DepMember> depMembers = depMemberRepository.findDepMemberByDepId(depId);
        List<List<Course>> res = new ArrayList<>();
        for(DepMember depMember : depMembers) {
            List<Course> courses = courseRepository.findByUserId(depMember.getUserId());
            res.add(courses);
        }
        result.put("code", WyyResultCode.Companion.getSUCCESS());
        result.put("courses", res);
        return result;
    }
}

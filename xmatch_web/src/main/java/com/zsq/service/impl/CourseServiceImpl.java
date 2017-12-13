package com.zsq.service.impl;

import com.zsq.model.Course;
import com.zsq.model.CourseRepository;
import com.zsq.model.User;
import com.zsq.model.UserRepository;
import com.zsq.service.CourseService;
import com.zsq.util.WyyResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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
    public int deleteCourse(long courseId) {
        Course isExistCourse = courseRepository.findByCourseId(courseId);
        if(isExistCourse == null) {
            return WyyResultCode.Companion.getCOURSE_NOT_EXIST();
        }
        courseRepository.delete(isExistCourse);
        return WyyResultCode.Companion.getSUCCESS();
    }
}

package com.zsq.service;

import com.zsq.model.Course;

import java.util.List;
import java.util.Map;

/**
 * Created by ${wyy} on 2017/12/13/013.
 */
public interface CourseService {
    public int addCourse(Course course);
    public int addCourses(List<Course> courses);
    public int deleteCourse(long courseId);
    public Map<String, Object> getCourseByUserId(long userId);
    public Map<String, Object> getCourseByDepId(long depId);
}

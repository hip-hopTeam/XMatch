package com.zsq.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ${wyy} on 2017/12/13/013.
 */

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    public Course findByCourseId(long courseId);
}
package com.zsq.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ${wyy} on 2017/12/13/013.
 */
public interface CourseRepository extends PagingAndSortingRepository<com.zsq.model.Course, Long> {

    public com.zsq.model.Course findByCourseId(long courseId);

    @Query("select c from Course c where c.userId=?1")
    public List<Course> findByUserId(long userId);
}

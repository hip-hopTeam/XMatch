package com.zsq.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author _Lines
 * Created by _Lines on 2017/11/9.
 */
public interface DepartmentRepository extends PagingAndSortingRepository<Department,Long> {
    public Department findDepartmentByDepName(String depName);

    @Query("select d from Department d")
    public Page<Department> getAllDepartments(Pageable pageable);
}

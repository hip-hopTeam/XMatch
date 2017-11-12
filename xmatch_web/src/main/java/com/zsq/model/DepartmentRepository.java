package com.zsq.model;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author _Lines
 * Created by _Lines on 2017/11/9.
 */
public interface DepartmentRepository extends PagingAndSortingRepository<Department,Long> {
    public Department findDepartmentByDepName(String depName);
}

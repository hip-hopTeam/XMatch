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

    @Query("select d from Department d where d.departmentId=?1")
    public Department getDepartmentByDepartmentId(long depId);

    @Query("select d from Department d")
    public Page<Department> getAllDepartments(Pageable pageable);

    @Query("select d from Department d where d.departmentId in (?1)")
    public List<Department> getDepartmentsByDepartmentId(List<Long> depIds);

    @Query("select d from Department d where d.departmentId=?1")
    public Department getByDepartmentId(long depId);
}

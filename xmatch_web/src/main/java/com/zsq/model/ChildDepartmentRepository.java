package com.zsq.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wyy on 2017/11/13/013.
 */
public interface ChildDepartmentRepository extends PagingAndSortingRepository<ChildDepartment, Long> {
    @Query("select a from ChildDepartment a where a.departmentId=:depId")
    public Page<ChildDepartment> getByDepartmentId(@Param("depId") long depId, Pageable pageable);

    public ChildDepartment getByDepartmentIdAndName(long depId, String name);
    public ChildDepartment getByChildDepartmentId(long childDepId);

    @Query("select d from ChildDepartment d")
    public Page<ChildDepartment> getAll(Pageable pageable);
}

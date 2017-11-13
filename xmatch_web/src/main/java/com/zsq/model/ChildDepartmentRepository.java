package com.zsq.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by wyy on 2017/11/13/013.
 */
public interface ChildDepartmentRepository extends PagingAndSortingRepository<ChildDepartment, Long> {
    public List<ChildDepartment> getByDepartmentId(long depId);
    public ChildDepartment getByDepartmentIdAndName(long depId, String name);
    public ChildDepartment getByChildDepartmentId(long childDepId);
    @Query("select d from ChildDepartment d")
    public List<ChildDepartment> getAll();
}

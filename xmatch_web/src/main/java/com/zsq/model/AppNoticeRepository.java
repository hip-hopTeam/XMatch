package com.zsq.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ThomasNg on 2017/11/10.
 */
public interface AppNoticeRepository extends PagingAndSortingRepository<AppNotice,Long>{
    /**
     * 返回对应学号的学生
     * @param departmentId,type 部门ID，群发类型
     * @return 返回对应学号的学生
     */
    public AppNotice findAppNoticeByDepartmentIdAndType(long departmentId,int type);


}


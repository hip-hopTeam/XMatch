package com.zsq.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ThomasNg on 2017/11/10.
 */
public interface AppNoticeRepository extends PagingAndSortingRepository<AppNotice,Long>{
    /**
     * 返回对应学号的学生
     * @param departmentId,type 部门ID，群发类型
     * @return 返回对应部门ID的通知
     */

    public List<AppNotice> findAppNoticesByDepartmentId(long departmentId);

    @Query("select a from AppNotice a where a.departmentId=?1 and a.type=?2")
    public List<AppNotice> findAppNoticesByDepartmentIdAndType(long departmentId,int type);

    @Query("select a from AppNotice a")
    public List<AppNotice> getAll();
}


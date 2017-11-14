package com.zsq.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by coderqiang on 2017/11/14.
 */
public interface DepartmentAlbumRepository extends PagingAndSortingRepository<DepartmentAlbum,Long> {

    @Query("select a from DepartmentAlbum a where a.depId=?1")
    public List<DepartmentAlbum> getbyDepId(long depId);

    @Query("select a from DepartmentAlbum a where a.activityId=?1")
    public List<DepartmentAlbum> getbyActivityId(long activity);


}

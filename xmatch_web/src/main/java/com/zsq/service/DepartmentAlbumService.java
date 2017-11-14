package com.zsq.service;

import com.zsq.model.DepartmentAlbum;

import java.util.List;

/**
 * Created by coderqiang on 2017/11/14.
 */
public interface DepartmentAlbumService {

    public int addAlbum(DepartmentAlbum departmentAlbum);

    public int uploadAlbumImage(long departmentAlbumId, String url);

    public List<DepartmentAlbum> getAlbumByDepId(long depId);

    public List<DepartmentAlbum> getAlbumByActivity(long activity);

    public int deleteAlbum(long departmentAlbumId);

}

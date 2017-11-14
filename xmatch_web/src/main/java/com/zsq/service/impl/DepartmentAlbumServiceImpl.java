package com.zsq.service.impl;

import com.zsq.model.DepartmentAlbum;
import com.zsq.model.DepartmentAlbumRepository;
import com.zsq.service.DepartmentAlbumService;
import com.zsq.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by coderqiang on 2017/11/14.
 */
@Service
@EnableTransactionManagement
@Transactional
public class DepartmentAlbumServiceImpl implements DepartmentAlbumService {

    @Autowired
    private DepartmentAlbumRepository departmentAlbumRepository;


    @Override
    public Map<String, Object> addAlbum(DepartmentAlbum departmentAlbum) {
        Map<String, Object> result = new HashMap<>();
        departmentAlbumRepository.save(departmentAlbum);
        result.put("code",ResultCode.Companion.getSUCCESS());
        result.put("result", departmentAlbum);
        return result;
    }

    @Override
    public int uploadAlbumImage(long departmentAlbumId, String url) {
        DepartmentAlbum departmentAlbum = departmentAlbumRepository.findOne(departmentAlbumId);
        departmentAlbum.setImageUrl(url);
        return ResultCode.Companion.getSUCCESS();
    }

    @Override
    public List<DepartmentAlbum> getAlbumByDepId(long depId) {
        List<DepartmentAlbum> departmentAlbums = departmentAlbumRepository.getbyDepId(depId);
        return departmentAlbums;
    }

    @Override
    public List<DepartmentAlbum> getAlbumByActivity(long activity) {
        List<DepartmentAlbum> departmentAlbums = departmentAlbumRepository.getbyDepId(activity);
        return departmentAlbums;
    }

    @Override
    public int deleteAlbum(long departmentAlbumId) {
        departmentAlbumRepository.delete(departmentAlbumId);
        return ResultCode.Companion.getSUCCESS();
    }
}

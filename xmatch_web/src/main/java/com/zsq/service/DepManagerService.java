package com.zsq.service;

import java.util.Map;
import com.zsq.model.DepManager;
import com.zsq.model.Department;

/**
 * Created by hp on 2017/11/10.
 */
public interface DepManagerService {

    public DepManager getDepManager(long depManagerId);
    public int addDepManager(DepManager depManager);

    public int updateDepManager(DepManager depManager);


    public Map<String,Object> depMangerLogin(String depManagerAccount, String password);
}

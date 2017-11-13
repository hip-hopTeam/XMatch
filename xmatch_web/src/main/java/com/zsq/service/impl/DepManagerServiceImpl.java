package com.zsq.service.impl;

import com.zsq.model.DepManager;
import com.zsq.model.DepManagerRepository;
import com.zsq.service.DepManagerService;
import com.zsq.util.LsyResultCode;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2017/11/10.
 */
@Service
@EnableTransactionManagement
@Transactional
public class DepManagerServiceImpl implements DepManagerService {
    @Autowired
    DepManagerRepository repository;

    @Override
    public DepManager getDepManager(long depManagerId) {
        DepManager depManager = repository.findOne(depManagerId);
        return depManager;
    }

    @Override
    public int addDepManager(DepManager depManager) {
        DepManager isExitDepManager = repository.findDepManagerByDepManagerAccount(depManager.getDepManagerAccount());
        if(isExitDepManager != null && isExitDepManager.getDepManagerId() >= 0) {
            return LsyResultCode.Companion.getDEPMANAGER_EXIST();
        }
        depManager.setDepManagerId(0);
        depManager.setRole(1);
        repository.save(depManager);
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public int updateDepManager(DepManager depManager) {
        DepManager resDepManager = repository.findOne(depManager.getDepManagerId());
        if(resDepManager == null) {
            return LsyResultCode.Companion.getDEPMANAGER_NOT_EXIST();
        }
        resDepManager.setEmail(depManager.getEmail());
        resDepManager.setManagerSummary(depManager.getManagerSummary());
        resDepManager.setManagerName(depManager.getManagerName());
        resDepManager.setPhoneNum(depManager.getPhoneNum());
        return LsyResultCode.Companion.getSUCCESS();
    }

    @Override
    public Map<String,Object> depMangerLogin(String depManagerAccount, String password) {
        Map<String,Object> result = new HashMap<>();
        DepManager depManager = repository.findDepManagerByDepManagerAccount(depManagerAccount);
        if(depManager == null) {
            result.put("code", LsyResultCode.Companion.getDEPMANAGER_NOT_EXIST());
            return result;
        }
        if(!password.equals(depManager.getPassword())) {
            result.put("code", LsyResultCode.Companion.getDEPMANAGER_PASSWORD_ERROR());
            return result;
        }
        result.put("code",LsyResultCode.Companion.getSUCCESS());
        result.put("depManager",depManager);
        return result;
    }
}

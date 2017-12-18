package com.zsq.controller;

import com.zsq.service.LsyRotaService;
import com.zsq.util.ObjectMessage;
import com.zsq.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2017/12/17.
 */

@RestController
@RequestMapping("/api/rota")
public class LsyRotaController {
    @Autowired
    LsyRotaService rotaService;
    @RequestMapping("/algorithm/getFreeMatrix")
    public ObjectMessage getFreeBydep(@RequestParam long userId) {
        ObjectMessage message = new ObjectMessage();
        message.code = ResultCode.Companion.getSUCCESS();
        message.result = ResultCode.Companion.getMap().get(message.code);
        message.object = rotaService.getFreeByUserId(userId);
        return message;
    }

    @RequestMapping("/algorithm/getStudent")
    public ObjectMessage getStudentBydep(@RequestParam long depId) {
        ObjectMessage message = new ObjectMessage();
        message.code = ResultCode.Companion.getSUCCESS();
        message.result = ResultCode.Companion.getMap().get(message.code);
        message.object = rotaService.getStudentsByDepId(depId);
        return message;
    }

    @RequestMapping("/algorithm/getAttendance")
    public ObjectMessage getAttendanceBydep(@RequestParam long depId) {
    //public ObjectMessage getStudentBydep(@RequestBody long depId, @RequestBody List<List<Integer>> needs) {
        ObjectMessage message = new ObjectMessage();
        message.code = ResultCode.Companion.getSUCCESS();
        message.result = ResultCode.Companion.getMap().get(message.code);
        List<List<Integer>> needs = new ArrayList<>();
        for(int i=0;i<5;++i) {
            List<Integer> list = new ArrayList<>();
            list.add(0); list.add(0); list.add(1); list.add(1);
            needs.add(list);
        }

        message.object = rotaService.getDepAttendance(depId,needs);
        return message;
    }



}

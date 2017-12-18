package com.zsq.service;
import com.zsq.model.Attendance;
import com.zsq.util.*;

import java.util.List;

/**
 * Created by hp on 2017/12/17.
 */
public interface LsyRotaService {
    public int[][][] getFreeByUserId(long userId);
    public Student[] getStudentsByDepId(long depId);
    public List<Attendance> getDepAttendance(long depId,List<List<Integer>> needs);
}

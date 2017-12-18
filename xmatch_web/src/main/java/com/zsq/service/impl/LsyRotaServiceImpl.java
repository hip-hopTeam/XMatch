package com.zsq.service.impl;

import com.zsq.dto.MemberDto;
import com.zsq.model.Attendance;
import com.zsq.model.Course;
import com.zsq.model.CourseRepository;
import com.zsq.model.Department;
import com.zsq.service.DepMemberManagerService;
import com.zsq.service.DepartmentService;
import com.zsq.service.LsyRotaService;
import com.zsq.service.UserService;
import com.zsq.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2017/12/17.
 */
@Service
@EnableTransactionManagement
@Transactional
public class LsyRotaServiceImpl implements LsyRotaService {

    @Autowired
    DepMemberManagerService depMemberManagerService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    LsyRotaService lsyRotaService;
    @Autowired
    UserService userService;


    @Override
    public int[][][] getFreeByUserId(long userId) {
        int freeMatrix[][][];
        freeMatrix = new int[25][][];
        for (int i = 0; i < 25; ++i) freeMatrix[i] = new int[6][];
        for (int i = 0; i < 25; ++i) {
            for (int j = 0; j < 6; ++j) {
                freeMatrix[i][j] = new int[5];
                for(int k=0;k<5;++k) freeMatrix[i][j][k] = 1;
            }
        }

        List<Course> memberCourse = courseRepository.findByUserId(userId);



        for (int k = 0; k < memberCourse.size(); ++k) {
            Course cou = memberCourse.get(k);
            /*
            System.out.println(cou.getName());
            System.out.println("start:"+cou.getStartTime());
            System.out.println("end:"+cou.getEndTime());
            System.out.println("start:"+cou.getStartWeek());
            System.out.println("end:"+cou.getEndWeek());
            System.out.println(cou.isSingle());
            System.out.println(cou.isDouble());
            */
            int day = cou.getWeekend();
            if(!(day>=1&&day<=5)) continue;
            int cnt = 0;
            int tmp[] = new int[10];

            for (int a = (cou.getStartTime() + 1) / 2; a <= Math.min((cou.getEndTime() + 1) / 2,4); a++) {
                tmp[cnt++] = a; System.out.println("a:"+a);
            }

            int startWeek = cou.getStartWeek();
            int endWeek = cou.getEndWeek();
            for (int j = startWeek; j <= endWeek; ++j) {
                //if ((j % 2 == 0 && cou.isSingle()) || (j % 2 == 1 && cou.isDouble())) continue; //课程只在单周或只在双周
                //获取空闲时间矩阵 matrix[周数][星期][节数]
                for (int b = 0; b < cnt; ++b) {
                    freeMatrix[j][day][tmp[b]] = 0;
                }
            }
        }


        return freeMatrix;
    }

    @Override
    public Student[] getStudentsByDepId(long depId) {
        List<MemberDto> depMembers = depMemberManagerService.getDepMember(depId, 2, 0, 100000);
        int n = depMembers.size();
        Student[] staff = new Student[n+1];
        int freeMatrix[][][];
        for(int i=0;i<n;++i) {
            long userId = depMembers.get(i).getUserId();
            freeMatrix = getFreeByUserId(userId);
            staff[i+1] = new Student(userId,freeMatrix);
        }

        /*for(int i=1;i<=n;++i) {int [][][]cnt =staff[i].getMatrix();
            for(int j=0;j<25;++j) {
                for(int k=0;k<6;++k) {
                    for(int a=0;a<5;++a) {
                        System.out.print(cnt[j][k][a]+" ");
                    }
                }
            }System.out.println();
        }*/
        return staff;
    }

    @Override
    public List<Attendance> getDepAttendance(long depId, List<List<Integer>> needs) {
        RotaAlgorithm rotaAlgorithm=new RotaAlgorithm();
        List<Department> departments=departmentService.getAllDepartments(0,10);
        System.out.println(departments.size());

        int n = departmentService.getDepartment(depId).getMemberNum();
        System.out.println(needs.size());
        int needMatrix[][] =rotaAlgorithm.readIn(depId,needs);
        Student[] staff = lsyRotaService.getStudentsByDepId(depId);

        //RotaAlgorithm.Rota table = new RotaAlgorithm.Rota();
        //RotaAlgorithm.NetFlow maxflow = new RotaAlgorithm.NetFlow();
        List<Attendance> result = rotaAlgorithm.getResult(n,depId,needMatrix,staff,userService);



        return result;
    }
}

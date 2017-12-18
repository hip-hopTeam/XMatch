package com.zsq;

import com.zsq.model.Department;
import com.zsq.service.DepartmentService;
import com.zsq.service.LsyRotaService;
import com.zsq.service.UserService;
import com.zsq.util.*;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderQiang
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.zsq.controller","com.zsq.service","com.zsq.config","com.zsq.model"} )
public class XmatchApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(XmatchApplication.class, args);
        DepartmentService departmentService=context.getBean(DepartmentService.class);
        LsyRotaService lsyRotaService=context.getBean(LsyRotaService.class);
        UserService userService=context.getBean(UserService.class);

    }

}

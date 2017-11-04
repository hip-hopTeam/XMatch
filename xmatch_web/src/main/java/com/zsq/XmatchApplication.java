package com.zsq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.zsq.controller","com.zsq.service"} )
public class XmatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmatchApplication.class, args);
	}
}

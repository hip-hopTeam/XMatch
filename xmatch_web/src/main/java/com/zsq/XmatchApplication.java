package com.zsq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CoderQiang
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.zsq.controller","com.zsq.service","com.zsq.config"} )
public class XmatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmatchApplication.class, args);
	}
}

package com.candidatemobileapi;
/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidateMobileSpringBootMain is a class to bootstrap the Application
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CandidateMobileSpringBootMain {
	
	@Bean
	public Logger log() {
	   return LoggerFactory.getLogger(CandidateMobileSpringBootMain.class);
	}
	
	public static void main(String[] args) {
		 SpringApplication.run(CandidateMobileSpringBootMain.class, args);
	}


}
	
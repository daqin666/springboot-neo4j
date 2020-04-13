package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


@EnableNeo4jRepositories
//@EnableNeo4jRepositories("com.repository")  //加不加具体包都可以
@EntityScan(basePackages="com.domain") //必须指定domain的具体包
@SpringBootApplication
public class StartNeo4jApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(StartNeo4jApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		 return builder.sources(StartNeo4jApplication.class);
	}

}
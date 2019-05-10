package com.kostenko.andrey.testconcord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:ApplicationContext.xml"})
public class TestConcordApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =
		SpringApplication.run(TestConcordApplication.class, args);
	}

}

package io.gtech.empmanager.empmanager;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmpManagerApplication {
	
	@Value("${special.data}")
	private String specialData;
	
	@Value("${special.simple}")
	private String specialSimple;

	public static void main(String[] args) {
		SpringApplication.run(EmpManagerApplication.class, args);
	}
	
	@Bean
	public void simpleInit() {
		System.out.println("***********************special.data: " + specialData);
		System.out.println("***********************special.simple: " + specialSimple);
	}

}

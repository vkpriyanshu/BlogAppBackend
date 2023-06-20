package com.vinni.blog;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vinni.blog.security.CustomUserDetailService;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@SpringBootApplication(scanBasePackages= {
//        "com.vinni.blog"
//})
@SpringBootApplication
//@EnableJpaRepositories
@EntityScan(basePackages = {"com.vinni.blog.entities"})
public class BlogAppApisApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	public CustomUserDetailService customUserDetailService() {
//		return new CustomUserDetailService();
//	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("xyz" + this.passwordEncoder.encode("xyz"));
		
	}

}

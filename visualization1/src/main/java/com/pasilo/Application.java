package com.pasilo;

import com.pasilo.bean.User;
import com.pasilo.dao.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pasilo.dao")
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

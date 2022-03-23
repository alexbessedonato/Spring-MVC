package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.domain.MyRole;
import com.example.domain.MyUser;
import com.example.repository.MyUserRepository;

@SpringBootApplication
public class Lab1S2ExAApplication implements ApplicationRunner {

	@Autowired
	private MyUserRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(Lab1S2ExAApplication.class, args);
	}

	@Autowired
	private PasswordEncoder pe;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		MyUser u1 = new MyUser();
		u1.setName("guest");
		u1.setPassword(pe.encode("password"));
		
		MyRole r1 = new MyRole();
		r1.setName("GUEST");
		
		MyRole r2 = new MyRole();
		r2.setName("PREMIUM");
		
		u1.getRoles().add(r1);
		u1.getRoles().add(r2);
		
		repo.save(u1);
	}

}

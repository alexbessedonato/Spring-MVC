package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.MyUser;

public interface MyUserRepository extends CrudRepository<MyUser, String> {
	public MyUser findByName(String name);
}

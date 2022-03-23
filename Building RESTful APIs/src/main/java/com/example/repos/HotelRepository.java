package com.example.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {
	public List<Hotel> findAll();
	public void deleteById(Integer id);
}

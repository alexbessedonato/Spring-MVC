package com.example;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.Hotel;
import com.example.domain.Room;
import com.example.repos.HotelRepository;

@SpringBootApplication
public class Lab6S2ApiApplication implements ApplicationRunner {

	@Autowired
	private HotelRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Lab6S2ApiApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Hotel h = new Hotel();
		h.setName("Carlton");
		h.setDescription("A nice hotel");

		h = repo.save(h);

		Room r1 = new Room();
		r1.setName("Penthouse");
		r1.setCategory("Good");
		r1.setGuests(5);

		Room r2 = new Room();
		r2.setName("Budget");
		r2.setCategory("Poor");
		r2.setGuests(2);

		h.setRooms(new ArrayList<>());
		h.getRooms().add(r1);
		h.getRooms().add(r2);
		
		h = repo.save(h);

	}


}

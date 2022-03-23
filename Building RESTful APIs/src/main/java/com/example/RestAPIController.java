package com.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.Hotel;
import com.example.repos.HotelRepository;

@RestController
@RequestMapping("/api")
public class RestAPIController {

	public static final Logger logger = LoggerFactory.getLogger(RestAPIController.class);

	@Autowired
	HotelRepository repo;

	/*
	 * Retrieve single hotel
	 */
	@GetMapping("/hotels/{id}")
	public ResponseEntity<?> getHotel(@PathVariable("id") int id) {
		
		if (repo.findById(id).isPresent()) {
			Hotel hotel = repo.findById(id).get();
			return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
		} else
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("Hotel with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
	}

	/*
	 * Retrieve all hotels
	 */
	@GetMapping("/hotels")
	public ResponseEntity<List<Hotel>> listAllHotels() {
		List<Hotel> hotels = repo.findAll();
		if (hotels.isEmpty()) {
			return new ResponseEntity<List<Hotel>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}

	/*
	 * Create a hotel
	 */
	@PostMapping("/hotels")
	public ResponseEntity<?> createHotel(@RequestBody Hotel hotel, UriComponentsBuilder ucBuilder) {

		if (repo.existsById(hotel.getId())) {
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("A hotel named " + hotel.getName() + " already exists."),
					HttpStatus.CONFLICT);
		}
		repo.save(hotel);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/hotels/{id}").buildAndExpand(hotel.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/*
	 * Update a hotel
	 */
	@PutMapping("/hotels/{id}")
	public ResponseEntity<?> updateHotel(@PathVariable("id") int id, @RequestBody Hotel newHotel) {

		if (repo.findById(id).isPresent()) {
			Hotel currentHotel = repo.findById(id).get();
			currentHotel.setName(newHotel.getName());
			currentHotel.setDescription(newHotel.getDescription());
			
			currentHotel.getRooms().clear();
			currentHotel.getRooms().addAll(newHotel.getRooms());

			repo.save(currentHotel);
			return new ResponseEntity<Hotel>(currentHotel, HttpStatus.OK);
		} else
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("Hotel with id " + id + " not found."),
					HttpStatus.NOT_FOUND);

	}
	
	/*
	 * Delete a hotel
	 */
	@DeleteMapping("/hotels/{id}")
	public ResponseEntity<?> deleteHotel(@PathVariable("id") int id) {

		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return ResponseEntity.ok(null);
		} else
			return new ResponseEntity<ErrorInfo>(new ErrorInfo("Hotel with id " + id + " not found."),
					HttpStatus.NOT_FOUND);

	}
	
	/*
	 * Delete all hotels
	 */
	@DeleteMapping("/hotels")
	public ResponseEntity<?> deleteHotels() {

		repo.deleteAll();
		return ResponseEntity.ok(null);	
	}
}

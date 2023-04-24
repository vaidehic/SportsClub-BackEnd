package com.cybage.controller;

import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cybage.dto.ManagerDto;
import com.cybage.entity.Sports;
import com.cybage.exception.ResourceNotFoundException;
import com.cybage.exception.SportsServiceException;
import com.cybage.entity.Users;
import com.cybage.services.SportsServiceImpl;
import com.cybage.services.UsersService;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	SportsServiceImpl sportsServiceImpl;

	@Autowired
	UsersService userService;


	//get list of sports
	@GetMapping("/sport")
	public ResponseEntity<List<Sports>> getSports() {
		List<Sports> listSport=sportsServiceImpl.getSports();
		return ResponseEntity.status(HttpStatus.OK).body(listSport);
	}

	//get List Of Sports For Manager
	@GetMapping ("/SportForManager")
	public ResponseEntity<List<Sports>> getSportsForManager(){
		List<Sports> listSport=sportsServiceImpl.getSportsForManager();
		if(listSport.isEmpty())
			throw new ResourceNotFoundException("All Sports are Assigned ");
		else
			return ResponseEntity.status(HttpStatus.OK).body(listSport);
	}

	//get sport by id
	@GetMapping("/sports/{sportId}")
	public ResponseEntity<Sports> findById(@PathVariable int sportId) {
		Sports sport=sportsServiceImpl.getSportById(sportId);
		if(sport==null)
			throw new ResourceNotFoundException("No Sports available");
		else
			return ResponseEntity.status(HttpStatus.OK).body(sport);
	}

	//Add sport entity (with image)
	@PostMapping(path = "/image")
	public ResponseEntity<Object> addSportWithImage(
			@RequestPart("sportName") String sportName,
			@RequestPart("file") MultipartFile file )
	{
		Sports sport = new Sports();
		try {
			sport.setSportImage(file.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}
		sport.setSportName(sportName);
		sportsServiceImpl.addSport(sport);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	//delete sport
	@DeleteMapping("/{sportId}")
	public ResponseEntity<Integer> deleteSport(@PathVariable int sportId)throws SportsServiceException{
		Sports sport=sportsServiceImpl.getSportById(sportId);
		if(sport==null)
			return ResponseEntity.notFound().build();
		sportsServiceImpl.deleteSport(sportId);
		return ResponseEntity.ok(1);
	}


	//Update sport by id
	@PutMapping("/sport/{id}")
	public ResponseEntity<Object> updateSport(
			@RequestPart("sportName") String sportName,
			@RequestPart("file") MultipartFile file,
			@PathVariable int id)
					throws ResourceNotFoundException, IOException{
		Sports updatedSport=sportsServiceImpl.getSportById(id);
		if(updatedSport==null)
			return ResponseEntity.notFound().build();
		else{
			updatedSport.setSportImage(file.getBytes());
			updatedSport.setSportName(sportName);
			return new ResponseEntity<>(sportsServiceImpl.updateSport(updatedSport),HttpStatus.OK);
		}
	}

	//get list of Manager
	@GetMapping("/managers")
	public ResponseEntity<List<ManagerDto>> getAllManagers() {
		List<ManagerDto> listManagers=userService.getManagers();
		if(listManagers.isEmpty())
			throw new ResourceNotFoundException("No Managers available");
		else
			return ResponseEntity.status(HttpStatus.OK).body(listManagers);
	}

	//update Manager
	@PutMapping("/updatemanager/{managerId}/{sportId}")
	public ResponseEntity<Object> updateManager(@PathVariable int managerId,@PathVariable int sportId ,@RequestBody Users user) {
		return new ResponseEntity<>(userService.updateManager(managerId, sportId, user),HttpStatus.OK);
	}
	
	//Add user with  manager role 
	@PostMapping("/addmanager/{id}")
	public ResponseEntity<Object> addManager(@RequestBody Users user,@PathVariable int id) throws AddressException, MessagingException {
		return new ResponseEntity<>(userService.addManager(user,id),HttpStatus.OK);
	}

	//get Manager by id
	@GetMapping("/manager/{managerId}")
	public ResponseEntity<Users> findManagerById(@PathVariable int managerId) {
		Users user=userService.getManagerById(managerId);
		if(user==null)
			throw new ResourceNotFoundException("No user available");
		else
			return ResponseEntity.status(HttpStatus.OK).body(user);
	}



	//delete manager by id
	@DeleteMapping("/deletemanager/{id}")
	public ResponseEntity<Object> deleteManger(@PathVariable int id)  throws ResourceNotFoundException
	{
		return new ResponseEntity<>(userService.deleteManger(id),HttpStatus.OK);
	}

	// unlocking user
	@PutMapping("/unlock")
	public ResponseEntity<Users> unlockAccount(@RequestBody Users user)
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.unlockAccount(user.getEmail(),user.getPassword()));
	}

	//Request for Unlocking account
	@GetMapping("/requestUnlock")
	public ResponseEntity<List<Users>> getUserByRequestForUnlock() {
		List<Users> users=userService.getUserByRequestForUnlock();
		if(users.isEmpty())
			throw new ResourceNotFoundException("There is no any request for unlocking account");
		else
			return ResponseEntity.status(HttpStatus.OK).body(users);
	}
}

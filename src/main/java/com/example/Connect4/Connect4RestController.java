package com.example.Connect4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Connect4RestController {
	//TODO connect to DB
//	private final EmployeeRepository repository;
//
//	Connect4RestController(EmployeeRepository repository) {
//		this.repository = repository;
//	}
	@RequestMapping(value = "/START", method = RequestMethod.GET)
	@ResponseBody
	public String startTheGame() {
		Connect4GamePlay.reset();
		return "Ready";
	}

	@PutMapping("/{id}")
	public String play(@PathVariable int id) {
		switch (Connect4GamePlay.playAturn(id)) {
		case -1:
			return "Invalid";

		case 2:
			return "Red Wins";

		case 3:
			return "Yellow wins";

		default:
			return "Valid";
		}
	}



}

package com.example.demo;

import java.util.logging.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String password) {
		DatabaseHandler handler = new DatabaseHandler();
		handler.Connect();
		handler.InsertUser(username, password);
		return "saved";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody String All() {
		DatabaseHandler dbusers = new DatabaseHandler();
		dbusers.Connect();
		return dbusers.AllUsers().toString();
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public boolean authenticate(@RequestParam String username, @RequestParam String password) {
		DatabaseHandler dbHandler = new DatabaseHandler();
		dbHandler.Connect();
		boolean auth = dbHandler.Authenticate(username, password);
		return auth;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public @ResponseBody String messages(@RequestParam String message) {
		DatabaseHandler handler = new DatabaseHandler();
		handler.Connect();
		handler.send(message);
		return message;
	}
	
	@RequestMapping(value = "/recieve",method = RequestMethod.GET)
	public @ResponseBody String recieving() {
		DatabaseHandler handler = new DatabaseHandler();
		handler.Connect();
		return handler.recieve().toString();
	}
	
}

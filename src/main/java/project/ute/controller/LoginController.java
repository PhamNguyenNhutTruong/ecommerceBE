package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import project.ute.dto.MessageDto;
import project.ute.model.User;
import project.ute.sbjwt.service.JwtService;
import project.ute.service.LoginService;
import project.ute.service.UsersService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
	@Autowired
	UsersService usersService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> login(HttpServletRequest request, @RequestParam("to-email") String toMail,
			@RequestParam("password") String password) {
		MessageDto messageDto = loginService.handleLogin(toMail, password);
		return ResponseEntity.status(HttpStatus.OK).body(messageDto);
	}
}

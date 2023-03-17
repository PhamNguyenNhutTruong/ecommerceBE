package project.ute.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.ute.dto.MessageDto;
import project.ute.model.User;
import project.ute.service.SignUpService;
import project.ute.util.ConstantUtils;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class SignUpController {
	String email = null;
	String pass = null;
	
	@Autowired
	SignUpService signUpService;
	
	@RequestMapping(value = "/sign-up-account", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> sendMail(HttpServletResponse response, @RequestParam("to-email") String toMail,
			@RequestParam("password") String password) throws IOException {
		
		MessageDto messageDto = signUpService.checkContionSignUp(toMail, password);
		try {
			if(messageDto.getStatus() == ConstantUtils.SUCCESS) {
				email = toMail;
				pass = password;
				signUpService.sendEmail(email, response);
			} 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(messageDto);
	}
	
	
	@RequestMapping(value = "/sign-up-account/verify-account", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> verifyMail(@RequestParam("otp-code") String otpCode, HttpServletRequest request) throws IOException {
		MessageDto messageDto = signUpService.signUpAccount(email, pass, 1, otpCode, request);
		return ResponseEntity.status(HttpStatus.OK).body(messageDto);
	}
}

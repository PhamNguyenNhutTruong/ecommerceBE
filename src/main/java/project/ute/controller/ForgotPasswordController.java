package project.ute.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;
import project.ute.service.ForgotPasswordService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ForgotPasswordController {
	@Autowired
	ForgotPasswordService forgotPasswordService;

	@RequestMapping(value = "/login/forgot-password", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> uploadImage(@RequestParam("email") String email) throws IOException, MessagingException {
		MessageDto messageDto = forgotPasswordService.checkEmailBeforeResetPassword(email);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}

	@RequestMapping(value = "/login/forgot-password/reset-password-form/{email}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<?> getFormResetPassword(@PathVariable("email") String email) {
		String contentHtml = forgotPasswordService.formResetPassword(email);
		return ResponseEntity.status(HttpStatus.OK).body(contentHtml);
	}
	
	@RequestMapping(value = "/login/forgot-password/reset-password", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> setResetPassword(@RequestBody AccountDto accountDto) {
		MessageDto messageDto = forgotPasswordService.setResetPassword(accountDto);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
}

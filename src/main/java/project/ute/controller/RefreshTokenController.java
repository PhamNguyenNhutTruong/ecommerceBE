package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;
import project.ute.service.RefreshTokenService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RefreshTokenController {
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@RequestMapping(value = "/refresh-token", method = RequestMethod.POST)
	public ResponseEntity<?> refreshToken(HttpServletRequest request,  @RequestBody AccountDto accountDto) {		
		MessageDto messageDto = refreshTokenService.handleRefreshToken(request, accountDto);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
	
	
	@RequestMapping(value = "/delete-token", method = RequestMethod.POST)
	public ResponseEntity<?> deleteToken(HttpServletRequest request,  @RequestBody AccountDto accountDto) {		
		MessageDto messageDto = refreshTokenService.handleDeleteToken(request);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
}

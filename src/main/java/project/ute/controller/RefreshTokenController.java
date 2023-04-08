package project.ute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
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
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(HttpServletRequest request,  @RequestBody AccountDto accountDto, @RequestHeader MultiValueMap<String, String> headers ) {		
		System.out.println("Refresh token");
		headers.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
		MessageDto messageDto = refreshTokenService.handleRefreshToken(request, accountDto);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
}

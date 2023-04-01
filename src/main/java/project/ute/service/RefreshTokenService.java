package project.ute.service;

import org.springframework.lang.NonNull;

import jakarta.servlet.http.HttpServletRequest;
import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;

public interface RefreshTokenService {
	public MessageDto handleRefreshToken(@NonNull HttpServletRequest request, AccountDto accountDto);
}

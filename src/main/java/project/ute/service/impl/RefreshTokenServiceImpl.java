package project.ute.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;
import project.ute.dto.TokenDto;
import project.ute.sbjwt.service.JwtService;
import project.ute.service.RefreshTokenService;
import project.ute.util.ConstantUtils;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
	@Autowired
	JwtService jwtService;
	
	
	@Override
	public MessageDto handleRefreshToken(@NonNull HttpServletRequest request, AccountDto accountDto) { 
		String actionGen = ConstantUtils.GEN_TOKEN;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String tokenOld = httpRequest.getHeader(ConstantUtils.TOKEN_HEADER);
		String refreshToken = httpRequest.getHeader(ConstantUtils.TOKEN_HEADER_REFRESH);
		
		/*
		  	- Đầu tiên kiểm tra xem Refresh Token hết hạn hay chưa
		  		+ Nếu chưa hết hạn -> Cho tạo Token mới
		  		+ Nếu hết hạn --> Yêu cầu đăng nhâp.
		 */
		TokenDto tokenDto = new TokenDto();
//		Nếu token còn hạn sử dụng -> Trả về thông báo
		if(!jwtService.isTokenExpired(tokenOld)) {
			tokenDto.setAccessToken(tokenOld);
			tokenDto.setRefreshToken(refreshToken);
			return new MessageDto("Refresh Token", "Token unexpired", ConstantUtils.WRANING, accountDto.getEmail(), tokenDto, HttpStatus.BAD_REQUEST);
		}
		
//		Nếu Refresh Token không hết hạn.
		if(!jwtService.isTokenExpired(refreshToken)) {
			String tokenNew = jwtService.generateTokenLogin(accountDto.getEmail(), actionGen);
			
			tokenDto.setAccessToken(tokenNew);
			tokenDto.setRefreshToken(refreshToken);
			
			return new MessageDto("Refresh Token", "Refresh Token Successful", ConstantUtils.SUCCESS, accountDto.getEmail(), tokenDto, HttpStatus.OK);
		}		
		return new MessageDto("Refresh Token", "Refresh Token Failed - Refresh token expired - Please login again", ConstantUtils.ERROR, accountDto.getEmail(), null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

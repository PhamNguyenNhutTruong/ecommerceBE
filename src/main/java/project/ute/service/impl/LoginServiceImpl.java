package project.ute.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.dto.MessageDto;
import project.ute.model.User;
import project.ute.sbjwt.service.JwtService;
import project.ute.service.LoginService;
import project.ute.service.SignUpService;
import project.ute.service.UsersService;
import project.ute.util.BcryptUtils;
import project.ute.util.ConstantUtils;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	UsersService usersService;
	
	@Autowired
	SignUpService signUpService;
	
	@Autowired
	JwtService jwtService;

	@Override
	public MessageDto handleLogin(String email, String password) {
		MessageDto messageDto = signUpService.checkContionSignUp(email, password);
		
		if(messageDto.getStatus() == ConstantUtils.SUCCESS) {
			Optional<User> user = usersService.loadUserByEmail(email);
			if(!user.isEmpty()) {
				String passwordHash = user.get().getPassword();
				if(BcryptUtils.checkpwd(password, passwordHash)) {
					String token = jwtService.generateTokenLogin(user.get().getEmail());
					return new MessageDto("Login", token, ConstantUtils.SUCCESS);
				} else {
					return new MessageDto("Login", "Incorrect password", ConstantUtils.ERROR);
				}
			} else {
				return new MessageDto("Login", "Account does not exist", ConstantUtils.ERROR);
			}
		} else {
			return messageDto;
		}
	}
}

package project.ute.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;
import project.ute.dto.TokenDto;
import project.ute.model.Customer;
import project.ute.model.User;
import project.ute.sbjwt.service.JwtService;
import project.ute.service.CustomerService;
import project.ute.service.LoginService;
import project.ute.service.SignUpService;
import project.ute.service.UsersService;
import project.ute.util.BcryptUtils;
import project.ute.util.ConstantUtils;

@Service
public class LoginServiceImpl implements LoginService{
	public int role = -1;
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Autowired
	UsersService usersService;
	
	@Autowired
	SignUpService signUpService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	CustomerService customerService;

	@Override
	public MessageDto handleLogin(AccountDto accountDto) {
		String actionGen = ConstantUtils.GEN_TOKEN;
		String actionRef = ConstantUtils.REF_TOKEN;
		this.setRole(accountDto.getRole());
//		Kiểm tra nếu là đăng nhập bằng google
		if (accountDto.isGoogleLogin()) {
//			Nếu role == 1 --> vai trò khách hàng
			if(accountDto.getRole() == 1) {
				Optional<Customer> customer = customerService.checkCustomerAccount(accountDto.getEmail());
//				Nếu email đó đã được đăng kí
				if(!customer.isEmpty()) {
//					Kiểm tra nếu đúng mật khẩu thì cho đăng nhập
					if(BcryptUtils.checkpwd(accountDto.getPassword(), customer.get().getPassword())) {
						String token = jwtService.generateTokenLogin(customer.get().getEmail(), actionGen);
						String refreshToken = jwtService.generateTokenLogin(customer.get().getEmail(), actionRef);
						
						TokenDto tokenDto = new TokenDto();
						tokenDto.setAccessToken(token);
						tokenDto.setRefreshToken(refreshToken);
						return new MessageDto("Google Login", "Login successful", ConstantUtils.SUCCESS ,accountDto.getEmail(), tokenDto, HttpStatus.OK);
					}
				} 
//				Nếu email đó chưa được đăng kí
				else {
//					Tạo ra tài khoản mới
					Customer customerAccount = new Customer();
					customerAccount.setId(customerService.randomCustomerId());
					customerAccount.setEmail(accountDto.getEmail());
					customerAccount.setPassword(BcryptUtils.hashpwd(accountDto.getPassword()));
					customerAccount.setName(accountDto.getEmail());
					customerAccount.setFamilyName("");
					customerAccount.setGivenName("");
					customerAccount.setIsGoogleLogin(true);
					customerAccount.setPhonenumber("");
					customerAccount.setPicture("");
					customerAccount.setVerifiedEmail("");
					
					customerService.addNewCutomer(customerAccount);
//					Cho pass qua đăng nhập
					String token = jwtService.generateTokenLogin(accountDto.getEmail(), actionGen);
					String refreshToken = jwtService.generateTokenLogin(customer.get().getEmail(), actionRef);
					
					TokenDto tokenDto = new TokenDto();
					tokenDto.setAccessToken(token);
					tokenDto.setRefreshToken(refreshToken);
					return new MessageDto("Google Login", "Login successful", ConstantUtils.SUCCESS ,accountDto.getEmail(), tokenDto, HttpStatus.OK);
				}
			} 
		} 
		
//		Kiểm tra nếu không phải đăng nhập bằng google
		MessageDto messageDto = signUpService.checkContionSignUp(accountDto.getEmail(), accountDto.getPassword());
		if(messageDto.getStatus() == ConstantUtils.SUCCESS) {
			Optional<User> user = usersService.loadUserByEmail(accountDto.getEmail());
			if(!user.isEmpty()) {
				String passwordHash = user.get().getPassword();
				if(BcryptUtils.checkpwd(accountDto.getPassword(), passwordHash)) {
					String token = jwtService.generateTokenLogin(user.get().getEmail(), actionGen);
					String refreshToken = jwtService.generateTokenLogin(user.get().getEmail(), actionRef);
					
					TokenDto tokenDto = new TokenDto();
					tokenDto.setAccessToken(token);
					tokenDto.setRefreshToken(refreshToken);
					return new MessageDto("Login", "Login successful", ConstantUtils.SUCCESS ,accountDto.getEmail(), tokenDto, HttpStatus.OK);
				} else {
					return new MessageDto("Login", "Incorrect password", ConstantUtils.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new MessageDto("Login", "Account does not exist", ConstantUtils.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return messageDto;
		}
	}
}

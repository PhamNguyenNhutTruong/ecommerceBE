package project.ute.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;
import project.ute.model.User;
import project.ute.sbjwt.service.JwtService;
import project.ute.service.LoginService;
import project.ute.service.UsersService;
import project.ute.util.ConstantUtils;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UsersService userService;
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(HttpServletRequest request, @RequestBody AccountDto accountDto) {		
		/*
		 Object được đưa xuống có dạng: {"email":"20110756@student.hcmute.edu.vn","password" : "pnnt19062002", "role": 0/1, "isGoogleLogin": true/false}
		 Kiểm tra nếu is_google_login
		 - Là true: là đăng nhập bằng google
		 - Là false:  là đăng nhập bằng tay
		 
		 Kiểm tra role
		 - Nếu role là 1 thì lưu vào bảng customer
		 - Nếu role là 0 thù lưu vào bảng user
		 
		 Kiểm tra tài khoản
		 - Nếu chưa có thì tạo tài khoản mới
		 - Nếu đã có tài khoản thì cho đăng nhập và tạo token cho lần đăng nhập đó.
		 */
		MessageDto messageDto = loginService.handleLogin(accountDto);
		return ResponseEntity.status(messageDto.getHttpStatus()).body(messageDto);
	}
}

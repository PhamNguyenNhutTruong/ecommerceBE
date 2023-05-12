package project.ute.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.ute.dto.MessageDto;
import project.ute.model.Customer;
import project.ute.service.CustomerService;
import project.ute.service.SignUpService;
import project.ute.service.UsersService;
import project.ute.util.BcryptUtils;
import project.ute.util.ConstantUtils;
import project.ute.util.PasswordUtils;
import project.ute.util.RandomNumber;

import org.springframework.mail.javamail.MimeMessageHelper;;

@Service
public class SignUpServiceImpl implements SignUpService{
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private PasswordUtils passwordUtils;
	
	@Autowired
	RandomNumber randomOTPCode;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public void sendEmail(String toEmail, HttpServletResponse response) throws MessagingException, UnsupportedEncodingException{		
		String subject = "Code to verify your email";
		String otpCode = randomOTPCode.randomOTPCode();
		String toMailEncoder = URLEncoder.encode(toEmail, "UTF-8");
		
		String htmlContent = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\r\n"
				+ "  <div style=\"margin:50px auto;width:70%;padding:20px 0\">\r\n"
				+ "    <div style=\"border-bottom:1px solid #eee\">\r\n"
				+ "      <a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">TAT Coffee</a>\r\n"
				+ "    </div>\r\n"
				+ "    <p style=\"font-size:1.1em\">Hi,</p>\r\n"
				+ "    <p>Thank you for choosing TAT Coffee. Use the following OTP to complete your Sign Up procedures. OTP is valid for 5 minutes</p>\r\n"
				+ "    <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">"+ otpCode +"</h2>\r\n"
				+ "    <p style=\"font-size:0.9em;\">TAT Coffee,<br />Your Brand</p>\r\n"
				+ "    <hr style=\"border:none;border-top:1px solid #eee\" />\r\n"
				+ "    <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">\r\n"
				+ "      <p>TAT Coffee Inc</p>\r\n"
				+ "      <p>No1. Vo Van Ngan Street</p>\r\n"
				+ "      <p>Thu Duc, Ho Chi Minh City</p>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "</div>";
		
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
        
        Cookie cookie = new Cookie(toMailEncoder, otpCode);
        cookie.setMaxAge(5 * 60);
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
	}

	@Override
	public boolean validateEmail(String email) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return Pattern.compile(regexPattern)
			      .matcher(email)
			      .matches();
	}

	@Override
	public MessageDto checkContionSignUp(String email, String password) {
		MessageDto messageDto = new MessageDto("Sign Up Message", "Verify OTP code to complete sign up", ConstantUtils.SUCCESS, email, null, HttpStatus.OK);
		
		if (!this.validateEmail(email)) {
			return new MessageDto("Sign Up Message", "Email Invalid!!!", ConstantUtils.ERROR, email, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(!passwordUtils.checkPasswordStrong(password)) {
			return new MessageDto("Sign Up Message", "Password must be at least 8 characters!!!", ConstantUtils.ERROR, email, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return messageDto;
	}

	@Override
	public MessageDto verifyMailToSignUp(String email, String password, String otpCode, HttpServletRequest request) throws UnsupportedEncodingException {
		try {
			String emailEncode = URLEncoder.encode(email, "UTF-8");
			String otpInCookie = null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(emailEncode)) {
					otpInCookie = cookie.getValue();
				}
			}
			
			if (otpInCookie.equals(otpCode)) {
				return new MessageDto("Verify email", "Verify email successfull", ConstantUtils.SUCCESS, email, null, HttpStatus.OK);
			}
			return new MessageDto("Verify email", "Verify email falied", ConstantUtils.ERROR, email, null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new MessageDto("Verify email", "OTP - Time out", ConstantUtils.ERROR, email, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public MessageDto signUpAccount(String email, String pass, int role, String otpCode, HttpServletRequest request ) throws UnsupportedEncodingException {
		MessageDto messageDto = this.verifyMailToSignUp(email, pass, otpCode, request);
		
		try {
			if(messageDto.getStatus() == ConstantUtils.SUCCESS) {
//				Xác nhận mã OTP  thành công -> Tạo mới 1 user
				Customer customerAccount = new Customer();
				customerAccount.setId(customerService.randomCustomerId());
				customerAccount.setEmail(email);
				customerAccount.setPassword(BcryptUtils.hashpwd(pass));
				customerAccount.setName(email);
				customerAccount.setFamilyName("");
				customerAccount.setGivenName("");
				customerAccount.setIsGoogleLogin(false);
				customerAccount.setPhonenumber("");
				customerAccount.setPicture(null);
				customerAccount.setVerifiedEmail("");
				
				customerService.addNewCutomer(customerAccount);
				
				return new MessageDto("Sign up account", "Sign up successful", ConstantUtils.SUCCESS, email, null, HttpStatus.OK);
			} else {
//				Xác nhận mã OTP thất bại -> Trả về thông báo bị lỗi
				return messageDto;
			}
		} catch (Exception e) {
			return new MessageDto("Sign up account", "Email has already exist", ConstantUtils.ERROR, email, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

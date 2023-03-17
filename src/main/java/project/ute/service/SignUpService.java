package project.ute.service;

import java.io.UnsupportedEncodingException;


import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.ute.dto.MessageDto;

public interface SignUpService {
	public MessageDto checkContionSignUp(String email, String password);
	public void sendEmail(String toEmail, HttpServletResponse response) throws MessagingException, UnsupportedEncodingException;
	public boolean validateEmail(String email);
	public MessageDto verifyMailToSignUp(String email, String password, String otpCode, HttpServletRequest request) throws UnsupportedEncodingException;
	public MessageDto signUpAccount(String email, String pass, int role, String otpCode,HttpServletRequest request) throws UnsupportedEncodingException;
}

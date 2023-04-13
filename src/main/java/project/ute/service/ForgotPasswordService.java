package project.ute.service;

import jakarta.mail.MessagingException;
import project.ute.dto.AccountDto;
import project.ute.dto.MessageDto;

public interface ForgotPasswordService {
	public MessageDto checkEmailBeforeResetPassword(String email) throws MessagingException;
	public void sendMailToForgotPassword(String email) throws MessagingException;
	public String formResetPassword(String email);
	public MessageDto setResetPassword(AccountDto accountDto);
}

package project.ute.service;

import project.ute.dto.MessageDto;
import project.ute.model.User;

public interface LoginService {
	public MessageDto handleLogin(String email, String password);
}

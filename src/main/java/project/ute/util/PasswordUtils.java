package project.ute.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {
	public boolean checkPasswordStrong(String password) {
		return password.length() >= 8;
	}
}

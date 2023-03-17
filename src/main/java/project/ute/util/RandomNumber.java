package project.ute.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomNumber {
	public static String randomOTPCode() {
		String otpCode =  RandomStringUtils.randomNumeric(6);
		return otpCode;
	}
	
	public static String randomId(String type) {
		String id  = type + RandomStringUtils.randomNumeric(6);
		return id;
	}
}

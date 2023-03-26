package project.ute.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import project.ute.model.User;
import project.ute.respository.UserRepository;
import project.ute.service.UsersService;
import project.ute.util.BcryptUtils;
import project.ute.util.RandomNumber;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addUsers(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getUsersById(String id) {
		return userRepository.getUsersById(id);
	}

	@Override
	public String randomUserId() {
		String userId =  null;
		do {
			userId = RandomNumber.randomId("US");
		} while(this.getUsersById(userId).size() > 0);
		return userId;
	}

	@Override
	public Optional<User> loadUserByEmail(String email) {
		return userRepository.getByEmail(email);
	}
}

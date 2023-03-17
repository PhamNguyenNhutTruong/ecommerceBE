package project.ute.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.model.User;
import project.ute.respository.UserRepository;
import project.ute.service.UsersService;
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
	public List<User> getAllUsersById(String id) {
		return userRepository.getAllUsersById(id);
	}

	@Override
	public String randomUserId() {
		String userId =  null;
		do {
			userId = RandomNumber.randomId("US");
//			List<User> users = this.getAllUsersById(userId);
//			if (users.size() <= 0) {
//				return userId;
//			}
		} while(this.getAllUsersById(userId).size() > 0);
		return userId;
	}

}

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
	public List<User> getAllUsersById(String id) {
		return userRepository.getAllUsersById(id);
	}

	@Override
	public String randomUserId() {
		String userId =  null;
		do {
			userId = RandomNumber.randomId("US");
		} while(this.getAllUsersById(userId).size() > 0);
		return userId;
	}

	@Override
	public Optional<User> loadUserByEmail(String email) {
		return userRepository.getByEmail(email);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
		
	}

	@Override
	public boolean checkLogin(User user) {
		List<User> listUser = userRepository.findAll();
		for (User userExist : listUser) {
			if (user.getEmail().equals(userExist.getEmail())
					&& BcryptUtils.checkpwd(user.getPassword(), userExist.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Optional<User> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends User> boolean save(S entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

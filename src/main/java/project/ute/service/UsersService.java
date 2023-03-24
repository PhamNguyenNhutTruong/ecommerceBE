package project.ute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import project.ute.model.User;

public interface UsersService {
	public void addUsers(User user);
	public List<User> getAllUsersById(String id);
	public String randomUserId();
	Optional<User> loadUserByEmail(String email);
	void delete(User entity);
	public boolean checkLogin(User user);
	Optional<User> findById(String id);
	public <S extends User> boolean save(S entity); 
	public void deleteById(String id);
	public List<User> findAll();
}

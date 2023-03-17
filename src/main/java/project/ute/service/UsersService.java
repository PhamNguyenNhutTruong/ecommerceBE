package project.ute.service;

import java.util.List;

import org.springframework.stereotype.Service;

import project.ute.model.User;

public interface UsersService {
	public void addUsers(User user);
	public List<User> getAllUsersById(String id);
	public String randomUserId();
}

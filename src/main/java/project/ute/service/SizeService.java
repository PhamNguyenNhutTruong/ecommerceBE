package project.ute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.model.Size;
import project.ute.respository.SizeRepository;

@Service
public class SizeService {
	@Autowired
	private SizeRepository sizeRepository;
	
	public List<Size> getAllSize() {
		return sizeRepository.findAll();
	}
	
	public List<Size> getSizeByName(String name, long price) {
		return sizeRepository.getSizeByName(name, price);
	}
}

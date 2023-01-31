package project.ute.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import project.ute.model.Size;
import project.ute.respository.SizeRepository;
import project.ute.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService{
	@Autowired
	SizeRepository sizeRepository;
	
	public List<Size> getAllSize() { 
		return sizeRepository.findAll();
	}
	
	public List<Size> getSizeByName(String name, long price) {
		return sizeRepository.getSizeByName(name, price);
	}
	
//	Phân trang
	public List<Size> paging(Optional<Integer> pageNo) {
		PageRequest pageable = PageRequest.of(pageNo.orElse(0), 2);
		Page<Size> page = sizeRepository.findAll(pageable);
		
		return page.getContent();
	}
}

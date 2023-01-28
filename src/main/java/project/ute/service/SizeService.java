package project.ute.service;

import java.util.List;
import java.util.Optional;

import project.ute.model.Size;

public interface SizeService {
	List<Size> getAllSize();
	List<Size> paging(Optional<Integer> pageNo);
	List<Size> getSizeByName(String name, long price);
}

package project.ute.service;

import java.util.List;

import project.ute.dto.LineItemDto;

public interface LineItemService {
	public List<LineItemDto> getAllLineItemByOrderId(String orderId);
}

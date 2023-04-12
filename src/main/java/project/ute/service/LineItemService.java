package project.ute.service;

import java.util.List;

import project.ute.dto.LineItemDto;
import project.ute.model.LineItem;

public interface LineItemService {
	public List<LineItemDto> getAllLineItemByOrderId(String orderId);
	public void addNewLineItem(LineItem lineItem);
}

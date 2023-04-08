package project.ute.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ute.dto.LineItemDto;
import project.ute.mapper.LineItemMapper;
import project.ute.model.LineItem;
import project.ute.respository.LineItemRepository;
import project.ute.service.LineItemService;

@Service
public class LineItemServiceImpl implements LineItemService{
	@Autowired
	LineItemRepository lineItemRepository;

	@Override
	public List<LineItemDto> getAllLineItemByOrderId(String orderId) {
		List<LineItem> lineItems = lineItemRepository.getAllLineItemByOrderId(orderId);
		List<LineItemDto> lineItemDtos = new ArrayList<>();
		
		for(LineItem lineItem : lineItems) {
			lineItemDtos.add(LineItemMapper.toLineItemDto(lineItem));
		}
		return lineItemDtos;
	}

}

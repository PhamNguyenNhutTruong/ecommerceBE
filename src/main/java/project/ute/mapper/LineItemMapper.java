package project.ute.mapper;

import project.ute.dto.LineItemDto;
import project.ute.model.LineItem;

public class LineItemMapper {
	public static LineItemDto toLineItemDto(LineItem lineItem) {
		LineItemDto lineItemDto = new LineItemDto();
		lineItemDto.setId(lineItem.getId());
		lineItemDto.setOrderId(lineItem.getOrder().getId());
		lineItemDto.setAmount(lineItem.getAmount());
		lineItemDto.setQuantity(lineItem.getQuantity());
		lineItemDto.setProductName(lineItem.getProduct().getName());
		
		return lineItemDto;
	}
}

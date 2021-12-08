package com.springboot.eShopper.OrderItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/orderItems")
public class OrderItemController {
	
	private final OrderItemService orderItemService;
	
	@Autowired
	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@GetMapping
	public List<OrderItem> getAllOrderItems() {
		return orderItemService.getAllOrderItems();
	}
	
	@PostMapping
	public void registerNewOrderItem(@RequestBody OrderItem orderItem) {
		orderItemService.addNewOrderItem(orderItem);
	}
	
	@DeleteMapping(path="{orderItemId}")
	public void deleteOrderItem(@PathVariable("orderItemId") Integer orderItemId) {
		orderItemService.deleteOrderItem(orderItemId);
	}
	
	@PutMapping(path="{orderItemId}")
	public void updateOrderItem(@RequestBody OrderItem orderItem, @PathVariable Integer orderItemId) {
		orderItemService.updateOrderItem(orderItem, orderItemId);
	}
}

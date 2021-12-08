package com.springboot.eShopper.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@PostMapping
	public void registerNewOrder(@RequestBody Order order) {
		orderService.addNewOrder(order);
	}
	
	@DeleteMapping(path="{orderId}")
	public void deleteOrder(@PathVariable("orderId") Integer orderId) {
		orderService.deleteOrder(orderId);
	}
	
	@PutMapping(path="{orderId}")
	public void updateOrder(@RequestBody Order order, @PathVariable Integer orderId) {
		orderService.updateOrder(order, orderId);
	}
}

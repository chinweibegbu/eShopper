package com.springboot.eShopper.Order;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public void addNewOrder(Order order) {
		orderRepository.save(order);		
	}

	public void deleteOrder(Integer orderId) {
		// Get order with the given ID
		boolean exists = orderRepository.existsById(orderId);
		
		if(!exists) {
			throw new IllegalStateException("Order with an id of " + orderId + " does not exist");
		}
		
		// Else, save the order
		orderRepository.deleteById(orderId);
	}

	@Transactional
	public void updateOrder(Order order, Integer orderId) {
		boolean exists = orderRepository.existsById(orderId);
		
		if(!exists) {
			throw new IllegalStateException("Order with an id of " + orderId + " does not exist");
		}
		
		orderRepository.save(order);
	}
}

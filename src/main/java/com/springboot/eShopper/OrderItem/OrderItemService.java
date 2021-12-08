package com.springboot.eShopper.OrderItem;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class OrderItemService {
	
	private final OrderItemRepository orderItemRepository;
	
	@Autowired
	public OrderItemService(OrderItemRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}
	
	public List<OrderItem> getAllOrderItems() {
		return orderItemRepository.findAll();
	}

	public void addNewOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);		
	}

	public void deleteOrderItem(Integer orderItemId) {
		// Get orderItem with the given ID
		boolean exists = orderItemRepository.existsById(orderItemId);
		
		if(!exists) {
			throw new IllegalStateException("OrderItem with an id of " + orderItemId + " does not exist");
		}
		
		// Else, save the orderItem
		orderItemRepository.deleteById(orderItemId);
	}

	@Transactional
	public void updateOrderItem(OrderItem orderItem, Integer orderItemId) {
		boolean exists = orderItemRepository.existsById(orderItemId);
		
		if(!exists) {
			throw new IllegalStateException("OrderItem with an id of " + orderItemId + " does not exist");
		}
		
		orderItemRepository.save(orderItem);
	}
}

package com.stacksimplify.restservices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class OrderService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public void createOrder(Long userId, Order order) throws UserNotFoundException {
		User user = this.checkForUser(userId);
		
		order.setUser(user);
		orderRepository.save(order);
	}
	
	public Order getOrderByOrderId(Long orderId, Long userId) throws UserNotFoundException {
		this.checkForUser(userId);
		
		Optional<Order> orderOptional = this.orderRepository.findById(orderId);
		if (!orderOptional.isPresent())
			throw new UserNotFoundException("Order Not Found");
		
		return orderOptional.get();
	}
	
	private User checkForUser(Long userId) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		return userOptional.get();
	}

}

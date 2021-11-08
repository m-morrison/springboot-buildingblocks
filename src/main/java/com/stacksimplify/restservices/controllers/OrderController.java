package com.stacksimplify.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.OrderService;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping("/users")
public class OrderController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	// Get all orders for a user
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) {
		try {
			return userService.getAllOrders(userId);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	//Create Order
	@PostMapping("/{userId}/orders")
	public ResponseEntity<Void> createOrder(@PathVariable Long userId, @RequestBody Order order, UriComponentsBuilder builder) {
		try {
			orderService.createOrder(userId, order);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(order.getOrderId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	// Get Order By Order ID
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderByOrderId(Long orderId, Long userId) {
		try {
			return this.orderService.getOrderByOrderId(orderId, userId);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}

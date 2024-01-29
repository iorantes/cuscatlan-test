package cuscatlan.test.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cuscatlan.test.demo.model.dto.OrderDto;
import cuscatlan.test.demo.services.OrdersService;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@GetMapping
	public ResponseEntity<?> getOrders() {
		return ResponseEntity.ok(ordersService.getOrders());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(ordersService.getOrder(id));
	}

	@PostMapping
	public ResponseEntity<?> saveOrder(@RequestBody OrderDto order) {
		return ResponseEntity.ok(ordersService.saveOrder(order));
	}

	@PutMapping
	public ResponseEntity<?> updateOrder(@RequestBody OrderDto order) {
		return ResponseEntity.ok(ordersService.updateOrder(order));
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable(name = "id") int id) {
		ordersService.deleteOrder(id);
	}

}

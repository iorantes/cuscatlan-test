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

import cuscatlan.test.demo.model.entity.PaymentEntity;
import cuscatlan.test.demo.services.PaymentService;


@RestController
@RequestMapping(value = "/payment")
public class PaymentsController {
	
	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public ResponseEntity<?> getOrders() {
		return ResponseEntity.ok(paymentService.getPayments());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(paymentService.getPayment(id));
	}

	@PostMapping
	public ResponseEntity<?> saveOrder(@RequestBody PaymentEntity payment) {
		return ResponseEntity.ok(paymentService.savePayment(payment));
	}

	@PutMapping
	public ResponseEntity<?> updateOrder(@RequestBody PaymentEntity payment) {
		return ResponseEntity.ok(paymentService.updatePayment(payment));
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable(name = "id") int id) {
		paymentService.deletePayment(id);
	}

}

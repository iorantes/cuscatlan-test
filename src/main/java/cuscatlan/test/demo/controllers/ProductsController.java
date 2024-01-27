package cuscatlan.test.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cuscatlan.test.demo.services.ProductsService;


@RestController
@RequestMapping(value = "/products")
public class ProductsController {
	
	@Autowired
	private ProductsService productsService; 

	@GetMapping
	public ResponseEntity<?> getProducts() {
		return ResponseEntity.ok(productsService.getProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") int idProduct) {
		return ResponseEntity.ok(productsService.getProduct(idProduct));
	}
	
}

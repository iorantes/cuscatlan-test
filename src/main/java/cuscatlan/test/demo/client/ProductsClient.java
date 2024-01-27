package cuscatlan.test.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cuscatlan.test.demo.model.Products;

@FeignClient(value = "products", url = "https://fakestoreapi.com")
public interface ProductsClient {

	@GetMapping("/products")
	List<Products> getProducts();

	@GetMapping("/products/{id}")
	Products getProduct(@PathVariable("id") int idProduct);

}

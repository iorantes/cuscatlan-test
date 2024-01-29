package cuscatlan.test.demo.services;

import java.util.List;

import cuscatlan.test.demo.model.entity.Products;

public interface ProductsService {
	
	public List<Products> getProducts();
	
	public Products getProduct(int idProduct);

}

package cuscatlan.test.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.client.ProductsClient;
import cuscatlan.test.demo.model.Products;
import cuscatlan.test.demo.services.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService{

	@Autowired
	private ProductsClient productsClient;
	
	@Override
	public List<Products> getProducts() {
		List<Products> productsList = productsClient.getProducts();
		return productsList;
	}

	@Override
	public Products getProduct(int idProduct) {
		Products product = productsClient.getProduct(idProduct);
		return product;
	}

}

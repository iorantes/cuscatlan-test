package cuscatlan.test.demo.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import cuscatlan.test.demo.model.entity.Products;

@Component
public class ProductClientFallbackImpl implements FallbackFactory<ProductsClient> {

	@Override
	public ProductsClient create(Throwable cause) {
		return new ProductsClient() {

			@Override
			public List<Products> getProducts() {
				if (cause instanceof Exception) {
					System.out.println("Exception!");
				}
				return new ArrayList<Products>();
			}

			@Override
			public Products getProduct(int idProduct) {
				if (cause instanceof Exception) {
					System.out.println("Exception!");
				}
				return new Products();
			}
		};
	}

}

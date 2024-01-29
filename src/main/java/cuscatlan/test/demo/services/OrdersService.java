package cuscatlan.test.demo.services;

import java.util.List;

import cuscatlan.test.demo.model.dto.OrderDto;
import cuscatlan.test.demo.model.entity.OrdersEntity;

public interface OrdersService {

	public List<OrdersEntity> getOrders();

	public OrdersEntity getOrder(Integer id);

	public OrderDto saveOrder(OrderDto order);

	public OrderDto updateOrder(OrderDto order);

	public void deleteOrder(Integer id);

}

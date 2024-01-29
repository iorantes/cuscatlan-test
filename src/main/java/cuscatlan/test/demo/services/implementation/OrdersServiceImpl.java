package cuscatlan.test.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.config.EntityUtil;
import cuscatlan.test.demo.model.dto.OrderDto;
import cuscatlan.test.demo.model.entity.OrdersEntity;
import cuscatlan.test.demo.repositories.OrdersRepository;
import cuscatlan.test.demo.services.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Override
	public List<OrdersEntity> getOrders() {
		List<OrdersEntity> orderList = ordersRepo.findAll();
		return orderList;
	}

	@Override
	public OrdersEntity getOrder(Integer id) {
		OrdersEntity order = ordersRepo.findById(Long.valueOf(id)).orElse(null);
		return order;
	}

	@Override
	public OrderDto saveOrder(OrderDto order) {
		order.setOrderId(null);
		EntityUtil entityUtil = new EntityUtil();
		OrdersEntity orderSave = entityUtil.dtoToOrdesEntity(order);
		orderSave = ordersRepo.save(orderSave);
		order.setOrderId(orderSave.getOrderId());
		return order;
	}

	@Override
	public OrderDto updateOrder(OrderDto order) {
		EntityUtil entityUtil = new EntityUtil();
		OrdersEntity orderSave = entityUtil.dtoToOrdesEntity(order);
		orderSave = ordersRepo.save(orderSave);
		order.setOrderId(orderSave.getOrderId());
		return order;
	}

	@Override
	public void deleteOrder(Integer id) {
		ordersRepo.deleteById(Long.valueOf(id));
	}

}

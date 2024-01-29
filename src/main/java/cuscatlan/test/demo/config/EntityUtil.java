package cuscatlan.test.demo.config;

import java.sql.Date;

import cuscatlan.test.demo.model.dto.ClientDto;
import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.model.dto.OrderDto;
import cuscatlan.test.demo.model.entity.ClientsEntity;
import cuscatlan.test.demo.model.entity.DetailsEntity;
import cuscatlan.test.demo.model.entity.OrdersEntity;

public class EntityUtil {

	public Date getCurrentDate() {
		Date currentDate = new Date(System.currentTimeMillis());
		return currentDate;
	}

	public Date getDateFromString(String dateString) {
		Date date = Date.valueOf(dateString);
		return date;
	}

	public ClientsEntity dtoToClientsEntity(ClientDto clientDto) {
		ClientsEntity clientsEntity = new ClientsEntity();
		clientsEntity.setClientId(clientDto.getClientId());
		clientsEntity.setName(clientDto.getName());
		clientsEntity.setLastName(clientDto.getLastName());
		clientsEntity.setEmail(clientDto.getEmail());
		clientsEntity.setAddress(clientDto.getAddress());
		clientsEntity.setBirthDate(getDateFromString(clientDto.getBirthDate()));
		clientsEntity.setStatus(clientDto.getStatus());

		return clientsEntity;
	}

	public DetailsEntity dtoToDetailsEntity(DetailDto detailDto) {

		DetailsEntity detailEntity = new DetailsEntity();
		detailEntity.setDetailId(detailDto.getDetailId());

		OrdersEntity ordersEntity = new OrdersEntity();
		ordersEntity.setOrderId(detailDto.getOrder());
		detailEntity.setOrder(ordersEntity);
		detailEntity.setPrice(detailDto.getPrice());
		detailEntity.setProduct(detailDto.getProduct());
		detailEntity.setQuantity(detailDto.getQuantity());

		return detailEntity;

	}

	public OrdersEntity dtoToOrdesEntity(OrderDto orderDto) {

		OrdersEntity orderEntity = new OrdersEntity();
		orderEntity.setOrderId(orderDto.getOrderId());
		orderEntity.setOrderDate(getDateFromString(orderDto.getOrderDate()));
		orderEntity.setTotal(orderDto.getTotal());
		
		ClientsEntity clientEntity = new ClientsEntity();
		clientEntity.setClientId(orderDto.getClient());
		orderEntity.setClient(clientEntity);
		
		return orderEntity;

	}

}

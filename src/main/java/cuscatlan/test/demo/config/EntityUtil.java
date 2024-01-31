package cuscatlan.test.demo.config;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cuscatlan.test.demo.model.dto.ClientDto;
import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.model.dto.OrderDto;
import cuscatlan.test.demo.model.dto.PaymentDto;
import cuscatlan.test.demo.model.entity.ClientsEntity;
import cuscatlan.test.demo.model.entity.DetailsEntity;
import cuscatlan.test.demo.model.entity.OrdersEntity;
import cuscatlan.test.demo.model.entity.PaymentEntity;

public class EntityUtil {

	public Date getCurrentDate() {
		Date currentDate = new Date(System.currentTimeMillis());
		return currentDate;
	}

	public Date getDateFromString(String dateString) {
		Date date = Date.valueOf(dateString);
		return date;
	}

	public String convertDateToString(Date dt) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateToString = df.format(dt);
		return dateToString;
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
		clientsEntity.setPassword(clientDto.getPassword());
		clientsEntity.setRole(clientDto.getRole());

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

	public PaymentEntity dtoToPaymentEntity(PaymentDto paymentDto) {

		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setPaymentId(paymentDto.getPaymentId());
		paymentEntity.setOrder(new OrdersEntity(paymentDto.getOrder()));
		paymentEntity.setPaymentDate(getCurrentDate());
		paymentEntity.setStatus(paymentDto.getStatus());
		paymentEntity.setTotal(paymentDto.getTotal());
		paymentEntity.setVoucher(paymentDto.getVoucher());

		return paymentEntity;

	}

	public boolean validateNumber(Float number) {
		
		if (number >= 0) {
			return true;
		}
		
		return false;
	}
	
}

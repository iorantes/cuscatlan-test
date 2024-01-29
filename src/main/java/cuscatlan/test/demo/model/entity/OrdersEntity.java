package cuscatlan.test.demo.model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Orders")
public class OrdersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	@Column
	private Date orderDate;

	@Column
	private Float total;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "clientId", nullable = false)
	private ClientsEntity client;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public ClientsEntity getClient() {
		return client;
	}

	public void setClient(ClientsEntity client) {
		this.client = client;
	}

	public OrdersEntity() {
		super();
	}

	public OrdersEntity(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public OrdersEntity(Long orderId, Date orderDate, Float total, ClientsEntity client) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.total = total;
		this.client = client;
	}

}

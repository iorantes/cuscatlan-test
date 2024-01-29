package cuscatlan.test.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Details")
public class DetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long detailId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "orderId", nullable = false)
	private OrdersEntity order;

	@Column
	private Integer quantity;

	@Column
	private Float price;

	@Column
	private Integer product;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public OrdersEntity getOrder() {
		return order;
	}

	public void setOrder(OrdersEntity order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

}

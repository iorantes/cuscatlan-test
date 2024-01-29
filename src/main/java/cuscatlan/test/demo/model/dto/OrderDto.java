package cuscatlan.test.demo.model.dto;

public class OrderDto {

	private Long orderId;
	private String orderDate;
	private Float total;
	private Long client;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public OrderDto(Long orderId, String orderDate, Float total, Long client) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.total = total;
		this.client = client;
	}

	public OrderDto() {
		super();
	}

}

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

@Entity(name = "Payment")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "orderId", nullable = false)
	private OrdersEntity order;

	@Column
	private Float total;

	@Column
	private Date paymentDate;

	@Column
	private String voucher;

	@Column
	private String status;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public OrdersEntity getOrder() {
		return order;
	}

	public void setOrder(OrdersEntity order) {
		this.order = order;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

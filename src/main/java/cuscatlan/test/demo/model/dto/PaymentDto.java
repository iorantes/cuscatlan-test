package cuscatlan.test.demo.model.dto;

public class PaymentDto {

	private Long paymentId;
	private Long order;
	private Float total;
	private String voucher;
	private String status;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
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

	public PaymentDto(Long paymentId, Long order, Float total, String voucher, String status) {
		super();
		this.paymentId = paymentId;
		this.order = order;
		this.total = total;
		this.voucher = voucher;
		this.status = status;
	}

	public PaymentDto() {
		super();
	}

}

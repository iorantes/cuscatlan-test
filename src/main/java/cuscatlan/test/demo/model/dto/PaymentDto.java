package cuscatlan.test.demo.model.dto;

public class PaymentDto {

	private Long paymentId;
	private Long order;
	private Float total;
	private String paymentDate;
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

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
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

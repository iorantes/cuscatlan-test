package cuscatlan.test.demo.services;

import java.util.List;

import cuscatlan.test.demo.model.dto.PaymentDto;
import cuscatlan.test.demo.model.entity.PaymentEntity;

public interface PaymentService {

	public List<PaymentEntity> getPayments();

	public PaymentEntity getPayment(Integer id);

	public PaymentDto savePayment(PaymentDto payment);

	public PaymentDto updatePayment(PaymentDto payment);

	public void deletePayment(Integer id);

}

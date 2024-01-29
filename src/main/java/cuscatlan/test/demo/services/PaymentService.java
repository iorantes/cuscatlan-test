package cuscatlan.test.demo.services;

import java.util.List;

import cuscatlan.test.demo.model.entity.PaymentEntity;

public interface PaymentService {

	public List<PaymentEntity> getPayments();

	public PaymentEntity getPayment(Integer id);

	public PaymentEntity savePayment(PaymentEntity payment);

	public PaymentEntity updatePayment(PaymentEntity payment);

	public void deletePayment(Integer id);

}

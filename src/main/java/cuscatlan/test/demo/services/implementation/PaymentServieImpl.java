package cuscatlan.test.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.config.EntityUtil;
import cuscatlan.test.demo.model.dto.PaymentDto;
import cuscatlan.test.demo.model.entity.PaymentEntity;
import cuscatlan.test.demo.repositories.PaymentRepository;
import cuscatlan.test.demo.services.PaymentService;

@Service
public class PaymentServieImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public List<PaymentEntity> getPayments() {
		List<PaymentEntity> paymentList = paymentRepo.findAll();
		return paymentList;
	}

	@Override
	public PaymentEntity getPayment(Integer id) {
		PaymentEntity payment = paymentRepo.findById(Long.valueOf(id)).orElse(null);
		return payment;
	}

	@Override
	public PaymentDto savePayment(PaymentDto payment) {

		payment.setPaymentId(null);
		EntityUtil entityUtil = new EntityUtil();
		PaymentEntity paymentEntity = entityUtil.dtoToPaymentEntity(payment);
		paymentEntity = paymentRepo.save(paymentEntity);
		payment.setPaymentId(paymentEntity.getPaymentId());
		return payment;
	}

	@Override
	public PaymentDto updatePayment(PaymentDto payment) {

		EntityUtil entityUtil = new EntityUtil();
		PaymentEntity paymentEntity = entityUtil.dtoToPaymentEntity(payment);
		paymentEntity = paymentRepo.save(paymentEntity);
		payment.setPaymentId(paymentEntity.getPaymentId());
		return payment;

	}

	@Override
	public void deletePayment(Integer id) {
		paymentRepo.deleteById(Long.valueOf(id));

	}

}

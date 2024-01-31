package cuscatlan.test.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.config.EntityUtil;
import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.model.entity.DetailsEntity;
import cuscatlan.test.demo.model.entity.OrdersEntity;
import cuscatlan.test.demo.repositories.DetailsRepository;
import cuscatlan.test.demo.repositories.OrdersRepository;
import cuscatlan.test.demo.services.DetailsService;
import cuscatlan.test.demo.model.dto.Response;

@Service
public class DetailsServiceImpl implements DetailsService {

	@Autowired
	private DetailsRepository detailsRepo;

	@Autowired
	private OrdersRepository ordersRepo;

	@Override
	public List<DetailsEntity> getDetails() {
		List<DetailsEntity> detailsList = detailsRepo.findAll();
		return detailsList;
	}

	@Override
	public List<DetailsEntity> getDetailByOrder(Integer id) {

		OrdersEntity order = new OrdersEntity(Long.valueOf(id));
		List<DetailsEntity> detailList = detailsRepo.findAllByOrder(order);

		return detailList;
	}

	@Override
	public ResponseEntity<?> saveDetail(DetailDto details) {

		details.setDetailId(null);
		EntityUtil entityUtil = new EntityUtil();
		DetailsEntity detailsSave = entityUtil.dtoToDetailsEntity(details);
		OrdersEntity orderEntity = ordersRepo.findById(detailsSave.getOrder().getOrderId()).orElse(null);

		boolean checkPrice = entityUtil.validateNumber(details.getPrice());
		boolean checkQuantity = entityUtil.validateNumber(Float.valueOf(details.getQuantity()));

		if (checkPrice == false && checkQuantity == false) {
			return new ResponseEntity<Response>(new Response("Error, datos invalidos"), HttpStatus.BAD_REQUEST);
		}

		List<DetailsEntity> detailList = detailsRepo.findAllByOrder(detailsSave.getOrder());
		Float total = 0f;

		for (DetailsEntity detailsEntity : detailList) {
			total = total + (detailsEntity.getPrice() * detailsEntity.getQuantity());
			orderEntity.setTotal(total);
		}

		ordersRepo.save(orderEntity);
		detailsSave = detailsRepo.save(detailsSave);
		details.setDetailId(detailsSave.getDetailId());
		return ResponseEntity.ok(details);
	}

	@Override
	public DetailDto updateDetail(DetailDto details) {
		EntityUtil entityUtil = new EntityUtil();
		DetailsEntity detailsSave = entityUtil.dtoToDetailsEntity(details);
		Long detailId = detailsSave.getDetailId();
		OrdersEntity orderEntity = ordersRepo.findById(detailsSave.getOrder().getOrderId()).orElse(null);

		List<DetailsEntity> detailList = detailsRepo.findAllByOrder(detailsSave.getOrder());
		Float total = 0f;

		for (DetailsEntity detailsEntity : detailList) {
			total = total + (detailsEntity.getPrice() * detailsEntity.getQuantity());
			orderEntity.setTotal(total);
		}

		ordersRepo.save(orderEntity);
		detailsSave.setDetailId(detailId);
		detailsRepo.save(detailsSave);
		details.setDetailId(detailsSave.getDetailId());
		return details;
	}

	@Override
	public void deleteDetail(Integer id) {
		detailsRepo.deleteById(Long.valueOf(id));
	}

}

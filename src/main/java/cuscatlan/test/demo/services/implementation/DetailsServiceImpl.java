package cuscatlan.test.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.config.EntityUtil;
import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.model.entity.DetailsEntity;
import cuscatlan.test.demo.model.entity.OrdersEntity;
import cuscatlan.test.demo.repositories.DetailsRepository;
import cuscatlan.test.demo.repositories.OrdersRepository;
import cuscatlan.test.demo.services.DetailsService;

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
	public DetailDto saveDetail(DetailDto details) {

		details.setDetailId(null);
		EntityUtil entityUtil = new EntityUtil();
		DetailsEntity detailsSave = entityUtil.dtoToDetailsEntity(details);
		OrdersEntity orderEntity = ordersRepo.findById(detailsSave.getOrder().getOrderId()).orElse(null);

		List<DetailsEntity> detailList = detailsRepo.findAllByOrder(detailsSave.getOrder());
		Float total = 0f;

		for (DetailsEntity detailsEntity : detailList) {
			total = total + (detailsEntity.getPrice() * detailsEntity.getQuantity());
			orderEntity.setTotal(total);
		}

		ordersRepo.save(orderEntity);
		detailsSave = detailsRepo.save(detailsSave);
		details.setDetailId(detailsSave.getDetailId());
		return details;
	}

	@Override
	public DetailDto updateDetail(DetailDto details) {
		EntityUtil entityUtil = new EntityUtil();
		DetailsEntity detailsSave = entityUtil.dtoToDetailsEntity(details);
		OrdersEntity orderEntity = ordersRepo.findById(detailsSave.getOrder().getOrderId()).orElse(null);

		List<DetailsEntity> detailList = detailsRepo.findAllByOrder(detailsSave.getOrder());
		Float total = 0f;

		for (DetailsEntity detailsEntity : detailList) {
			total = total + (detailsEntity.getPrice() * detailsEntity.getQuantity());
			orderEntity.setTotal(total);
		}

		ordersRepo.save(orderEntity);
		
		detailsSave = detailsRepo.save(detailsSave);
		details.setDetailId(detailsSave.getDetailId());
		return details;
	}

	@Override
	public void deleteDetail(Integer id) {
		detailsRepo.deleteById(Long.valueOf(id));
	}

}

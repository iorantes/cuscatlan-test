package cuscatlan.test.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cuscatlan.test.demo.model.entity.DetailsEntity;
import cuscatlan.test.demo.model.entity.OrdersEntity;

@Repository
public interface DetailsRepository extends JpaRepository<DetailsEntity, Long> {

	public List<DetailsEntity> findAllByOrder(OrdersEntity order);

}

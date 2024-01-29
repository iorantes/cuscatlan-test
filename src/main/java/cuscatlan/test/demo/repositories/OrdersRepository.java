package cuscatlan.test.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cuscatlan.test.demo.model.entity.OrdersEntity;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

}

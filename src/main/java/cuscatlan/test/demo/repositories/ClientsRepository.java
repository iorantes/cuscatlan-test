package cuscatlan.test.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cuscatlan.test.demo.model.entity.ClientsEntity;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {

	public List<ClientsEntity> findByEmail(String email);

}

package cuscatlan.test.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cuscatlan.test.demo.model.entity.ClientsEntity;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {

	public ClientsEntity findByEmail(String email);

}

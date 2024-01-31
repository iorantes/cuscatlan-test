package cuscatlan.test.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.model.entity.DetailsEntity;

public interface DetailsService {

	public List<DetailsEntity> getDetails();

	public List<DetailsEntity> getDetailByOrder(Integer id);

	public ResponseEntity<?> saveDetail(DetailDto details);

	public DetailDto updateDetail(DetailDto details);

	public void deleteDetail(Integer id);

}

package cuscatlan.test.demo.services;

import java.util.List;

import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.model.entity.DetailsEntity;

public interface DetailsService {

	public List<DetailsEntity> getDetails();

	public List<DetailsEntity> getDetailByOrder(Integer id);

	public DetailDto saveDetail(DetailDto details);

	public DetailDto updateDetail(DetailDto details);

	public void deleteDetail(Integer id);

}

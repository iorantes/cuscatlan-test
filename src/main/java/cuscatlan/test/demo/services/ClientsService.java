package cuscatlan.test.demo.services;

import java.util.List;

import cuscatlan.test.demo.model.dto.ClientDto;
import cuscatlan.test.demo.model.entity.ClientsEntity;

public interface ClientsService {

	public List<ClientsEntity> getClients();

	public ClientsEntity getClient(Integer id);

	public ClientDto saveClient(ClientDto client);

	public ClientDto updateClient(ClientDto client);

	public void deleteClient(Integer id);

}

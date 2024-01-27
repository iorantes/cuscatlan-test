package cuscatlan.test.demo.services;

import java.util.List;

import cuscatlan.test.demo.model.ClientsEntity;

public interface ClientsService {

	public List<ClientsEntity> getClients();

	public ClientsEntity getClient(int id);

	public ClientsEntity saveClient(ClientsEntity client);

	public ClientsEntity updateClient(ClientsEntity client);

	public void deleteClient(int id);

}

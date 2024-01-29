package cuscatlan.test.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.config.EntityUtil;
import cuscatlan.test.demo.model.dto.ClientDto;
import cuscatlan.test.demo.model.entity.ClientsEntity;
import cuscatlan.test.demo.repositories.ClientsRepository;
import cuscatlan.test.demo.services.ClientsService;

@Service
public class ClientsServiceImpl implements ClientsService {

	@Autowired
	private ClientsRepository clientsRepo;

	@Override
	public List<ClientsEntity> getClients() {
		List<ClientsEntity> clients = clientsRepo.findAll();
		return clients;
	}

	@Override
	public ClientsEntity getClient(Integer id) {
		ClientsEntity clients = clientsRepo.findById(Long.valueOf(id)).orElse(null);
		return clients;
	}

	@Override
	public ClientDto saveClient(ClientDto client) {
		client.setClientId(null);
		EntityUtil entityUtil = new EntityUtil();
		ClientsEntity clientSave = entityUtil.dtoToClientsEntity(client);
		clientSave = clientsRepo.save(clientSave);
		client.setClientId(clientSave.getClientId());
		return client;
	}

	@Override
	public ClientDto updateClient(ClientDto client) {
		EntityUtil entityUtil = new EntityUtil();
		ClientsEntity clientSave = entityUtil.dtoToClientsEntity(client);
		clientSave = clientsRepo.save(clientSave);
		client.setClientId(clientSave.getClientId());
		return client;
	}

	@Override
	public void deleteClient(Integer id) {
		clientsRepo.deleteById(Long.valueOf(id));
	}

}

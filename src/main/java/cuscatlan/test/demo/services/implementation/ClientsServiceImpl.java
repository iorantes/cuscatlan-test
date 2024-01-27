package cuscatlan.test.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cuscatlan.test.demo.model.ClientsEntity;
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
	public ClientsEntity getClient(int id) {
		ClientsEntity clients = clientsRepo.findById(Long.valueOf(id)).orElse(null);
		return clients;
	}

	@Override
	public ClientsEntity saveClient(ClientsEntity client) {
		System.out.println(client.getAddress());
		ClientsEntity clients = clientsRepo.save(client);
		return clients;
	}

	@Override
	public ClientsEntity updateClient(ClientsEntity client) {
		System.out.println(client.getId());
		ClientsEntity clients = clientsRepo.save(client);
		return clients;
	}

	@Override
	public void deleteClient(int id) {
		clientsRepo.deleteById(Long.valueOf(id));
	}

}

package cuscatlan.test.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cuscatlan.test.demo.model.dto.AuthRequest;
import cuscatlan.test.demo.model.dto.ClientDto;
import cuscatlan.test.demo.services.ClientsService;
import cuscatlan.test.demo.services.SecurityService;

@RestController
@RequestMapping(value = "/clients")
public class ClientsController {

	@Autowired
	private ClientsService clientsService;

	@Autowired
	private SecurityService securityService;

	@GetMapping
	public ResponseEntity<?> getClients() {
		return ResponseEntity.ok(clientsService.getClients());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getClient(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(clientsService.getClient(id));
	}

	@PostMapping
	public ResponseEntity<?> saveClient(@RequestBody ClientDto client) {
		return ResponseEntity.ok(clientsService.saveClient(client));
	}

	@PutMapping
	public ResponseEntity<?> updateClient(@RequestBody ClientDto client) {
		return ResponseEntity.ok(clientsService.updateClient(client));
	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable(name = "id") Integer id) {
		clientsService.deleteClient(id);
	}

	@PostMapping(value = "/auth")
	public ResponseEntity<?> authClient(@RequestBody AuthRequest auth) throws Exception {
		return ResponseEntity.ok(securityService.authClient(auth));
	}

}

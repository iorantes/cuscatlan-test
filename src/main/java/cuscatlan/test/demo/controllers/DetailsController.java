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

import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.services.DetailsService;

@RestController
@RequestMapping(value = "/details")
public class DetailsController {

	@Autowired
	private DetailsService detailsService;

	@GetMapping
	public ResponseEntity<?> getDetails() {
		return ResponseEntity.ok(detailsService.getDetails());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getDetailByOrder(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(detailsService.getDetailByOrder(id));
	}

	@PostMapping
	public ResponseEntity<?> saveDetail(@RequestBody DetailDto details) {
		return ResponseEntity.ok(detailsService.saveDetail(details));
	}

	@PutMapping
	public ResponseEntity<?> updateDetail(@RequestBody DetailDto details) {
		return ResponseEntity.ok(detailsService.updateDetail(details));
	}

	@DeleteMapping("/{id}")
	public void deleteDetail(@PathVariable(name = "id") Integer id) {
		detailsService.deleteDetail(id);
	}

}

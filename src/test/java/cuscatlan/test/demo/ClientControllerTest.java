package cuscatlan.test.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cuscatlan.test.demo.config.EntityUtil;
import cuscatlan.test.demo.config.security.JwtUtil;
import cuscatlan.test.demo.config.security.SecurityConfig;
import cuscatlan.test.demo.controllers.ClientsController;
import cuscatlan.test.demo.model.dto.ClientDto;
import cuscatlan.test.demo.model.entity.ClientsEntity;
import cuscatlan.test.demo.repositories.ClientsRepository;
import cuscatlan.test.demo.services.ClientsService;
import cuscatlan.test.demo.services.SecurityService;

@WebMvcTest(ClientsController.class)
@Import({ SecurityConfig.class, ClientsController.class })
public class ClientControllerTest {

	@MockBean
	private ClientsService clientService;

	@MockBean
	private ClientsRepository clientRepo;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private SecurityService securityService;

	@MockBean
	private EntityUtil entityUtil;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldGetAllClients() throws Exception {

		mockMvc.perform(get("/clients").contentType(MediaType.APPLICATION_JSON).with(csrf())).andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldGetClient() throws Exception {

		mockMvc.perform(get("/clients/1").contentType(MediaType.APPLICATION_JSON).with(csrf()))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldSaveClient() throws Exception {

		ClientDto clientDto = new ClientDto(null, "Test", "Test LastName", "test@test.com", "Test", "1995-07-07", 1,
				"123", "ADMIN");

		mockMvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(clientDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldUpdateClient() throws Exception {

		long id = 1L;

		ClientsEntity clientUpdate = new ClientsEntity(id, "Test", "Test LastName", "test@test.com", "Test",
				entityUtil.getDateFromString("1995-07-07"), 1, "123", "ADMIN");

		when(clientRepo.findById(id)).thenReturn(Optional.empty());

		when(clientRepo.save(any(ClientsEntity.class))).thenReturn(clientUpdate);

		ClientDto clientDto = new ClientDto(clientUpdate.getClientId(), clientUpdate.getName(),
				clientUpdate.getLastName(), clientUpdate.getEmail(), clientUpdate.getAddress(),
				entityUtil.convertDateToString(clientUpdate.getBirthDate()), clientUpdate.getStatus(),
				clientUpdate.getPassword(), clientUpdate.getRole());

		mockMvc.perform(put("/clients").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(clientDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldDeleteClient() throws Exception {

		ClientDto clientDto = new ClientDto(null, "Test", "Test LastName", "test_delete@test.com", "Test", "1995-07-07",
				1, "123", "ADMIN");

		mockMvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(clientDto))).andExpect(status().isOk()).andDo(print());

		long id = 1L;
		ClientsEntity client = new ClientsEntity(id, "Test", "Test LastName", "test_delete@test.com", "Test",
				entityUtil.getDateFromString("1995-07-07"), 1, "123", "ADMIN");

		when(clientRepo.findByEmail("test_delete@test.com")).thenReturn(client);

		mockMvc.perform(delete("/clients/" + client.getClientId().toString()).contentType(MediaType.APPLICATION_JSON)
				.with(csrf())).andExpect(status().isOk()).andDo(print());

	}

}

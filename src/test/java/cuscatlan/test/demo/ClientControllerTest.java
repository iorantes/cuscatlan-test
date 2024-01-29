package cuscatlan.test.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cuscatlan.test.demo.config.security.JwtUtil;
import cuscatlan.test.demo.config.security.SecurityConfig;
import cuscatlan.test.demo.controllers.ClientsController;
import cuscatlan.test.demo.model.dto.ClientDto;
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

}

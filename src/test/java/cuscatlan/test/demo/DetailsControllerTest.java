package cuscatlan.test.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import cuscatlan.test.demo.config.EntityUtil;
import cuscatlan.test.demo.config.security.JwtUtil;
import cuscatlan.test.demo.config.security.SecurityConfig;
import cuscatlan.test.demo.controllers.DetailsController;
import cuscatlan.test.demo.model.dto.DetailDto;
import cuscatlan.test.demo.repositories.ClientsRepository;
import cuscatlan.test.demo.repositories.DetailsRepository;
import cuscatlan.test.demo.services.DetailsService;
import cuscatlan.test.demo.services.SecurityService;

@WebMvcTest(DetailsController.class)
@Import({ SecurityConfig.class, DetailsController.class })
public class DetailsControllerTest {

	@MockBean
	private DetailsService detailService;

	@MockBean
	private DetailsRepository detailsRepo;

	@MockBean
	private ClientsRepository clientsRepo;

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
	void shouldGetAllDetails() throws Exception {

		mockMvc.perform(get("/details").contentType(MediaType.APPLICATION_JSON).with(csrf())).andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldGetDetailsByOrderId() throws Exception {

		mockMvc.perform(get("/details/order/1").contentType(MediaType.APPLICATION_JSON).with(csrf()))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldSaveDetail() throws Exception {

		DetailDto detailDto = new DetailDto(null, 1L, 2, 1.5f, 1);

		mockMvc.perform(post("/details").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(detailDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldUpdateDetail() throws Exception {

		long id = 2L;

		DetailDto detailDto = new DetailDto(id, 1L, 1, 2.5f, 1);

		mockMvc.perform(put("/details").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(detailDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldDeleteDetail() throws Exception {

		mockMvc.perform(delete("/details/1").contentType(MediaType.APPLICATION_JSON).with(csrf()))
				.andExpect(status().isOk()).andDo(print());

	}

}

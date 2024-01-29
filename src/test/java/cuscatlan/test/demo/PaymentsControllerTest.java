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
import cuscatlan.test.demo.controllers.PaymentsController;
import cuscatlan.test.demo.model.dto.PaymentDto;
import cuscatlan.test.demo.repositories.ClientsRepository;
import cuscatlan.test.demo.repositories.PaymentRepository;
import cuscatlan.test.demo.services.PaymentService;
import cuscatlan.test.demo.services.SecurityService;

@WebMvcTest(PaymentsController.class)
@Import({ SecurityConfig.class, PaymentsController.class })
public class PaymentsControllerTest {

	@MockBean
	private PaymentService paymentService;

	@MockBean
	private PaymentRepository paymentRepo;

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
	void shouldGetAllPayments() throws Exception {

		mockMvc.perform(get("/payment").contentType(MediaType.APPLICATION_JSON).with(csrf())).andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldGetPayment() throws Exception {

		mockMvc.perform(get("/payment/1").contentType(MediaType.APPLICATION_JSON).with(csrf()))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldSavePayment() throws Exception {

		PaymentDto paymentDto = new PaymentDto(null, 1L, 10f, "1100202-00", "DONE");

		mockMvc.perform(post("/payment").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(paymentDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldUpdatePayment() throws Exception {

		PaymentDto paymentDto = new PaymentDto(1L, 1L, 10f, "1100202-00", "DONE");

		mockMvc.perform(put("/payment").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(paymentDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldDeletePayment() throws Exception {

		mockMvc.perform(delete("/payment/1").contentType(MediaType.APPLICATION_JSON).with(csrf()))
				.andExpect(status().isOk()).andDo(print());

	}

}

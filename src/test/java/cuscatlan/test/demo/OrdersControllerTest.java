package cuscatlan.test.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

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
import cuscatlan.test.demo.controllers.OrdersController;
import cuscatlan.test.demo.model.dto.OrderDto;
import cuscatlan.test.demo.model.entity.ClientsEntity;
import cuscatlan.test.demo.model.entity.OrdersEntity;
import cuscatlan.test.demo.repositories.ClientsRepository;
import cuscatlan.test.demo.repositories.OrdersRepository;
import cuscatlan.test.demo.services.OrdersService;
import cuscatlan.test.demo.services.SecurityService;

@WebMvcTest(OrdersController.class)
@Import({ SecurityConfig.class, OrdersController.class })
public class OrdersControllerTest {

	@MockBean
	private OrdersService orderService;

	@MockBean
	private OrdersRepository orderRepo;
	
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
	void shouldGetAllOrders() throws Exception {

		mockMvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON).with(csrf())).andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldGetOrder() throws Exception {

		mockMvc.perform(get("/orders/1").contentType(MediaType.APPLICATION_JSON).with(csrf()))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldSaveOrder() throws Exception {

		OrderDto orderDto = new OrderDto(null, null, 2.2f, 1L);

		mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(orderDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldUpdateOrder() throws Exception {

		long id = 1L;

		ClientsEntity clientUpdate = new ClientsEntity(id, "Test", "Test LastName", "test@test.com", "Test",
				entityUtil.getDateFromString("1995-07-07"), 1, "123", "ADMIN");

		OrdersEntity ordersEntity = new OrdersEntity(id, entityUtil.getCurrentDate(), 2.2f, clientUpdate);

		when(orderRepo.findById(id)).thenReturn(Optional.empty());

		when(orderRepo.save(any(OrdersEntity.class))).thenReturn(ordersEntity);
		
		OrderDto orderDto = new OrderDto(ordersEntity.getOrderId(), entityUtil.convertDateToString(ordersEntity.getOrderDate()), ordersEntity.getTotal(), ordersEntity.getClient().getClientId());

		mockMvc.perform(put("/orders").contentType(MediaType.APPLICATION_JSON).with(csrf())
				.content(objectMapper.writeValueAsString(orderDto))).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(username = "irvin@irvin.com", roles = "ADMIN")
	void shouldDeleteOrder() throws Exception {

		long id = 1L;

		ClientsEntity clientUpdate = new ClientsEntity(id, "Test", "Test LastName", "test@test.com", "Test",
				entityUtil.getDateFromString("1995-07-07"), 1, "123", "ADMIN");

		OrdersEntity ordersEntity = new OrdersEntity(id, entityUtil.getCurrentDate(), 2.2f, clientUpdate);

		when(orderRepo.findById(id)).thenReturn(Optional.empty());

		when(orderRepo.save(any(OrdersEntity.class))).thenReturn(ordersEntity);

		mockMvc.perform(delete("/orders/" + ordersEntity.getOrderId().toString()).contentType(MediaType.APPLICATION_JSON)
				.with(csrf())).andExpect(status().isOk()).andDo(print());

	}

}

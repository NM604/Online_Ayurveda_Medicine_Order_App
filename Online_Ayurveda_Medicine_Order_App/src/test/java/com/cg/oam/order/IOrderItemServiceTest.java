package com.cg.oam.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderItemDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Admin;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.Order;
import com.cg.oam.entity.OrderItem;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IOrderItemRepository;
import com.cg.oam.repository.IOrderRepository;
import com.cg.oam.service.IOrderItemServiceImpl;
import com.cg.oam.service.IOrderServiceImpl;

/**
 * The Class IOrderServiceTest.
 */
@SpringBootTest
@DisplayName("OrderService Test")
public class IOrderItemServiceTest {

	/** The order repository. */
	@Mock
	IOrderItemRepository orderItemRepository;

	/** The customer repository. */
	@Mock
	ICustomerRepository customerRepository;

	/** The order service. */
	@InjectMocks
	IOrderItemServiceImpl orderItemService;

	/**
	 * View order by id test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing viewOrderById with valid Order Id")
	public void viewOrderByIdTest() throws InvalidDataException {
		OrderItemDTO expectedOrder = new OrderItemDTO(1, null, null,null,null);
		Optional<OrderItem> opt = Optional.of(new OrderItem(1, null, null, null, null));
		Mockito.when(orderItemRepository.findById(1)).thenReturn(opt);
		OrderItemDTO actualOrder = orderItemService.viewOrderItemById(1);
		assertEquals(expectedOrder, actualOrder);
	}
	
	/**
	 * View order by id test wIth invalid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing viewOrderById with invalid Order Id")
	public void viewOrderByIdTestWIthInvalidId() throws InvalidDataException {
		Optional<OrderItem> opt = Optional.ofNullable(null);
		Mockito.when(orderItemRepository.findById(anyInt())).thenReturn(opt);
		InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderItemService.viewOrderItemById(1));
		assertEquals("Service.ORDER_ITEM_NOT_FOUND", exception.getMessage());
		
	}
	
	/**
	 * View all orders Test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing viewAllOrders")
	public void viewAllOrdersTest() throws InvalidDataException {
		OrderItemDTO orderDto = new OrderItemDTO(1, null, null, null,null);
		List<OrderItemDTO> expOrders =new ArrayList<>();
		expOrders.add(orderDto);
		List<OrderItem> orders =new ArrayList<>();
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderItemId(1);
		orders.add(orderItem);
		Mockito.when(orderItemRepository.findAll()).thenReturn(orders);
		List<OrderItemDTO> actualOrders = orderItemService.viewAllOrderItems();
		assertEquals(expOrders, actualOrders);
	}

	

}

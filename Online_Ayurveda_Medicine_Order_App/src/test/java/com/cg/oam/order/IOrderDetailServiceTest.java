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
import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDetailDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Admin;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.OrderDetail;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IOrderDetailRepository;
import com.cg.oam.service.IOrderDetailServiceImpl;

/**
 * The Class IOrderServiceTest.
 */
@SpringBootTest
@DisplayName("OrderService Test")
public class IOrderDetailServiceTest {

	/** The order repository. */
	@Mock
	IOrderDetailRepository orderDetailRepository;

	/** The customer repository. */
	@Mock
	ICustomerRepository customerRepository;

	/** The order service. */
	@InjectMocks
	IOrderDetailServiceImpl orderDetailService;

	/**
	 * View order by id test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing viewOrderDetailById with valid Order Id")
	
	public void viewOrderDetailByIdTest() throws InvalidDataException {
		
		OrderDetailDTO expectedOrder = new OrderDetailDTO(3,null,null,OrderStatus.CREATED,null,null);
		
		Optional<OrderDetail> opt = Optional.of(new OrderDetail(3,null,null,OrderStatus.CREATED,null,null));
		Mockito.when(orderDetailRepository.findById(3)).thenReturn(opt);
		OrderDetailDTO actualOrder = orderDetailService.viewOrderDetailById(3);
		assertEquals(expectedOrder, actualOrder);
	}
	
	/**
	 * View order by id test wIth invalid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing viewOrderDetailById with invalid Order Id")
	public void viewOrderDetailByIdTestWIthInvalidId() throws InvalidDataException {
		Optional<OrderDetail> opt = Optional.ofNullable(null);
		Mockito.when(orderDetailRepository.findById(anyInt())).thenReturn(opt);
		InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderDetailService.viewOrderDetailById(1));
		assertEquals("Service.ORDER_NOT_FOUND", exception.getMessage());
		
	}
	
	/**
	 * View all orders Test.
	 *
	 * @throws InvalidDataException the invalid data exception
//	 */
	@Test
	@DisplayName("Testing viewAllOrders")
	public void viewAllOrdersDetailsTest() throws InvalidDataException {
		OrderDetailDTO orderDto = new OrderDetailDTO(1, null, null,OrderStatus.CREATED,null,null);
		List<OrderDetailDTO> expOrders =new ArrayList<>();
		expOrders.add(orderDto);
		List<OrderDetail> orders =new ArrayList<>();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderDetailId(1);
		orders.add(orderDetail);
		Mockito.when(orderDetailRepository.findAll()).thenReturn(orders);
		List<OrderDetailDTO> actualOrders = orderDetailService.viewAllOrderDetail();
		assertEquals(expOrders, actualOrders);
	}

	/**
	 * Cancel order test with valid order id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing CancelOrder with valid Order Id")
	public void cancelOrderDetailTestWithValidOrderId() throws InvalidDataException {
		OrderDetailDTO expectedOrder = new OrderDetailDTO(1, null, null, OrderStatus.CANCELLED,null,null);
		Optional<OrderDetail> opt = Optional.of(new OrderDetail(1, null, null, OrderStatus.CREATED,null,null));
		Mockito.when(orderDetailRepository.findById(anyInt())).thenReturn(opt);
		OrderDetailDTO actualOrder = orderDetailService.cancelOrderDetail(1);
		assertEquals(expectedOrder, actualOrder);
	}

	/**
	 * Cancel order test with invalid values.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing CancelOrderDetail with invalid Order Id")
	public void cancelOrderDetailTestWithInvalidValues() throws InvalidDataException {
		Optional<OrderDetail> opt = Optional.ofNullable(null);
		Mockito.when(orderDetailRepository.findById(anyInt())).thenReturn(opt);
		InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderDetailService.cancelOrderDetail(1));
		assertEquals("Service.ORDER_NOT_FOUND", exception.getMessage());
	}



	/**
	 * Show all orders by customer id test with invalid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Show all order by customerId from invalid id")
	public void showAllOrdersByCustomerIdTestWithInvalidId() throws InvalidDataException {
		Optional<Customer> option = Optional.ofNullable(null);
		Mockito.when(customerRepository.findById(anyInt())).thenReturn(option);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderDetailService.showAllOrderDetailssByCustomerId(1));
		assertEquals("Service.CUSTOMERS_NOT_FOUND", exception.getMessage());
	}

	/**
	 * Show all orders by customer id test with valid id.
	 *
	 * @throws InvalidDataException the invalid data exception
//	 */
//	@Test
//	@DisplayName("Show all order by customerId with valid id")
//	public void showAllOrdersByCustomerIdTestWithvalidId() throws InvalidDataException {
//		OrderDetail expectedOrder = new OrderDetail();
//		expectedOrder.setOrderDetailId(101);
//
//		OrderDetailDTO orderDto = new OrderDetailDTO();
//		orderDto.setOrderDetailId(expectedOrder.getOrderDetailId());
//
//		List<OrderDetailDTO> expectedOrders = new ArrayList<>();
//		expectedOrders.add(orderDto);
//
//		Customer customer = new Customer();
//		customer.setCustomerId(1);
//		customer.setOrder(expectedOrder);
//		Optional<Customer> option = Optional.of(customer);
//		Mockito.when(customerRepository.findById(1)).thenReturn(option);
//		List<OrderDetailDTO> actualOrders = orderDetailService.showAllOrderDetailssByCustomerId(1);
//		assertEquals(expectedOrders, actualOrders);
//	}

	/**
	 * Show all orders by order date test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order Detail by Order Date ")
	public void showAllOrdersDetailByOrderDateTest() throws InvalidDataException {
		LocalDate orderDate = LocalDate.of(2022, 10, 10);
		OrderDetail order = new OrderDetail();
		order.setOrderDetailId(101);
		order.setOrderDate(orderDate);

		OrderDetailDTO orderDto = new OrderDetailDTO();
		orderDto.setOrderDetailId(order.getOrderDetailId());
		orderDto.setOrderDate(order.getOrderDate());

		List<OrderDetail> orders = new ArrayList<>();
		orders.add(order);
		List<OrderDetailDTO> expectedOrders = new ArrayList<>();
		expectedOrders.add(orderDto);
		Mockito.when(orderDetailRepository.findByOrderDate(any())).thenReturn(orders);
		List<OrderDetailDTO> actualOrders = orderDetailService.showAllOrderDetailsByOrderDate(orderDate);
		assertEquals(expectedOrders, actualOrders);
	}

	/**
	 * Show all orders by order date test exception.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order Detail by Order Date with no orders found")
	public void showAllOrdersDetailByOrderDateTestException() throws InvalidDataException {
		LocalDate orderDate = LocalDate.of(2022, 10, 10);
		List<OrderDetail> orders = new ArrayList<>();
		Mockito.when(orderDetailRepository.findByOrderDate(any())).thenReturn(orders);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderDetailService.showAllOrderDetailsByOrderDate(orderDate));
		assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());
	}

	/**
	 * Show all orders by dispatch date test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order by Dispatch Date ")
	public void showAllOrdersByDispatchDateTest() throws InvalidDataException {
		LocalDate dispatchDate = LocalDate.of(2022, 10, 10);
		OrderDetail order = new OrderDetail();
		order.setOrderDetailId(101);
		order.setOrderDate(dispatchDate);

		OrderDetailDTO orderDto = new OrderDetailDTO();
		orderDto.setOrderDetailId(order.getOrderDetailId());
		orderDto.setOrderDate(order.getOrderDate());

		List<OrderDetail> orders = new ArrayList<>();
		orders.add(order);
		List<OrderDetailDTO> expectedOrders = new ArrayList<>();
		expectedOrders.add(orderDto);
		Mockito.when(orderDetailRepository.findByDispatchDate(any())).thenReturn(orders);
		List<OrderDetailDTO> actualOrders = orderDetailService.showAllOrderDetailsByDispatchDate(dispatchDate);
		assertEquals(expectedOrders, actualOrders);
	}

	/**
	 * Show all orders by dispatch date test exception.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order Detail by Dispatch Date with no orders found")
	public void showAllOrdersDetailByDispatchDateTestException() throws InvalidDataException {
		LocalDate dispatchDate = LocalDate.of(2022, 10, 10);
		List<OrderDetail> orders = new ArrayList<>();
		Mockito.when(orderDetailRepository.findByDispatchDate(any())).thenReturn(orders);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderDetailService.showAllOrderDetailsByDispatchDate(dispatchDate));
		assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());
	}






	/**
	 * Update order not present.
	 *
	 * @throws InvalidDataException the invalid data exception
//	 */
	@Test
	@DisplayName("Check Updating Non-Existing Order Detail")
	public void updateOrderDetailNotPresent() throws InvalidDataException {
		OrderDetailDTO order = new OrderDetailDTO(4, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), OrderStatus.CREATED,null,null);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class,
				() -> orderDetailService.updateOrderDetail(order));
		Assertions.assertEquals("Service.ORDER_NOT_FOUND", e.getMessage());
	}

	/**
	 * Update order status.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */

	@Test
	void validFeedbackUpdate() throws InvalidDataException{
		Optional<OrderDetail> dupOrder = Optional
		.of(new OrderDetail(1, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8),OrderStatus.CREATED,null,null));

		OrderDetailDTO order = new OrderDetailDTO(1, null, null, OrderStatus.CREATED,null,null);
		Mockito.when(orderDetailRepository.findById(order.getOrderDetailId())).thenReturn((dupOrder));
		Assertions.assertEquals(order,orderDetailService.updateOrderDetail(order ));
	}

	/**
	 * Update order status not present.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Order Detail Status with Non-Existing Order")
	public void updateOrderDetailStatusNotPresent() throws InvalidDataException {
		OrderDetailDTO order = new OrderDetailDTO(4, null, null, OrderStatus.CREATED,null,null);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class,
				() -> orderDetailService.updateOrderDetail(order));
		Assertions.assertEquals("Service.ORDER_NOT_FOUND", e.getMessage());
	}

	/**
	 * Show all orders by status.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Checking Show all order Detail by Order Status")
	public void showAllOrdersDetailByStatus() throws InvalidDataException {
		OrderStatus orderStatus = OrderStatus.CREATED;
		OrderDetail order = new OrderDetail();
		order.setOrderDetailId(101);
		order.setOrderStatus(orderStatus);
		OrderDetailDTO orderDto = new OrderDetailDTO();
		orderDto.setOrderDetailId(order.getOrderDetailId());
		orderDto.setOrderStatus(order.getOrderStatus());
		List<OrderDetail> orders = new ArrayList<>();
		orders.add(order);
		List<OrderDetailDTO> expectedOrders = new ArrayList<>();
		expectedOrders.add(orderDto);
		Mockito.when(orderDetailRepository.findByOrderStatus(any())).thenReturn(orders);
		List<OrderDetailDTO> actualOrders = orderDetailService.viewOrderDetailByStatus(orderStatus);
		assertEquals(expectedOrders, actualOrders);
	}

	/**
	 * Show all orders by order status with no order.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order Detail by Order Date with no orders found")
	public void showAllOrdersByOrderDetailStatuswithnoOrder() throws InvalidDataException {
		OrderStatus orderStatus = OrderStatus.CREATED;
		List<OrderDetail> orders = new ArrayList<>();
		Mockito.when(orderDetailRepository.findByOrderStatus(any())).thenReturn(orders);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderDetailService.viewOrderDetailByStatus(orderStatus));
		assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());
	}

	
}
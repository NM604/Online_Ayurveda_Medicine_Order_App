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
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Admin;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.Order;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IOrderRepository;
import com.cg.oam.service.IOrderServiceImpl;

/**
 * The Class IOrderServiceTest.
 */
@SpringBootTest
@DisplayName("OrderService Test")
public class IOrderServiceTest {

	/** The order repository. */
	@Mock
	IOrderRepository orderRepository;

	/** The customer repository. */
	@Mock
	ICustomerRepository customerRepository;

	/** The order service. */
	@InjectMocks
	IOrderServiceImpl orderService;

	/**
	 * View order by id test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing viewOrderById with valid Order Id")
	public void viewOrderByIdTest() throws InvalidDataException {
		OrderDTO expectedOrder = new OrderDTO(1, null, null, null, OrderStatus.CREATED);
		Optional<Order> opt = Optional.of(new Order(1, null, null, null, OrderStatus.CREATED));
		Mockito.when(orderRepository.findById(1)).thenReturn(opt);
		OrderDTO actualOrder = orderService.viewOrderById(1);
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
		Optional<Order> opt = Optional.ofNullable(null);
		Mockito.when(orderRepository.findById(anyInt())).thenReturn(opt);
		InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderService.viewOrderById(1));
		assertEquals("Service.ORDER_NOT_FOUND", exception.getMessage());
		
	}
	
	/**
	 * View all orders Test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing viewAllOrders")
	public void viewAllOrdersTest() throws InvalidDataException {
		OrderDTO orderDto = new OrderDTO(1, null, null, null, OrderStatus.CREATED);
		List<OrderDTO> expOrders =new ArrayList<>();
		expOrders.add(orderDto);
		List<Order> orders =new ArrayList<>();
		Order order = new Order();
		order.setOrderId(1);
		orders.add(order);
		Mockito.when(orderRepository.findAll()).thenReturn(orders);
		List<OrderDTO> actualOrders = orderService.viewAllOrder();
		assertEquals(expOrders, actualOrders);
	}

	/**
	 * Cancel order test with valid order id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing CancelOrder with valid Order Id")
	public void cancelOrderTestWithValidOrderId() throws InvalidDataException {
		OrderDTO expectedOrder = new OrderDTO(1, null, null, null, OrderStatus.CANCELLED);
		Optional<Order> opt = Optional.of(new Order(1, null, null, null, OrderStatus.CREATED));
		Mockito.when(orderRepository.findById(anyInt())).thenReturn(opt);
		OrderDTO actualOrder = orderService.cancelOrder(1);
		assertEquals(expectedOrder, actualOrder);
	}

	/**
	 * Cancel order test with invalid values.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing CancelOrder with invalid Order Id")
	public void cancelOrderTestWithInvalidValues() throws InvalidDataException {
		Optional<Order> opt = Optional.ofNullable(null);
		Mockito.when(orderRepository.findById(anyInt())).thenReturn(opt);
		InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderService.cancelOrder(1));
		assertEquals("Service.ORDER_NOT_FOUND", exception.getMessage());
	}

	/**
	 * Show all orders by medicine id test with empty orders.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order by medicineId with empty order List")
	public void showAllOrdersByMedicineIdTestWithEmptyOrders() throws InvalidDataException {
		Iterable<Customer> customers = new ArrayList<>();
		Mockito.when(customerRepository.findAll()).thenReturn(customers);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderService.showAllOrdersByMedicineId(1));
		assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());
	}

	/**
	 * Show all orders by medicine id test with invalid medicine id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order by medicineId with invalid medicine Id")
	public void showAllOrdersByMedicineIdTestWithInvalidMedicineId() throws InvalidDataException {
		Medicine medicine = new Medicine();
		medicine.setMedicineId("501");
		List<Medicine> medicines = new ArrayList<>();
		medicines.add(medicine);

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setMedicineList(medicines);
		Iterable<Customer> customers = new ArrayList<>();
		Mockito.when(customerRepository.findAll()).thenReturn(customers);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderService.showAllOrdersByMedicineId(1));
		assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());
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
				() -> orderService.showAllOrdersByCustomerId(1));
		assertEquals("Customer not found", exception.getMessage());
	}

	/**
	 * Show all orders by customer id test with valid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Show all order by customerId with valid id")
	public void showAllOrdersByCustomerIdTestWithvalidId() throws InvalidDataException {
		Order expectedOrder = new Order();
		expectedOrder.setOrderId(101);

		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(expectedOrder.getOrderId());

		List<OrderDTO> expectedOrders = new ArrayList<>();
		expectedOrders.add(orderDto);

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setOrder(expectedOrder);
		Optional<Customer> option = Optional.of(customer);
		Mockito.when(customerRepository.findById(1)).thenReturn(option);
		List<OrderDTO> actualOrders = orderService.showAllOrdersByCustomerId(1);
		assertEquals(expectedOrders, actualOrders);
	}

	/**
	 * Show all orders by order date test.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order by Order Date ")
	public void showAllOrdersByOrderDateTest() throws InvalidDataException {
		LocalDate orderDate = LocalDate.of(2022, 10, 10);
		Order order = new Order();
		order.setOrderId(101);
		order.setOrderDate(orderDate);

		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(order.getOrderId());
		orderDto.setOrderDate(order.getOrderDate());

		List<Order> orders = new ArrayList<>();
		orders.add(order);
		List<OrderDTO> expectedOrders = new ArrayList<>();
		expectedOrders.add(orderDto);
		Mockito.when(orderRepository.findByOrderDate(any())).thenReturn(orders);
		List<OrderDTO> actualOrders = orderService.showAllOrdersByOrderDate(orderDate);
		assertEquals(expectedOrders, actualOrders);
	}

	/**
	 * Show all orders by order date test exception.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order by Order Date with no orders found")
	public void showAllOrdersByOrderDateTestException() throws InvalidDataException {
		LocalDate orderDate = LocalDate.of(2022, 10, 10);
		List<Order> orders = new ArrayList<>();
		Mockito.when(orderRepository.findByOrderDate(any())).thenReturn(orders);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderService.showAllOrdersByOrderDate(orderDate));
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
		Order order = new Order();
		order.setOrderId(101);
		order.setOrderDate(dispatchDate);

		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(order.getOrderId());
		orderDto.setOrderDate(order.getOrderDate());

		List<Order> orders = new ArrayList<>();
		orders.add(order);
		List<OrderDTO> expectedOrders = new ArrayList<>();
		expectedOrders.add(orderDto);
		Mockito.when(orderRepository.findByDispatchDate(any())).thenReturn(orders);
		List<OrderDTO> actualOrders = orderService.showAllOrdersByDispatchDate(dispatchDate);
		assertEquals(expectedOrders, actualOrders);
	}

	/**
	 * Show all orders by dispatch date test exception.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order by Dispatch Date with no orders found")
	public void showAllOrdersByDispatchDateTestException() throws InvalidDataException {
		LocalDate dispatchDate = LocalDate.of(2022, 10, 10);
		List<Order> orders = new ArrayList<>();
		Mockito.when(orderRepository.findByDispatchDate(any())).thenReturn(orders);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderService.showAllOrdersByDispatchDate(dispatchDate));
		assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());
	}

	/**
	 * Calculate total cost test with valid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing calculate total cost with valid orderId")
	public void calculateTotalCostTestWithValidId() throws InvalidDataException {

		Medicine medicine1 = new Medicine();
		Medicine medicine2 = new Medicine();
		medicine1.setMedicineId("501");
		medicine1.setMedicineCost(20F);
		medicine2.setMedicineId("502");
		medicine2.setMedicineCost(30F);
		List<Medicine> medicines = new ArrayList<>();
		medicines.add(medicine1);
		medicines.add(medicine2);

		Optional<Order> optional = Optional.of(new Order(1, null, null, null, OrderStatus.CREATED));

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setOrder(optional.get());
		customer.setMedicineList(medicines);

		List<Customer> customers = new ArrayList<>();
		customers.add(customer);

		Mockito.when(orderRepository.findById(anyInt())).thenReturn(optional);
		Mockito.when(customerRepository.findAll()).thenReturn(customers);
		Double actualResult = orderService.calculateTotalCost(1);
		Double expectedResult = (double) (medicine1.getMedicineCost() + medicine2.getMedicineCost());
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Calculate total cost test with invalid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing calculate total cost with invalid orderId")
	public void calculateTotalCostTestWithInvalidId() throws InvalidDataException {
		Optional<Order> optional = Optional.ofNullable(null);
		Mockito.when(orderRepository.findById(anyInt())).thenReturn(optional);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderService.calculateTotalCost(1));
		assertEquals("Service.ORDER_NOT_FOUND", exception.getMessage());
	}

	/**
	 * Delete order test with invalid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing delete order with invalid orderId")
	public void deleteOrderTestWithInvalidId() throws InvalidDataException {
		Optional<Order> optional = Optional.ofNullable(null);
		Mockito.when(orderRepository.findById(anyInt())).thenReturn(optional);
		InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderService.deleteOrder(1));
		assertEquals("Service.ORDER_NOT_FOUND", exception.getMessage());
	}

	/**
	 * Delete order test with valid id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing delete order with valid orderId")
	public void deleteOrderTestWithValidId() throws InvalidDataException {
		Optional<Order> optional = Optional.of(new Order(1, null, null, null, OrderStatus.CREATED));
		Order order = optional.get();
		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(1);
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setOrder(order);
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);

		Mockito.when(orderRepository.findById(anyInt())).thenReturn(optional);
		Mockito.when(customerRepository.findAll()).thenReturn(customers);
		OrderDTO actualResult = orderService.deleteOrder(1);
		OrderDTO expectedResult = orderDto;
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * Adds the order.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Adding New Order")
	public void addOrderTest() throws InvalidDataException {
		OrderDTO order = new OrderDTO(1, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), 67f, OrderStatus.CREATED);
		Order newOrder = new Order(1, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), 67f, OrderStatus.CREATED);
		Mockito.when(orderRepository.save(Mockito.any())).thenReturn(newOrder);
		Assertions.assertEquals(order, orderService.addOrder(order));
	}

	/**
	 * Update admin.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Existing Order")
	public void updateAdmin() throws InvalidDataException {
		OrderDTO order = new OrderDTO(1, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), 67f, OrderStatus.CREATED);
		OrderDTO order1 = new OrderDTO(1, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), 67f, OrderStatus.CREATED);
		Optional<Order> dupOrder = Optional
				.of(new Order(1, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), 67f, OrderStatus.CREATED));

		List<Order> orders = new ArrayList<>();
		Order newOrder = new Order();
		Mockito.when(orderRepository.save(Mockito.any())).thenReturn(newOrder);
		orderService.addOrder(order);
		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(dupOrder);
		Assertions.assertEquals(order1, orderService.updateOrder(order1));
	}

	/**
	 * Update order not present.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Non-Existing Order")
	public void updateOrderNotPresent() throws InvalidDataException {
		OrderDTO order = new OrderDTO(4, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), 67f, OrderStatus.CREATED);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class,
				() -> orderService.updateOrder(order));
		Assertions.assertEquals("Service.ORDER_NOT_FOUND", e.getMessage());
	}

	/**
	 * Update order status.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Order  Status Existing Order")
	public void updateOrderStatus() throws InvalidDataException {
		OrderDTO order = new OrderDTO(1, null, null, null, OrderStatus.CREATED);
		OrderDTO order1 = new OrderDTO(1, null, null, null, OrderStatus.CREATED);
		Optional<Order> dupOrder = Optional
				.of(new Order(1, LocalDate.of(2020, 9, 8), LocalDate.of(2020, 9, 8), 67f, OrderStatus.CREATED));

		List<Order> orders = new ArrayList<>();
		Order newOrder = new Order();
		Mockito.when(orderRepository.save(Mockito.any())).thenReturn(newOrder);
		orderService.addOrder(order);
		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(dupOrder);
		Assertions.assertEquals(order1, orderService.updateOrder(order1));
	}

	/**
	 * Update order status not present.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Order Status with Non-Existing Order")
	public void updateOrderStatusNotPresent() throws InvalidDataException {
		OrderDTO order = new OrderDTO(4, null, null, null, OrderStatus.CREATED);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class,
				() -> orderService.updateOrder(order));
		Assertions.assertEquals("Service.ORDER_NOT_FOUND", e.getMessage());
	}

	/**
	 * Show all orders by status.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Checking Show all order by Order Status")
	public void showAllOrdersByStatus() throws InvalidDataException {
		OrderStatus orderStatus = OrderStatus.CREATED;
		Order order = new Order();
		order.setOrderId(101);
		order.setOrderStatus(orderStatus);
		OrderDTO orderDto = new OrderDTO();
		orderDto.setOrderId(order.getOrderId());
		orderDto.setOrderStatus(order.getOrderStatus());
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		List<OrderDTO> expectedOrders = new ArrayList<>();
		expectedOrders.add(orderDto);
		Mockito.when(orderRepository.findByOrderStatus(any())).thenReturn(orders);
		List<OrderDTO> actualOrders = orderService.viewOrderByStatus(orderStatus);
		assertEquals(expectedOrders, actualOrders);
	}

	/**
	 * Show all orders by order status with no order.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Testing Show all order by Order Date with no orders found")
	public void showAllOrdersByOrderStatuswithnoOrder() throws InvalidDataException {
		OrderStatus orderStatus = OrderStatus.CREATED;
		List<Order> orders = new ArrayList<>();
		Mockito.when(orderRepository.findByOrderStatus(any())).thenReturn(orders);
		InvalidDataException exception = assertThrows(InvalidDataException.class,
				() -> orderService.viewOrderByStatus(orderStatus));
		assertEquals("Service.ORDERS_NOT_FOUND", exception.getMessage());
	}

	
}
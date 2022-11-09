package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.dto.CategoryDTO;
import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.MedicineDTO;
import com.cg.oam.dto.OrderDetailDTO;
import com.cg.oam.dto.OrderItemDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.OrderDetail;
import com.cg.oam.entity.OrderItem;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IMedicineRepository;
import com.cg.oam.repository.IOrderDetailRepository;
import com.cg.oam.repository.IOrderItemRepository;

@Service(value = "orderItemService")
@Transactional
public class IOrderItemServiceImpl implements IOrderItemService {

	@Autowired
	private IOrderItemRepository orderItemRepository;
	
	@Autowired
	private IOrderDetailRepository orderDetailRepository;
	
	@Autowired
	private IMedicineRepository medicineRepository;

	public OrderItemDTO convertEntityToDto(OrderItem order) {
		OrderItemDTO resultOrderItemDto = new OrderItemDTO();
		resultOrderItemDto.setOrderItemId(order.getOrderItemId());
		resultOrderItemDto.setQuantity(order.getQuantity());
		resultOrderItemDto.setPrice(order.getPrice());

		
		OrderDetail orderDetail = order.getOrderDetail();
		if(orderDetail!=null) {
			OrderDetailDTO resultOrderDto = new OrderDetailDTO();
			resultOrderDto.setOrderDetailId(orderDetail.getOrderDetailId());
			resultOrderDto.setDispatchDate(orderDetail.getDispatchDate());
			resultOrderDto.setOrderDate(orderDetail.getOrderDate());
			resultOrderDto.setTotalCost(orderDetail.getTotalCost());
			resultOrderDto.setOrderStatus(orderDetail.getOrderStatus());
			Customer c = orderDetail.getCustomer();
			CustomerDTO cd = new CustomerDTO();
			cd.setCustomerId(c.getCustomerId());
			cd.setCustomerName(c.getCustomerName());
			cd.setCustomerPassword(c.getCustomerPassword());
			resultOrderDto.setCustomer(cd);
			resultOrderItemDto.setOrderDetail(resultOrderDto);
		}else {
			resultOrderItemDto.setOrderDetail(null);
		}
		

		Medicine medicine = order.getMedicine();
		if(medicine!=null) {
			MedicineDTO resultOMedicineDto = new MedicineDTO();
			// resultOMedicineDto.setSrno(medicine.getSrno());
			resultOMedicineDto.setMedicineId(medicine.getMedicineId());
			resultOMedicineDto.setMedicineName(medicine.getMedicineName());
			resultOMedicineDto.setMedicineCost(medicine.getMedicineCost());
			resultOMedicineDto.setMfd(medicine.getMfd());
			resultOMedicineDto.setExpiryDate(medicine.getExpiryDate());
			resultOMedicineDto.setCompanyName(medicine.getCompanyName());
			if(resultOMedicineDto.getCategoryDTO()!=null) {
				resultOMedicineDto.setCategoryDTO(
						new CategoryDTO(medicine.getCategory().getCategoryId(), medicine.getCategory().getCategoryName()));
			}else {
				resultOMedicineDto.setCategoryDTO(null);
			}
			

			resultOrderItemDto.setMedicine(resultOMedicineDto);
		}else {
			resultOrderItemDto.setOrderDetail(null);
		}

		

		return resultOrderItemDto;
	}

	@Override
	public OrderItemDTO addOrderItem(OrderItemDTO orderItem) throws InvalidDataException {
		if(orderItem.getOrderItemId()!=null) {
			Optional<OrderItem> optional = orderItemRepository.findById(orderItem.getOrderItemId());
			if(optional.isPresent()) {
				throw new InvalidDataException("Service.ORDER_ITEM_FOUND");
			}
		}
		OrderItem orderEntity = new OrderItem();
		orderEntity.setPrice(orderItem.getPrice());
		orderEntity.setQuantity(orderItem.getQuantity());
		Optional<OrderDetail> optionalOrdDet = orderDetailRepository.findById(orderItem.getOrderDetail().getOrderDetailId());
		OrderDetail ord = optionalOrdDet.get();
		orderEntity.setOrderDetail(ord);
		
		Optional<Medicine> optionalMed = medicineRepository.findById(orderItem.getMedicine().getMedicineId());
		Medicine med = optionalMed.get();
		orderEntity.setMedicine(med);
		
		OrderItem createdOrder = orderItemRepository.save(orderEntity);

		OrderItemDTO createdOrderDto = convertEntityToDto(createdOrder);
		return createdOrderDto;
	}

	@Override
	public List<OrderItemDTO> viewAllOrderItems() throws InvalidDataException {
		Iterable<OrderItem> orders = orderItemRepository.findAll();
		List<OrderItemDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderItemDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});
		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDER_ITEMS_NOT_FOUND");
		}
		return orderDtos;
	}

	@Override
	public OrderItemDTO viewOrderItemById(Integer orderItemId) throws InvalidDataException {
		Optional<OrderItem> optional = orderItemRepository.findById(orderItemId);
		OrderItem orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_ITEM_NOT_FOUND"));

		OrderItemDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderItemDTO updateOrderItem(OrderItemDTO orderItem) throws InvalidDataException {
		Optional<OrderItem> optional = orderItemRepository.findById(orderItem.getOrderItemId());
		OrderItem orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_ITEM_NOT_FOUND"));

		if (orderEntity.getQuantity() != null)
			orderEntity.setQuantity(orderItem.getQuantity());
		if (orderEntity.getPrice() != null)
			orderEntity.setPrice(orderItem.getPrice());
		if (orderEntity.getOrderDetail() != null) {
			Optional<OrderDetail> optionalCust = orderDetailRepository.findById(orderItem.getOrderDetail().getOrderDetailId());
			OrderDetail ord = optionalCust.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));
			orderEntity.setOrderDetail(ord);
		}
		if (orderEntity.getMedicine() != null) {
			Optional<Medicine> optionalCust = medicineRepository.findById(orderItem.getMedicine().getMedicineId());
			Medicine med = optionalCust.orElseThrow(() -> new InvalidDataException("Service.MEDICINE_NOT_FOUND"));
			orderEntity.setMedicine(med);
		}
		OrderItemDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public List<OrderItemDTO> showAllOrderItemsByMedicineId(Integer medicineId) throws InvalidDataException {
		Optional<Medicine> optionalmed = medicineRepository.findById(medicineId);
		Medicine med = optionalmed.orElseThrow(() -> new InvalidDataException("Service.MEDICINE_NOT_FOUND"));
		
		List<OrderItem> orders = orderItemRepository.findByMedicine(med);
		List<OrderItemDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderItemDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDER_ITEMS_NOT_FOUND");
		}
		return orderDtos;
	}

	@Override
	public OrderItemDTO deleteOrderItem(Integer orderItemId) throws InvalidDataException {
		Optional<OrderItem> optional = orderItemRepository.findById(orderItemId);
		OrderItem orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_ITEM_NOT_FOUND"));
		orderEntity.setMedicine(null);
		orderEntity.setOrderDetail(null);
		OrderItemDTO orderDto = convertEntityToDto(orderEntity);
		
		orderItemRepository.delete(orderEntity);

		return orderDto;
	}

	@Override
	public List<OrderItemDTO> showAllOrderItemsByOrderDetailId(Integer orderDetailId) throws InvalidDataException {
		Optional<OrderDetail> optionalord = orderDetailRepository.findById(orderDetailId);
		OrderDetail ord = optionalord.orElseThrow(() -> new InvalidDataException("Service.ORDER_DETAIL_NOT_FOUND"));
		
		List<OrderItem> orders = orderItemRepository.findByOrderDetail(ord);
		List<OrderItemDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderItemDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDER_ITEMS_NOT_FOUND");
		}
		return orderDtos;
	}

}

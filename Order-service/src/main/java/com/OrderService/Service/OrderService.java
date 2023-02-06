package com.OrderService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrderService.Exception.OrderServiceException;

import com.OrderService.entity.OrderDemo;
import com.OrderService.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderrepo;
	
	    public int addOrder(Long dealerId, OrderDemo order) {
	       List<OrderDemo> OrderBySamedealer = orderrepo.findByDealerId(dealerId);
	       return orderrepo.save(order).getOrderId();
	    }
	    
	    public String updateOrder(OrderDemo order, int orderId) {
			String status="Not able to update order ";
			OrderDemo orderList = orderrepo.findById(orderId).orElseThrow(
				()-> new OrderServiceException("failed to update order"));
			if(order!=null) {
				order.setFCode(order.getFCode());
				order.setIndicator(order.getIndicator());
				order.setModel(order.getModel());
				order.setSysCode(order.getSysCode());
				order.setCarName(order.getCarName());
				status=  "updated order' "+order.getCarName()+"'";
	           }
			return status;
	    }

//	    public int updateOrder(Long dealerId, int OrderId, OrderDemo order) {
//	        Optional<OrderDemo> orderList= orderrepo.findById(OrderId);
//	        if (orderList.isPresent() && dealerId.equals(orderList.get().getDealerId())) {
//	        	OrderDemo saveOrder = orderList.get();
//	            saveOrder.setFCode(order.getFCode());
//	            saveOrder.setIndicator(order.getIndicator());
//	            saveOrder.setModel(order.getModel());
//	            saveOrder.setSysCode(order.getSysCode());
//	            saveOrder.setCarName(order.getCarName());
//	            return OrderId;
//	        } else throw new OrderServiceException("failed to update order ");
//	    }

	    public int deleteOrder(Long dealerId, int OrderId) {
	        Optional<OrderDemo> orderlist = orderrepo.findById(OrderId);
	        if (orderlist.isPresent() && dealerId.equals(orderlist.get().getDealerId())) {
	        	orderrepo.deleteById(OrderId);
	            return OrderId;
	        } else throw new OrderServiceException("Unable to Delete");
	    }

	    public List<OrderDemo> getAllorderbydealerId(Long dealerId) {
	        return orderrepo.findByDealerId(dealerId);
	    }
	    
	    public List<OrderDemo> getAllOrders() {
	        return orderrepo.findAll();
	    }

	    public OrderDemo getOrderById (int orderId) {
	        return orderrepo.findById(orderId)
	                .orElseThrow(()-> new OrderServiceException("order Does not exist "));
	    }
	    

	
	
	
}
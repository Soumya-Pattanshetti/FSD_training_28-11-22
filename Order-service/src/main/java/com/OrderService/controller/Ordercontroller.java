package com.OrderService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.OrderService.Service.OrderService;
import com.OrderService.entity.OrderDemo;


@RestController
@RequestMapping("/order")
public class Ordercontroller {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/test")
	public String hello(){
		return "hello";
	}
	
		@PostMapping("/createOrder/{dealerId}")
		public ResponseEntity<Integer> createOrder(@PathVariable Long dealerId, @RequestBody OrderDemo order) {
        return ResponseEntity.ok(orderService.addOrder(dealerId, order));
    }


	    @PutMapping("/updateOrder/{OrderId}")
	    public ResponseEntity<String> updateOrder( @PathVariable int OrderId, @RequestBody OrderDemo order) {
	        return ResponseEntity.ok(orderService.updateOrder(order,OrderId));
	    }

	    @DeleteMapping("/{DealerId}/deleteOrder/{OrderId}")
	    public ResponseEntity<Integer> deleteOrder(@PathVariable Long dealerId, @PathVariable int OrderId) {
	        return ResponseEntity.ok(orderService.deleteOrder(dealerId, OrderId));
	    }

	    @GetMapping("/{DealerId}/allordersbyDealerId")
	    public ResponseEntity<List<OrderDemo>> getAllorderbydealerId(@PathVariable Long dealerId) {
	        return ResponseEntity.ok(orderService.getAllorderbydealerId(dealerId));
	    }

	    @GetMapping("/getOrderById/{orderId}")
	    public ResponseEntity<OrderDemo> getOrderById(@PathVariable int orderId) {
	        return ResponseEntity.ok(orderService.getOrderById(orderId));
	    }

	 
	    @GetMapping("/getAllOrders")
	    public ResponseEntity<List<OrderDemo>> getAllOrders() {
	        return ResponseEntity.ok(orderService.getAllOrders());
	    }

	    
	
}

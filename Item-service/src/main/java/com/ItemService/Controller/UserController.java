package com.ItemService.Controller;


import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ItemService.Entity.OrderDemo;
import com.ItemService.Entity.User;
import com.ItemService.Exception.UserServiceException;
import com.ItemService.service.UserServices;


@RestController
//@SecurityRequirement(name = "security_scheme")
@CrossOrigin(value="http://localhost:4200/")
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	UserServices userService;
	
//	@Autowired
//	private OrderDemo orderdemo;

	@GetMapping("/home")
	public String getHomePage() {

		return "Welcome "; // text
	}

	@PostMapping("/register")
	public String addUser(@RequestBody User user) throws UserServiceException {
		System.out.println( user.getUsername() +user.getPassword()+user.getRoles());
	if (null != user.getUsername() && null != user.getPassword() && null != user.getRoles()&& null != user.getEmail()) {
		return userService.addUser(user);

		} else
			throw new UserServiceException("missing User details Retry with correct details");
	}
	
	@GetMapping("/allOrders")
	  public List<Order> allAccess() {
	    List orders = this.restTemplate.getForObject("http://localhost:8085/order/getAllOrders", List.class);
	    return orders;
	  }
	
	 @PostMapping("/createOrder/{dealerId}")
	  public ResponseEntity createOrder(@PathVariable int dealerId,@RequestBody OrderDemo order) {
		    String url = "http://localhost:8085/order/createOrder"+"/"+dealerId;
		    OrderDemo orderobj = new OrderDemo();
		    orderobj.setCarName(orderobj.getCarName());
		    orderobj.setModel(orderobj.getModel());
		    //orderobj.setDate(orderobj.getDate());
		    orderobj.setFCode(orderobj.getFCode());
		    orderobj.setIndicator(orderobj.getIndicator());
		    orderobj.setDealerId(orderobj.getDealerId());
		    orderobj.setSysCode(orderobj.getSysCode());
		    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		    orderobj.setDate(timeStamp);
		    
		    HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity(orderobj,headers);
			ResponseEntity<String> status = this.restTemplate.exchange(url, HttpMethod.POST,entity, String.class);
			return status;
	  }
	
	 @PutMapping("/updateOrder/{OrderId}")
	  public ResponseEntity<String> updateOrder(@PathVariable("OrderId") Integer OrderId, @RequestBody OrderDemo order) {
			String url = "http://localhost:8085/order/updateOrder/"+OrderId;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity(order,headers);
			ResponseEntity<String> status = this.restTemplate.exchange(url, HttpMethod.PUT,entity, String.class);
			return status;
		}
	 
	 @GetMapping("/allordersbyDealerId/{DealerId}")
	  public List<OrderDemo> getbookbyauthorId(@PathVariable int DealerId){
		  String url = "http://localhost:8085/order/allordersbyDealerId"+"/"+DealerId;
		  List order = this.restTemplate.getForObject(url, List.class);
		  return order;
	  }
	 
	 @DeleteMapping("/deleteOrder/{OrderId}")
	    public ResponseEntity<String> deleteOrder( @PathVariable int OrderId) {
		 String url = "http://localhost:8085/order/deleteOrder"+"/"+OrderId;
		  List order = this.restTemplate.getForObject(url, List.class);
		  //return order;
		  HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity(order,headers);
			ResponseEntity<String> status = this.restTemplate.exchange(url, HttpMethod.DELETE,entity, String.class);
			return status;
	        //return ResponseEntity.ok(orderService.deleteOrder(dealerId, OrderId));
	    }
	  
	 
	
}
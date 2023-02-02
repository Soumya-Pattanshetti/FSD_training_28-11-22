package com.OrderService.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.OrderService.entity.OrderDemo;

@Repository
public interface OrderRepository extends JpaRepository<OrderDemo,Integer> {
	
//	List<Order> findByOrderId(int OrderId);

//	List<Order> findByOrderCarName(String carName);
	
	List<OrderDemo> findByModel(String model);
	
	List<OrderDemo> findByFCode(String FCode);
	
	List<OrderDemo> findByDealerId(Long dealerId);

	
	
	
	

}

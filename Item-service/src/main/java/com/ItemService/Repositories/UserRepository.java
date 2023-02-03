package com.ItemService.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ItemService.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByemail(String email);
	public Optional<User> findByUsername(String userName);

}
 

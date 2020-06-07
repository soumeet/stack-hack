package com.hackerearth.stackhack.usersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackerearth.stackhack.usersapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u where u.userName=?1")
	public User getByUserName(String userName);
}

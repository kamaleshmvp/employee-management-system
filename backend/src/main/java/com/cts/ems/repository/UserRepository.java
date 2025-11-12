package com.cts.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.ems.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUserName(String userName);

	Optional<User> findByUserName(String username);

}

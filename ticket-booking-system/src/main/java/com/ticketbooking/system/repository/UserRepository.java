package com.ticketbooking.system.repository;

import com.ticketbooking.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

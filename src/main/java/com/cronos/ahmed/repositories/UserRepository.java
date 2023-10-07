package com.cronos.ahmed.repositories;

import com.cronos.ahmed.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}

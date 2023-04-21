package com.FOSEM.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FOSEM.main.Entity.Users;

public interface IUserRepository extends JpaRepository<Users, Integer> {

	public Users findByUserEmail(String userEmail);
}

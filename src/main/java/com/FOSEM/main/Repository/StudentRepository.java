package com.FOSEM.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FOSEM.main.Entity.StudentRegister;

public interface StudentRepository extends JpaRepository<StudentRegister, Integer>{

}

package com.FOSEM.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FOSEM.main.Entity.Courses;

public interface ICourseRepository extends JpaRepository<Courses, Integer> {

	
}

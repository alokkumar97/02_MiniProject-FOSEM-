package com.FOSEM.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FOSEM.main.Entity.EnquiryStatus;

public interface IEnquiryRepository extends JpaRepository<EnquiryStatus, Integer> {

}

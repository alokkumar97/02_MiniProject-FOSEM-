package com.FOSEM.main.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="enquiry_tab")
public class EnquiryStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="enq_id")
	private Integer enqId;
	@Column(name="enq_status")
	private String enqStatus;
}

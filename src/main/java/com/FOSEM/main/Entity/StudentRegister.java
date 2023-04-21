package com.FOSEM.main.Entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="student_register_tab")
public class StudentRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="s_id_col")
	private Integer enrollId;
	
	@Column(name="s_name_col")
	private String stdName;
	
	@Column(name="s_contact_no_col")
	private Long phoneNo;
	
	@Column(name="s_class_mode_col")
	private String classMode;
	
	@Column(name="s_course_col")
	private String courseName;
	
	@Column(name="s_enquiry_status_col")
	private String enquiryStatus;
	
	@Column(name="s_enquiry_date_col")
	@CreationTimestamp
	private LocalDate enquiryDate;
	
	@Column(name="s_eupdate_date_col")
	@UpdateTimestamp
	private LocalDate updateDate;
	
	@ManyToOne()
	@JoinColumn(name="usr_id_col")
	private Users user;
}

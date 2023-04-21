package com.FOSEM.main.Entity;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users_tab")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usr_id_col")
	private Integer uid;
	
	@Column(name="usr_name_col")
	private String userName;
	
	@Column(name="usr_email_col", unique = true)
	private String userEmail;
	
	@Column(name="usr_phone_col")
	private Long contactNo;
	
	@Column(name="usr_password_col")
	private String password;
	
	@Column(name="usr_acc_status_col")
	private String accountStatus;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<StudentRegister> sid;
	
}

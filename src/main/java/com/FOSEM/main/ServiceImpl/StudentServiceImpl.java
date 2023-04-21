package com.FOSEM.main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FOSEM.main.DTO.DashBoardResponse;
import com.FOSEM.main.DTO.EnquiryForm;
import com.FOSEM.main.DTO.EnquirySearchCriteria;
import com.FOSEM.main.Entity.Courses;
import com.FOSEM.main.Entity.EnquiryStatus;
import com.FOSEM.main.Repository.StudentRepository;
import com.FOSEM.main.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository stdRepo;
	
	@Override
	public DashBoardResponse res(Integer userid) {
		
		return null;
	}

	@Override
	public String addEnquiries(EnquiryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnquiryForm> viewEnquries(Integer userid, EnquirySearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Courses> getAllCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnquiryStatus> getAllStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}

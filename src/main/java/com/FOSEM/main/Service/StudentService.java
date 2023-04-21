package com.FOSEM.main.Service;

import java.util.List;

import com.FOSEM.main.DTO.DashBoardResponse;
import com.FOSEM.main.DTO.EnquiryForm;
import com.FOSEM.main.DTO.EnquirySearchCriteria;
import com.FOSEM.main.Entity.Courses;
import com.FOSEM.main.Entity.EnquiryStatus;

public interface StudentService {

	public DashBoardResponse res(Integer userid);
	
	public String addEnquiries(EnquiryForm form);
	
	public List<EnquiryForm> viewEnquries(Integer userid, EnquirySearchCriteria criteria);
	
	public List<Courses> getAllCourse();
	
	public List<EnquiryStatus> getAllStatus();
	
}

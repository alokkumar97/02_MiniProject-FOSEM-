package com.FOSEM.main.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FOSEM.main.DTO.DashBoardResponse;
import com.FOSEM.main.DTO.EnquiryForm;
import com.FOSEM.main.DTO.EnquirySearchCriteria;
import com.FOSEM.main.Entity.Courses;
import com.FOSEM.main.Entity.EnquiryStatus;
import com.FOSEM.main.Entity.StudentRegister;
import com.FOSEM.main.Entity.Users;
import com.FOSEM.main.Repository.ICourseRepository;
import com.FOSEM.main.Repository.IEnquiryRepository;
import com.FOSEM.main.Repository.IUserRepository;
import com.FOSEM.main.Repository.StudentRepository;
import com.FOSEM.main.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository stdRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private ICourseRepository courseRepo;
	
	@Autowired
	private IEnquiryRepository enqRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public DashBoardResponse getTotalEnquries(Integer userid) {
		
		DashBoardResponse dashboardData= new DashBoardResponse();
		Optional<Users> opt = userRepo.findById(userid);
		if(opt.isPresent()){
			Users userEntity = opt.get();
			List<StudentRegister> enquries = userEntity.getEnquries();
			Integer totalCnt = enquries.size();
			Integer enrolledCnt = enquries.stream()
																.filter(e -> e.getEnquiryStatus().contains("Enrolled"))
																.collect(Collectors.toList()).size();
			Integer lostCnt = enquries.stream()
														.filter(e -> e.getEnquiryStatus().contains("Lost"))
														.collect(Collectors.toList()).size();
			dashboardData.setTotalEnqCnt(totalCnt);
			dashboardData.setEnrolledCnt(enrolledCnt);
			dashboardData.setLostCnt(lostCnt);
		}				
		return dashboardData;
	}
	
	@Override
	public List<String> getAllCourse() {
		List<Courses> courses=courseRepo.findAll();
		List<String> courseNames =courses.stream()
																	.map(c -> c.getCourseName())
																	.collect(Collectors.toList());
		System.out.println(courseNames);
		return courseNames;
	}

	@Override
	public List<String> getAllStatus() {
		List<EnquiryStatus> status = enqRepo.findAll();
		List<String> statusName = status.stream().map(s -> s.getEnqStatus()).collect(Collectors.toList());
		System.out.println(statusName);
		return statusName;
	}
	
	@Override
	public String addEnquiries(EnquiryForm form) {
		StudentRegister addEnq = new StudentRegister();
		BeanUtils.copyProperties(form, addEnq);
		Integer userId =(Integer) session.getAttribute("uid");
		Users usrId = userRepo.findById(userId).get();
		addEnq.setUser(usrId);
		stdRepo.save(addEnq);		
		return "Success";
	}
	
	@Override
	public List<EnquiryForm> viewEnquries(Integer userid, EnquirySearchCriteria criteria) {
		stdRepo.findAll();
		return null;
	}


}

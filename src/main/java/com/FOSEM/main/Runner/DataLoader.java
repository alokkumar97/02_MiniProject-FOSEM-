package com.FOSEM.main.Runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.FOSEM.main.Entity.Courses;
import com.FOSEM.main.Entity.EnquiryStatus;
import com.FOSEM.main.Entity.StudentRegister;
import com.FOSEM.main.Entity.Users;
import com.FOSEM.main.Repository.ICourseRepository;
import com.FOSEM.main.Repository.IEnquiryRepository;
import com.FOSEM.main.Repository.IUserRepository;
import com.FOSEM.main.Repository.StudentRepository;

@Component
public class DataLoader implements CommandLineRunner{

	
	@Autowired
	private ICourseRepository courseRepository;
	
	@Autowired
	private IEnquiryRepository enquiryRepository;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private StudentRepository stdRepo;

	@Override
	public void run(String... args) throws Exception {
		
		Courses c1 = new Courses();
		c1.setCid(1);
		c1.setCourseName("AWS");
		
		Courses c2 = new Courses();
		c2.setCid(2);
		c2.setCourseName("DevOps with AWS");
		
		Courses c3 = new Courses();
		c3.setCid(3);
		c3.setCourseName("Python");
		
		Courses c4 = new Courses();
		c4.setCid(4);
		c4.setCourseName("Azure DevOps");
		
		Courses c5 = new Courses();
		c5.setCid(5);
		c5.setCourseName("Full Stack Java");
		
		Courses c6 = new Courses();
		c6.setCid(6);
		c6.setCourseName("Machine Learning");
		
		Courses c7 = new Courses();
		c7.setCid(7);
		c7.setCourseName("Oracle");
		
		Courses c8 = new Courses();
		c8.setCid(8);
		c8.setCourseName("Angular");
		
		List<Courses> list=Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8);
		courseRepository.saveAll(list);
		
		
		EnquiryStatus es = new EnquiryStatus();
		es.setEnqId(1);
		es.setEnqStatus("New");
		
		EnquiryStatus es2 = new EnquiryStatus();
		es2.setEnqId(2);
		es2.setEnqStatus("Enrolled");
		
		EnquiryStatus es3 = new EnquiryStatus();
		es3.setEnqId(3);
		es3.setEnqStatus("Lost");
		
		List<EnquiryStatus> list2=Arrays.asList(es,es2,es3);
		enquiryRepository.saveAll(list2);
		
		
		/*Users u1= new Users();
		u1.setUid(1);
		u1.setUserName("Alok");
		u1.setUserEmail("alok@gmail.com");
		u1.setContactNo(9090177321l);
		u1.setAccountStatus("unlocked");
		userRepo.save(u1);
		
		Users u2= new Users();
		u2.setUid(2);
		u2.setUserName("AlokP");
		u2.setUserEmail("alokp@gmail.com");
		u2.setContactNo(9090177322l);
		u2.setAccountStatus("unlocked");
		userRepo.save(u2);*/
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

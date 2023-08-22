package com.cjc.main.ServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.Repository.HomeRepository;
import com.cjc.main.ServiceI.HomeServiceI;

import com.cjc.main.exceptionE.EmployeeNotFoundException;
import com.cjc.main.model.EmployeeInfo;

@Service
public class HomeServiceImpl implements HomeServiceI {
	
	@Autowired
	HomeRepository hr;
	
	@Value("$(spring.mail.username)")
	private String fromMail;
	
	@Autowired
	private JavaMailSender sendr;
	

	@Override
	public EmployeeInfo saveEmp(EmployeeInfo readValue, MultipartFile profilephoto) {
		
		
		try {
			readValue.setProfilephoto(profilephoto.getBytes());
			Random random =new Random(777);
			readValue.setUsername(readValue.getEname()+random.nextInt(999));
			
			readValue.setPassword(readValue.getEname()+"@"+random.nextInt(2000));
			
			return hr.save(readValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

	@Override
	public Iterable<EmployeeInfo> getEmployee() {
		
		
		return hr.findAll();
	}

	@Override
	public void sendMail(EmployeeInfo employeeInfo) {
		
		SimpleMailMessage simplemsg=new SimpleMailMessage();
		simplemsg.setFrom(fromMail);
		simplemsg.setTo(employeeInfo.getEmpEmail());
		simplemsg.setSubject("Apna Finance");
		simplemsg.setText(
				"Hello "+employeeInfo.getEname()+",\n   Below are the login credential for Apna Finance, kindly note "
						+ "your username is :- "+employeeInfo.getUsername()+"and Password is :-"+employeeInfo.getPassword()
				);
		try {
		sendr.send(simplemsg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Iterable<EmployeeInfo> getallemployeepagable(int pagenumber) {
		
           Sort sort=Sort.by("ename");
		
		Pageable page=PageRequest.of(pagenumber,2,sort);
		
		return hr.findAll(page);
	
	}

	@Override
	public List<EmployeeInfo> findEmployeeByName(String ename) throws EmployeeNotFoundException{
		
		 List<EmployeeInfo> emp= hr.findAllByEname(ename);
		 if(emp.size()!=0) {
		      
		      return emp;
		
	
	}
		 else {
			 throw new EmployeeNotFoundException("No Such Employee In DataBase With Name"+ename);
		 }
		
	}

	@Override
	public EmployeeInfo getempl(String username, String password) {
		
		
		return hr.findAllByUsernameAndPassword(username, password);
	}

}

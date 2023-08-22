package com.cjc.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.cjc.main.ServiceI.HomeServiceI;
import com.cjc.main.exceptionE.EmployeeNotFoundException;
import com.cjc.main.model.BaseResponce;
import com.cjc.main.model.EmployeeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
@RequestMapping("/Admin")
public class Homecontroller {
	
	@Autowired
	HomeServiceI hs;
	
	@RequestMapping("/saveEmployee")
	public ResponseEntity<EmployeeInfo> createEmployee(@RequestPart(value="profileImg", required=true) MultipartFile profilephoto,
			@RequestPart ("empolyee") String employeeJson)
	{
		ObjectMapper om=new ObjectMapper();
		   try {
			EmployeeInfo readValue = om.readValue(employeeJson, EmployeeInfo.class);
			       EmployeeInfo udb =hs.saveEmp(readValue,profilephoto );
			       return new ResponseEntity<EmployeeInfo>(udb, HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GetMapping("/getEmployee")
	public Iterable<EmployeeInfo> getEmployee()
	{
		return hs.getEmployee();
	}
	
	@RequestMapping("/sendMail")
	public String sendMail(@RequestBody EmployeeInfo employeeInfo)
	{
		System.out.println(employeeInfo);
		hs.sendMail(employeeInfo);
		
		return "send";
	}
	
	
	@GetMapping("/getallemployeepagable/{pagenumber}")
	public Iterable<EmployeeInfo> getallemployeepagable(@PathVariable int pagenumber)
	{
		Iterable<EmployeeInfo> list= hs. getallemployeepagable(pagenumber);
		
		return list;
		  
	}
	

	@GetMapping("/{ename}")
	  public ResponseEntity<BaseResponce<List<EmployeeInfo>>> findEmployee(@PathVariable("ename") String ename) throws EmployeeNotFoundException{
	    
	    List<EmployeeInfo> searchedEmployee;
	    
	    searchedEmployee = hs.findEmployeeByName(ename);
	    
	    return new ResponseEntity<BaseResponce<List<EmployeeInfo>>>(new BaseResponce<List<EmployeeInfo>>(200, "Search Result ",new Date(),searchedEmployee ),HttpStatus.OK);
	  }
	
	@GetMapping("/getemp/{username}/{password}")
	public ResponseEntity<BaseResponce<EmployeeInfo>>getemp(@PathVariable String username ,
															@PathVariable String password
																			){
		
		EmployeeInfo e2=hs.getempl(username,password);
		
		return new ResponseEntity<BaseResponce<EmployeeInfo>>(new BaseResponce<EmployeeInfo>(200, "user Foundne",
												new Date(), e2),HttpStatus.OK);
		
	}
	

}

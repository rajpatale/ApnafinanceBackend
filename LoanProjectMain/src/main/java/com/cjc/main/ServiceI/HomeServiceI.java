package com.cjc.main.ServiceI;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.exceptionE.EmployeeNotFoundException;
import com.cjc.main.model.EmployeeInfo;

public interface HomeServiceI {

	EmployeeInfo saveEmp(EmployeeInfo readValue, MultipartFile profilephoto);

	Iterable<EmployeeInfo> getEmployee();

	void sendMail(EmployeeInfo employeeInfo);

	Iterable<EmployeeInfo> getallemployeepagable(int pagenumber);

	List<EmployeeInfo> findEmployeeByName(String ename) throws EmployeeNotFoundException;

	EmployeeInfo getempl(String username, String password);

}

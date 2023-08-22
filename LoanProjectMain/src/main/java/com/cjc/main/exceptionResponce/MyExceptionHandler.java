package com.cjc.main.exceptionResponce;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cjc.main.exceptionE.EmployeeNotFoundException;
import com.cjc.main.exceptionE.EnquiryNotSoundException;
import com.cjc.main.model.BaseResponce;
import com.cjc.main.model.EmployeeInfo;
import com.cjc.main.model.EnquiryDetails;

public class MyExceptionHandler {
	

	@ExceptionHandler(value=EmployeeNotFoundException.class)
	public ResponseEntity< BaseResponce<EmployeeInfo>> handleUserNotFound()
	{
		return new ResponseEntity<BaseResponce<EmployeeInfo>> (new BaseResponce<EmployeeInfo>(404, "user Not Found", new Date(), null),HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(value=EnquiryNotSoundException.class)
	public ResponseEntity< BaseResponce<EnquiryDetails>> handleEnquiryrNotFound()
	{
		return new ResponseEntity<BaseResponce<EnquiryDetails>> (new BaseResponce<EnquiryDetails>(404, "user Not Found", new Date(), null),HttpStatus.NOT_FOUND);
	}
}

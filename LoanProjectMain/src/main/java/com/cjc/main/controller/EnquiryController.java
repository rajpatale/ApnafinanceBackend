package com.cjc.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cjc.main.ServiceI.EnquiryServiceI;
import com.cjc.main.exceptionE.EmployeeNotFoundException;
import com.cjc.main.exceptionE.EnquiryNotSoundException;
import com.cjc.main.model.BaseResponce;
import com.cjc.main.model.EmployeeInfo;
import com.cjc.main.model.EnquiryDetails;

@CrossOrigin("*")
@RestController
@RequestMapping("/Enquiry")
public class EnquiryController {
	
	@Autowired
	RestTemplate rs;

	@Autowired
	EnquiryServiceI es;

	@RequestMapping("/createEnquiry")
	public ResponseEntity<BaseResponce<EnquiryDetails>> createEnquiry(@RequestBody EnquiryDetails e) {
		EnquiryDetails enquiry = es.createEnquiry(e);

		return new ResponseEntity<BaseResponce<EnquiryDetails>>(
				new BaseResponce<EnquiryDetails>(201, "User Created", new Date(), enquiry), HttpStatus.CREATED);

	}

	@GetMapping("/getallEnquirypagable/{pagenumber}")
	public Iterable<EnquiryDetails> getallEnquirypagable(@PathVariable int pagenumber) {
		Iterable<EnquiryDetails> list = es.getallEnquirypagable(pagenumber);

		return list;

	}

	@GetMapping("/findFirstName{firstName}")
	public ResponseEntity<BaseResponce<List<EnquiryDetails>>> findFirstName(@PathVariable("firstName") String firstName)
			throws EnquiryNotSoundException {

		List<EnquiryDetails> searchedEnquiry;

		searchedEnquiry = es.findEnquiryByFirstName(firstName);

		return new ResponseEntity<BaseResponce<List<EnquiryDetails>>>(
				new BaseResponce<List<EnquiryDetails>>(200, "Search Result ", new Date(), searchedEnquiry),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteEnquirydata/{eId}")
	public String deleteData(@PathVariable("eId") int eid) {
		es.deleteData(eid);
		return "success";
	}

	@GetMapping("/enquirysByStatus/{status1}/{status2}")
	public Iterable<EnquiryDetails> getEnquirysonStatus(@PathVariable ("status1") String status1, @PathVariable("status2") String status2) {
		return es.enquirysByStatus(status1,status2);

	}
	
	@PutMapping("/updateUserStatus/{eId}")
	public EnquiryDetails updateUserStatus(@PathVariable ("eId") int eid  )
	{
		
		EnquiryDetails e= es.updateUserStatus(eid);
		return e;
	}
	
	@GetMapping("/checkCibil/{pancardNumber}")
	public int checkCibil(@PathVariable("pancardNumber") String pancardNumber)
	{
		String url="http://localhost:8080/getCibilScore/"+pancardNumber;
		Integer cibilScore=rs.getForObject(url, Integer.class);
		return cibilScore;
				
		
	}
	

    

}



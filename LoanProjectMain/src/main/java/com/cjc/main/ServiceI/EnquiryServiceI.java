package com.cjc.main.ServiceI;

import java.util.List;

import com.cjc.main.exceptionE.EmployeeNotFoundException;
import com.cjc.main.exceptionE.EnquiryNotSoundException;
import com.cjc.main.model.EnquiryDetails;

public interface EnquiryServiceI {

	EnquiryDetails createEnquiry(EnquiryDetails e);

	Iterable<EnquiryDetails> getallEnquirypagable(int pagenumber);

	List<EnquiryDetails> findEnquiryByFirstName(String firstName) throws EnquiryNotSoundException;

	void deleteData(int eid);

	Iterable<EnquiryDetails> enquirysByStatus(String status1, String status2);

}

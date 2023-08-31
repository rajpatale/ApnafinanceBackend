package com.cjc.main.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cjc.main.Repository.EnquiryRepository;
import com.cjc.main.ServiceI.EnquiryServiceI;
import com.cjc.main.enums.EnquiryStatus;
import com.cjc.main.exceptionE.EnquiryNotSoundException;
import com.cjc.main.model.EmployeeInfo;
import com.cjc.main.model.EnquiryDetails;

@Service
public class EnquiryServiceImpl implements EnquiryServiceI{

	@Autowired
	EnquiryRepository er;
	
	
	@Override
	public EnquiryDetails createEnquiry(EnquiryDetails e) {
		
		e.setEnquiryStatus(String.valueOf(EnquiryStatus.CREATED));
		
		
		return er.save(e);
	}


	@Override
	public Iterable<EnquiryDetails> getallEnquirypagable(int pagenumber) {
		
        Sort sort=Sort.by("firstName");
		
		Pageable page=PageRequest.of(pagenumber,2,sort);
		
		return er.findAll(page);
	
		

	}


	@Override
	public List<EnquiryDetails> findEnquiryByFirstName(String firstName) throws EnquiryNotSoundException {
		 List<EnquiryDetails> enquiry= er.findAllByFirstName(firstName);
		 if(enquiry.size()!=0) {
		      
		      return enquiry;
		
	
	}
		 else {
			 throw new EnquiryNotSoundException("No Such Enquiry In DataBase With Name"+firstName);
		 }
		
		
	}


	@Override
	public void deleteData(int eid) {
        er.deleteById(eid);		
	}


	@Override
	public Iterable<EnquiryDetails> enquirysByStatus(String status1, String status2) {
      
		if(status2.length()<3)
		{
			
			return er.findAllByEnquiryStatus(status1);
		}
		else
		{
			return er.findAllByEnquiryStatusOrEnquiryStatus(status1,status2);
		}
		
	
	}

	
	

	@Override
	public EnquiryDetails updateUserStatus(int eid) {

		Optional<EnquiryDetails> findById = er.findById(eid);
		EnquiryDetails enquiryDetails = findById.get();
		
		enquiryDetails.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_REQUIRED));
		
		er.save(enquiryDetails);
		
		
		return enquiryDetails;
	}

}

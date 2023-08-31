package com.cjc.main.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cjc.main.Repository.EnquiryRepository;
import com.cjc.main.ServiceI.EnquiryServiceI;
import com.cjc.main.enums.Cibil_Status;
import com.cjc.main.enums.EnquiryStatus;
import com.cjc.main.exceptionE.EnquiryNotSoundException;
import com.cjc.main.model.Cibil;
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
	public EnquiryDetails updateUserStatus(int eid , EnquiryDetails ed) {

		System.out.println("In Service Impl");
		
		EnquiryDetails e = er.findByEid(eid);
		if(e.getCibil()==null)
		{
			e.setCibil(new Cibil());
		}
		String enquiryStatus = e.getEnquiryStatus();
		if(enquiryStatus.equals("CREATED"))
		{
			
			e.setEnquiryStatus(EnquiryStatus.CIBIL_REQUIRED.toString());
		
			er.save(e);
			return e;
		}else if(enquiryStatus.equals("CIBIL_REQUIRED"))
		{
			
			e.setEnquiryStatus(EnquiryStatus.CIBIL_CHECKED.toString());
			e.getCibil().setCibilScore(ed.getCibil().getCibilScore());;
			if(e.getCibil().getCibilScore()<650)
			{
				e.getCibil().setCibilRemark("cibil score is low");
				e.getCibil().setCibilStatus(Cibil_Status.LOW_CIBIL.toString());
				e.getCibil().setCibilScoreDateTime(new Date().toString());
				return er.save(e);
			}
			else if(e.getCibil().getCibilScore()<750 && e.getCibil().getCibilScore()>650)
			{
				e.getCibil().setCibilRemark("cibil score is good");
				e.getCibil().setCibilStatus(Cibil_Status.AVRAGE_CIBIL.toString());
				e.getCibil().setCibilScoreDateTime(new Date().toString());
				return er.save(e);
			}
			else if(e.getCibil().getCibilScore()>750 && e.getCibil().getCibilScore()<900)
			{
				e.getCibil().setCibilRemark("cibil score is high");
				e.getCibil().setCibilStatus(Cibil_Status.HIGH_CIBIL.toString());
				e.getCibil().setCibilScoreDateTime(new Date().toString());
				return er.save(e);
			}else{
				//throw CibilCoreNotApplicabelException
				
			}
		
	
		}
		 if (enquiryStatus.equals("CIBIL_CHECKED")) {
			if(e.getCibil().getCibilScore()>650)
			{
				e.setEnquiryStatus(EnquiryStatus.CIBIL_APROVED.toString());
				er.save(e);
				return e;
			}else {
				e.setEnquiryStatus(EnquiryStatus.CIBIL_REJECT.toString());
				er.save(e);
				return e;
			}
			
		}
		return null;
	}

}

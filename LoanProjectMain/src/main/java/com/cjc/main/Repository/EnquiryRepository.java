package com.cjc.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.cjc.main.model.EnquiryDetails;

@Repository
public interface EnquiryRepository  extends JpaRepository<EnquiryDetails, Integer> {

	List<EnquiryDetails> findAllByFirstName(String firstName);

	Iterable<EnquiryDetails> findAllByEnquiryStatus(String status1);

	Iterable<EnquiryDetails> findAllByEnquiryStatusOrEnquiryStatus(String status1, String status2);

	EnquiryDetails findByEid(int eid);

}

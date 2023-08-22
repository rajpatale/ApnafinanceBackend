package com.cjc.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanctionLetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		Integer sanctionId;
		String sanctionDate;
		String applicantName;
	 	Double  contactDetails;
		Double  loanAmtSanctioned;
		int  rateOfInterest;
		int  loanTenure;
		Double  monthlyEmiAmount;
		String  sanctionStatus;


}

package com.cjc.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentLoanDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private 	Integer currentloanId;	
private	Integer currentLoanNumber;

@OneToOne(cascade = CascadeType.ALL)
private	EMIDetails emiDetails;	
private Double loanAmount;	
private Integer rateOfInterest;	
private Integer tenure;	
private	 Double totalAmountToBePaidDouble;	
private Integer processingFees;	
private Double totalInterest;	
private String sanctionDate;	
private String remark;	
private String status;	


}

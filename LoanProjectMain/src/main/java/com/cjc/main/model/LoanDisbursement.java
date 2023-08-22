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
public class LoanDisbursement {
	
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
		Integer agreementId;
		Integer loanNo;
		String agreementDate;
		Double totalAmount;
		String bankName;
 		Long  accountNumber;
		Double transferAmount;
		String paymentStatus;
		String amountPaidDate;


}

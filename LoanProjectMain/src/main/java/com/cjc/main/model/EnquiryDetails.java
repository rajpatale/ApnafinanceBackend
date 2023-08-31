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
public class EnquiryDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eid;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Double mobileNumber;
	private Double pancardNumber;
	private String enquiryStatus;
	

	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibil;

}

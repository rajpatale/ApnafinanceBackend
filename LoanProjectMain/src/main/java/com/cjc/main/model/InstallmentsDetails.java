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
public class InstallmentsDetails {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer installmentsId ;
		private String installmentsDate;
		private Double installmentsAmount;


}

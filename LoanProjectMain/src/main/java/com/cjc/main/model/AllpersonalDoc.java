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
public class AllpersonalDoc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int documentId;
	 private byte[] addressProof;
	 private byte[] panCard;
	 private byte[] addharCard;
	 private byte[] photo;
	  private byte[ ]signature;
	  private byte[] salarySlips;

}

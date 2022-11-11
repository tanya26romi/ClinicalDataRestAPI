package com.tanya.ClinicalRestApi.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String lastName;
	private String firstName;
	private int age;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="patient")
	private List<ClinicalData> clinicalData;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<ClinicalData> getClinicalData() {
		return clinicalData;
	}
	public void setClinicalData(List<ClinicalData> clinicalData) {
		this.clinicalData = clinicalData;
	}
//	@Override
//	public String toString() {
//		return "Patient [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", age=" + age
//				+ ", clinicalData=" + clinicalData + "]";
//	}
//	@Override
//	public String toString() {
//		return "Patient [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", age=" + age
//				+ ", clinicalData=" + clinicalData + "]";
//	}
	
}

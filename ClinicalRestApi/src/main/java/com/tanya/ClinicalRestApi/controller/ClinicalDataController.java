package com.tanya.ClinicalRestApi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanya.ClinicalRestApi.dto.ClinicalDataRequest;
import com.tanya.ClinicalRestApi.model.ClinicalData;
import com.tanya.ClinicalRestApi.model.Patient;
import com.tanya.ClinicalRestApi.repos.ClinicalDataRepository;
import com.tanya.ClinicalRestApi.repos.PatientRepository;
import com.tanya.ClinicalRestApi.util.BMICalculator;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {
	
	@Autowired
	private ClinicalDataRepository clinicalDataRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@PostMapping("/clinicals")
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest clinicalDataRequest) {//data with patient id, component name and value is being passed so custom class is created
		Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
		ClinicalData data = new ClinicalData();
		data.setComponentName(clinicalDataRequest.getComponentName());
		data.setComponentValue(clinicalDataRequest.getComponentValue());
		//System.out.print(patient.getFirstName());
		//System.out.print(patient.getLastName());
		data.setPatient(patient);
		System.out.println(data.getPatient().getFirstName());
		return clinicalDataRepository.save(data);
	}
	
	@GetMapping("/clinicals/{patientId}/{componentName}")
	public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId, @PathVariable("componentName") String componentName){
		if(componentName.equals("bmi")) {
			componentName = "hw";
		}
		
		List<ClinicalData> clinicalData = clinicalDataRepository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
		ArrayList<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
		for(ClinicalData eachEntry : duplicateClinicalData) {
			BMICalculator.calculateBMI(clinicalData, eachEntry);
		}
		
		return clinicalData;
	}
}

package com.tanya.ClinicalRestApi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.tanya.ClinicalRestApi.model.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

	//@Query("from clinicaldata where patient_id=:patientId and component_name=:componentName orderby measured_date_time")
	List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime( int patientId, String componentName);

}

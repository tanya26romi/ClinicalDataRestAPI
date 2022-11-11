package com.tanya.ClinicalRestApi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanya.ClinicalRestApi.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}

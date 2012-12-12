package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.Patient;

@Component
public class PatientDao extends MappedModelDao<Patient> {

	@Override
	public Class<Patient> getActualClass() {
		// TODO Auto-generated method stub
		return Patient.class;
	}
}

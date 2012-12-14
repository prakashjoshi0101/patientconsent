package com.me.src.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.src.pojo.Patient;

@Component
public class PatientDao extends MappedModelDao<Patient> {

	@Override
	public Class<Patient> getActualClass() {
		// TODO Auto-generated method stub
		return Patient.class;
	}
	
	//nihar changes
	@SuppressWarnings("unchecked")
	public List<Patient> listPatient(int hospitalId) {
		// TODO Auto-generated method stub
		
//		Query query = (Query) sessionFactory.getCurrentSession().createQuery("from Person where hospital_id=:hospitalid");
//		query.setParameter("hospitalid", hospitalId);		
//		return query.list();
		
		Query query = (Query) sessionFactory.getCurrentSession().createQuery("from Person p join Patient pa where p.hospital_id=:hospitalid and p.id=pa.person_id");
		query.setParameter("hospitalid", hospitalId);		
		return query.list();
		
	}
	//nihar changes
}

package com.hospitalProject.dao;

import java.util.List;
import java.util.Map;

import com.hospitalProject.beans.Consultation;
import com.hospitalProject.beans.Patients;
import com.hospitalProject.beans.StoryPatient;

public interface PatientDao {

	List<Patients> ListPatient(String search) throws DaoException;
	void ajouterPatient(Patients patient) throws DaoException;
	int CountPatient(String name, String year) throws DaoException;
	List<Consultation>  SearchPatient(String search) throws DaoException;
	int trouver(String identifiant) throws DaoException;
	List<StoryPatient> storyPatient(String idp) throws DaoException;
	List<Consultation> SearchAllPatient(String search) throws DaoException;
	
}

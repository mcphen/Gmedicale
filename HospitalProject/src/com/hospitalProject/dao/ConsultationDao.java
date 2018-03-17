package com.hospitalProject.dao;

import com.hospitalProject.beans.Consultation;
import com.hospitalProject.beans.Visites;

public interface ConsultationDao {

	void ajouterConsultation(Consultation consultation, Visites visite) throws DaoException;
	int CountPatient() throws DaoException;
	 void modificationConsultation(Consultation  consultation) throws DaoException;
}

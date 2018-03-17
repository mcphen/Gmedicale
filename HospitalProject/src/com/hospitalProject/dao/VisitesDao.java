package com.hospitalProject.dao;

import java.util.List;

import com.hospitalProject.beans.Consultation;
import com.hospitalProject.beans.Visites;

public interface VisitesDao {

	int CountVisites() throws DaoException;
	List<Visites> listVisiteByCons(int idConsultation) throws DaoException ;
	int countOrder(int idConsultation) throws DaoException;
	void ajouterVisite(Visites visites) throws DaoException;
	public Consultation listConsultation(int idConsultation) throws DaoException;
}

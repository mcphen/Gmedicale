package com.hospitalProject.dao;

import com.hospitalProject.beans.Fonctions;
import java.util.List;
public interface FonctionDao {
	List <Fonctions> listFonction() throws DaoException;
	void ajouterPathologie(Fonctions fonctions) throws DaoException;
	List<Fonctions> listPathologie() throws DaoException;
}

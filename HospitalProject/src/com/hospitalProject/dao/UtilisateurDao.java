package com.hospitalProject.dao;



import java.util.List;
import com.hospitalProject.dao.DaoException;
import com.hospitalProject.beans.Patients;
import com.hospitalProject.beans.Users;

public interface UtilisateurDao {
	List<Users> ListUtilisateur() throws DaoException;
    void ajouter( Users utilisateur ) throws DaoException;
  //  int CountUtilisateur() throws DaoException ;
    Users LoginUtilisateur(String login, String mdp ) throws DaoException;
    List<Patients> ProchainRdv(int utilisateur)  throws DaoException ;
    
}
package com.hospitalProject.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hospitalProject.beans.Consultation;
import com.hospitalProject.beans.Visites;

public class ConsultationDaoImpl implements ConsultationDao {

	private DaoFactory daoFactory;
	/*
	 * Constructeur de la class implementant la class DaoFactory qui contient la connexion à la base de donnees
	 */
	ConsultationDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	
	
	public int CountPatient() throws DaoException {
		int count = 0;
		Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT COUNT(*) AS total FROM consultations ");
            while(resultat.next()){
                count = resultat.getInt("total");
                }
            

        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        } 
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
	    return count;
	}
	
	public void ajouterConsultation(Consultation consultation, Visites visite) throws DaoException {
		Connection connexion = null;   
        PreparedStatement preparedStatement = null;
        PreparedStatement pstmt = null;
        
        
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO `consultations`( `datedebutC`, `diagnostique`, `traitement`, `etatTraitement`, `idpatients`, `numConsultations`, `idUsers`) VALUES (NOW(),?,?,?,?,?,?)");
         
            preparedStatement.setString(1, consultation.getDiagnostique());
            preparedStatement.setString(2, consultation.getTraitement());
            preparedStatement.setString(3, consultation.getEtatTraitement());
            preparedStatement.setString(4, consultation.getIdPatientCons());
            preparedStatement.setString(5, consultation.getNumConsultation());
            preparedStatement.setInt(6, consultation.getIdUserCons());
            preparedStatement.executeUpdate();
            pstmt = connexion.prepareStatement("INSERT INTO `visites`( datevisite,numvisites, datenext, ordrevisite, statut, symptome, ordonnance, idConsultations) VALUES (NOW(),?,?,?,?,?,?,?)");
            pstmt.setString(1, visite.getNumVisites());
            pstmt.setString(2, visite.getDateRv());
            pstmt.setInt(3,visite.getOrdre());
            pstmt.setString(4, visite.getStatut());
            pstmt.setString(5, visite.getSymptome());
            pstmt.setString(6, visite.getOrdonnance());
            pstmt.setInt(7, visite.getIdConsultation());
            pstmt.executeUpdate();
            connexion.commit();
        }catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            e.printStackTrace();
        }
	}
	
	public void modificationConsultation(Consultation  consultation) throws DaoException {
		Connection connexion = null;
	    
        PreparedStatement preparedStatement = null;
        PreparedStatement psmt = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE `consultations` SET `diagnostique`=?,`traitement`=?,`etatTraitement`=? WHERE `idConsultations`=?");
            preparedStatement.setString(1, consultation.getDiagnostique());
            
            preparedStatement.setString(2, consultation.getTraitement());
            preparedStatement.setString(3, consultation.getEtatTraitement());
            preparedStatement.setInt(4, consultation.getIdUserCons());
           
            
            preparedStatement.executeUpdate();
            
            psmt = connexion.prepareStatement("UPDATE `visites` SET `datenext`=? WHERE ordrevisite=0 AND idConsultations=?");
            psmt.setString(1, consultation.getDateRv());
            psmt.setInt(2, consultation.getIdUserCons());
            psmt.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
	}
}

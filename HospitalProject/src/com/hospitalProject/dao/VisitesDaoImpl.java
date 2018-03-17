package com.hospitalProject.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hospitalProject.beans.Consultation;
import com.hospitalProject.beans.Fonctions;
import com.hospitalProject.beans.Users;
import com.hospitalProject.beans.Visites;
public class VisitesDaoImpl implements VisitesDao {

	private DaoFactory daoFactory;
	/*
	 * Constructeur de la class implementant la class DaoFactory qui contient la connexion à la base de donnees
	 */
	
	VisitesDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	
	/*
	 * fonction inserer les données dans la table visites
	 */
	
	public void ajouterVisite(Visites visites) throws DaoException{
		Connection connexion = null;
	    
        PreparedStatement preparedStatement = null;
        
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO `visites`(`numvisites`, `datevisite`, `datenext`, `ordrevisite`, `statut`, `symptome`, `ordonnance`, `idConsultations`) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, visites.getNumVisites());
            preparedStatement.setString(2,visites.getDateVisite());
            preparedStatement.setString(3, visites.getDateRv());
            preparedStatement.setInt(4, visites.getOrdre());
            preparedStatement.setString(5, visites.getStatut());
            preparedStatement.setString(6, visites.getSymptome());
            preparedStatement.setString(7, visites.getOrdonnance());
            preparedStatement.setInt(8, visites.getIdConsultation());
            
            preparedStatement.executeUpdate();
            connexion.commit();
        }catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
	}
	/*
	 * @param idConsultation int
	 * (non-Javadoc)
	 * @see com.hospitalProject.dao.VisitesDao#listVisiteByCons(int)
	 */
	public List<Visites> listVisiteByCons(int idConsultation) throws DaoException {
		List<Visites> vst = new ArrayList<Visites>();
		Connection connexion = null;
	    Visites vs= null;
        PreparedStatement preparedStatement = null;
        
        
        try {
        	connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT `idvisites`,`numvisites`,`datenext`,`datevisite`,`ordrevisite`,`statut`,`symptome`,`ordonnance` FROM `visites` inner join consultations on visites.idConsultations=consultations.idConsultations WHERE consultations.idConsultations=?");
			 preparedStatement.setInt(1, idConsultation);
			 ResultSet rs = preparedStatement.executeQuery();
			 while(rs.next()) {
					vs = new Visites();
					vs.setIdVisites(rs.getInt("idvisites"));
					vs.setNumVisites(rs.getString("numvisites"));
					vs.setDateRv(rs.getString("datenext"));
					vs.setDateVisite(rs.getString("datevisite"));
					vs.setOrdre(rs.getInt("ordrevisite"));
					vs.setStatut(rs.getString("statut"));
					vs.setSymptome(rs.getString("symptome"));
					vs.setOrdonnance(rs.getString("ordonnance"));
					vst.add(vs);
				}
		
        
        } catch (SQLException e) {
			// TODO Auto-generated catch block
        	throw new DaoException("Impossible de communiquer avec la base de données");
		}
        
       return vst;
	}
	
	public Consultation listConsultation(int idConsultation) throws DaoException {
		
		Connection connexion = null;
		Consultation vs= null;
        PreparedStatement preparedStatement = null;
        
        
        try {
        	connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT visites.datenext, traitement, etatTraitement, diagnostique, consultations.idConsultations FROM `consultations` INNER JOIN visites ON visites.idConsultations=consultations.idConsultations  WHERE consultations.idConsultations=? AND visites.ordrevisite=0");
			 preparedStatement.setInt(1, idConsultation);
			 ResultSet rs = preparedStatement.executeQuery();
			 while(rs.next()) {
					vs = new Consultation();
					vs.setDateRv(rs.getString("datenext"));
					vs.setTraitement(rs.getString("traitement"));
					vs.setEtatTraitement(rs.getString("etatTraitement"));
					vs.setDiagnostique(rs.getString("diagnostique"));
					vs.setIdConsultation(rs.getInt("idConsultations"));;
				}
		
        
        } catch (SQLException e) {
			// TODO Auto-generated catch block
        	throw new DaoException("Impossible de communiquer avec la base de données");
		}
        
       return vs;
	}
	
	
	public int countOrder(int idConsultation) throws DaoException{
		int count = 0;
		Connection connexion = null;
	    
	    ResultSet resultat = null;
	    PreparedStatement preparedStatement = null;
	    try {
            connexion = daoFactory.getConnection();
            preparedStatement =  connexion.prepareStatement("SELECT ordrevisite FROM visites WHERE idConsultations=?");
            preparedStatement.setInt(1, idConsultation);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                count = resultat.getInt("odrevisite");
                }
            

        } catch (SQLException e) {
        	e.printStackTrace();
        } 
	    return count;
	}
	public int CountVisites() throws DaoException {
		int count = 0;
		Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT COUNT(*) AS total FROM visites");
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
}

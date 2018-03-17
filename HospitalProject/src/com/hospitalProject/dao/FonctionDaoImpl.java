package com.hospitalProject.dao;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hospitalProject.beans.Fonctions;


public class FonctionDaoImpl implements FonctionDao {

	private DaoFactory daoFactory;
	
	FonctionDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
	
	@Override
	public List<Fonctions> listFonction() throws DaoException {
		List<Fonctions> fonctionsUsers = new ArrayList<Fonctions>();
		 Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT idfonctions, libelleF FROM fonctions");

	            while (resultat.next()) {
	            	int identifiant = resultat.getInt("idfonctions");
	                String libelleFonction = resultat.getString("libelleF");
	                

	                Fonctions fonctionsUser = new Fonctions();
	                fonctionsUser.setIdfonction(identifiant);
	                fonctionsUser.setLibelleFonction(libelleFonction);;
	               

	                fonctionsUsers.add(fonctionsUser);
	            }
	        } catch (SQLException e) {
	            throw new DaoException("Impossible de communiquer avec la base de données reesayer");
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
	        return fonctionsUsers;
	}
	
	public List<Fonctions> listPathologie() throws DaoException {
		List<Fonctions> fonctionsUsers = new ArrayList<Fonctions>();
		 Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT `idpathologie`, `libellePat` FROM `pathologie`");

	            while (resultat.next()) {
	            	int identifiant = resultat.getInt("idpathologie");
	                String libelleFonction = resultat.getString("libellePat");
	                

	                Fonctions fonctionsUser = new Fonctions();
	                fonctionsUser.setIdPathologie(identifiant);
	                fonctionsUser.setLibellePathologie(libelleFonction);;
	               

	                fonctionsUsers.add(fonctionsUser);
	            }
	        } catch (SQLException e) {
	            throw new DaoException("Impossible de communiquer avec la base de données reesayer");
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
	        return fonctionsUsers;
	}
	
	public void ajouterPathologie(Fonctions fonctions) throws DaoException {
		Connection connexion = null;   
        PreparedStatement preparedStatement = null;
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO `pathologie`(`libellePat`) VALUES (?)");
            preparedStatement.setString(1, fonctions.getLibellePathologie());
            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            e.printStackTrace();
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

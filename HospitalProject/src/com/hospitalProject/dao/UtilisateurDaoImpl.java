package com.hospitalProject.dao;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import com.hospitalProject.beans.Users;

import com.hospitalProject.beans.Patients;
import com.hospitalProject.beans.StoryPatient;

public class UtilisateurDaoImpl implements UtilisateurDao{
	
	private DaoFactory daoFactory;
		
		UtilisateurDaoImpl(DaoFactory daoFactory)  {
	        this.daoFactory = daoFactory;
		}
		/*
		public int CountUtilisateur() throws DaoException {
			Connection connexion = null;
		    Statement statement = null;
		    ResultSet resultat = null;
			try {
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT COUNT(*) FROM utilisateur");
	            resultat.next();
	            int cnt = (int) resultat.getDouble(0);
	            return cnt;
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
	        
		}
		*/
		
		public Users LoginUtilisateur(String login,String mdp) throws DaoException{
			Connection connexion = null;
		    Users utilisateur= null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = connexion.prepareStatement("SELECT titleU, idUsers, login, nameU, fnameU, utilisateur.idFonctions, fonctions.libelleF FROM utilisateur INNER JOIN fonctions ON fonctions.idfonctions=utilisateur.idFonctions WHERE login=? AND mdp=?");
	            
	            preparedStatement.setString(1, login);
	            preparedStatement.setString(2, mdp);
	            
	         // executing the query
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					utilisateur = new Users();
					utilisateur.setTitleUser(rs.getString("titleU"));
					utilisateur.setIdentifantUsers(rs.getInt("idUsers"));
					utilisateur.setLogin(rs.getString("login"));
					utilisateur.setNomUser(rs.getString("nameU"));
	                utilisateur.setPrenomUser( rs.getString("fnameU"));
	                utilisateur.setIdFonctions(rs.getInt("idFonctions"));
	                utilisateur.setLibelleFonctions(rs.getString("libelleF"));
				}
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
	        return utilisateur;
		}
		public List<Users> ListUtilisateur() throws DaoException{
			  List<Users> utilisateurs = new ArrayList<Users>();
			  Connection connexion = null;
			    Statement statement = null;
			    ResultSet resultat = null;

		        try {
		            connexion = daoFactory.getConnection();
		            statement = connexion.createStatement();
		            resultat = statement.executeQuery("SELECT idUsers, titleU, nameU, fnameU, telU, emailU, login, mdp, adressU, utilisateur.idFonctions, libelleF FROM utilisateur INNER JOIN fonctions ON fonctions.idfonctions=utilisateur.idFonctions");

		            while (resultat.next()) {
		            	int identifiant = resultat.getInt("idUsers");
		            	String titre = resultat.getString("titleU");
		                String nom = resultat.getString("nameU");
		                String prenom = resultat.getString("fnameU");
		                String tel = resultat.getString("telU");
		                String email = resultat.getString("emailU");
		                String login = resultat.getString("login");
		                String mdp = resultat.getString("mdp");
		                String adressU = resultat.getString("adressU");
		                int idfonction = resultat.getInt("idFonctions");
		                String libelleF = resultat.getString("libelleF");

		              Users utilisateur = new Users();
		                utilisateur.setIdentifantUsers(identifiant);
		                utilisateur.setNomUser(nom);
		                utilisateur.setPrenomUser(prenom);
		                utilisateur.setTitleUser(titre);
		                utilisateur.setTelUser(tel);
		                utilisateur.setEmailUser(email);
		                utilisateur.setLogin(login);
		                utilisateur.setMdp(mdp);
		                utilisateur.setAdresse(adressU);
		                utilisateur.setIdFonctions(idfonction);
		                utilisateur.setLibelleFonctions(libelleF);

		                utilisateurs.add(utilisateur);
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
		        return utilisateurs;
		}
	
		public void ajouter(Users user) throws DaoException {
			Connection connexion = null;
		    
		        PreparedStatement preparedStatement = null;

		        try {
		            connexion = daoFactory.getConnection();
		            preparedStatement = connexion.prepareStatement("INSERT INTO utilisateur(titleU, nameU, fnameU, telU, emailU, login, mdp, adressU, idFonctions) VALUES(?, ?,?,?,?,?,?,?,?);");
		            preparedStatement.setString(1, user.getTitleUser());
		            preparedStatement.setString(2, user.getNomUser());
		            preparedStatement.setString(3, user.getPrenomUser());
		            preparedStatement.setString(4, user.getTelUser());
		            preparedStatement.setString(5, user.getEmailUser());
		            preparedStatement.setString(6, user.getLogin());
		            preparedStatement.setString(7, user.getMdp());
		            preparedStatement.setString(8, user.getAdresse());
		            preparedStatement.setInt(9, user.getIdFonctions());
		            
		            preparedStatement.executeUpdate();
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
		
		public List<Patients> ProchainRdv(int utilisateur) throws DaoException {
			List<Patients> sPatient = new ArrayList<Patients>();
			Connection connexion = null;
		    Patients sp= null;
	        PreparedStatement preparedStatement = null;
	        
	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = connexion.prepareStatement("SELECT patients.titleP, date_format(visites.datenext, '%d/%m/%Y') AS dateRdv, patients.nameP, patients.idpatients FROM `utilisateur` INNER JOIN consultations ON consultations.idUsers=utilisateur.idUsers INNER JOIN visites ON visites.idConsultations=consultations.idConsultations INNER JOIN patients ON patients.idpatients=consultations.idpatients WHERE utilisateur.idUsers=? AND visites.datenext>NOW()");
	            preparedStatement.setInt(1, utilisateur);    
	          
	         // executing the query
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					sp = new Patients();
					sp.setTitrePatient(rs.getString("titleP"));
					sp.setDatev(rs.getString("dateRdv"));
					sp.setNomPatient(rs.getString("nameP"));
					sp.setCodePatient(rs.getString("idpatients"));
					
					sPatient.add(sp);
				}
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
	        return sPatient;
		}
}

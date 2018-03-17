package com.hospitalProject.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hospitalProject.dao.DaoException;
import com.hospitalProject.beans.Consultation;
import com.hospitalProject.beans.Patients;
import com.hospitalProject.beans.StoryPatient;
import com.hospitalProject.beans.Users;



public class PatientDaoImpl implements PatientDao {

	private DaoFactory daoFactory;
	
	PatientDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	
	public List<StoryPatient> storyPatient(String idp) throws DaoException{
		List<StoryPatient> sPatient = new ArrayList<StoryPatient>();
		Connection connexion = null;
	    StoryPatient sp= null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT nomPatient, prenomPatient, numeroC, numeroV,date_format(dateBegin, '%d/%m/%Y') AS dateBegin, date_format(dateVst, '%d/%m/%Y') AS dateVst, orderVst, statusV FROM storypatient WHERE idp=? ORDER BY dateVst ASC");
            preparedStatement.setString(1, idp);    
          
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				sp = new StoryPatient();
				sp.setNamePatient(rs.getString("nomPatient"));
				sp.setPrenomPatient(rs.getString("prenomPatient"));
				sp.setNumConsultation(rs.getString("numeroC"));
				sp.setNumVisite(rs.getString("numeroV"));
				sp.setDateDebut(rs.getString("dateBegin"));
				sp.setDateVisite(rs.getString("dateVst"));
				sp.setOrdre(rs.getInt("orderVst"));
				sp.setStatus(rs.getString("statusV"));
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
	
	public List<Consultation> SearchPatient(String search) throws DaoException{
		List< Consultation> patients = new ArrayList<Consultation>();
		
		Connection connexion = null;
	    Consultation patient= null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT idpatients, nameP, fnameP FROM patients WHERE patients.idpatients LIKE ?");
            preparedStatement.setString(1, search );    
          
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				patient = new Consultation();
				
				patient.setIdPatientCons(rs.getString("idpatients"));
				patient.setNamePatient(rs.getString("nameP"));
				patient.setPrenomPatient(rs.getString("fnameP"));
				patients.add(patient);
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
        return patients;
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see com.hospitalProject.dao.PatientDao#CountPatient(java.lang.String, java.lang.String)
	 */
	
	public int CountPatient(String name, String year) throws DaoException {
		int count = 0;
		Connection connexion = null;
	    Statement statement = null;
	    PreparedStatement preparedStatement = null;
	
	    try {
            connexion = daoFactory.getConnection();
            
            preparedStatement = connexion.prepareStatement("SELECT COUNT(*) AS total FROM patients WHERE patients.nameP=? AND YEAR(patients.dateRecord)=? ");
            
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, year);
         // executing the query
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                count = rs.getInt("total");
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
	

	@SuppressWarnings("null")
	
	
	
	public int trouver(String identifiant) throws DaoException {
		int count = 0;
		Connection connexion = null;
	    
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT  COUNT(*) AS total FROM consultations INNER JOIN patients ON patients.idpatients = consultations.idpatients WHERE consultations.idpatients=?");
            
            preparedStatement.setString(1, identifiant);
        
            
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("total");
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
	
	public int countVisites(int identifiant) throws DaoException {
		int count = 0;
		Connection connexion = null;
	    
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT COUNT(*) AS total FROM patients INNER JOIN consultations ON patients.idpatients = consultations.idpatients INNER JOIN visites ON visites.idConsultations=consultations.idConsultations WHERE consultations.idConsultations=?");
            
            preparedStatement.setInt(1, identifiant);
        
            
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("total");
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
	
	
	public String trouverDateRdv(int identifiant) throws DaoException {
		String count = null;
		Connection connexion = null;
	    
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT date_format(visites.datenext, '%d/%m/%Y') AS datev FROM patients INNER JOIN consultations ON patients.idpatients = consultations.idpatients INNER JOIN visites ON visites.idConsultations=consultations.idConsultations WHERE consultations.idConsultations=? ORDER BY visites.idvisites DESC LIMIT 1");
            
            preparedStatement.setInt(1, identifiant);
        
            
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				count = rs.getString("datev");
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
	
	public String trouverDate1(String identifiant) throws DaoException {
		String count = null;
		Connection connexion = null;
	    
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT date_format(visites.datevisite, '%d/%m/%Y') AS datev FROM `visites` INNER JOIN consultations on visites.idConsultations=consultations.idConsultations INNER JOIN patients ON patients.idpatients=consultations.idpatients WHERE patients.idpatients=? ORDER BY idvisites DESC LIMIT 1");
            
            preparedStatement.setString(1, identifiant);
        
            
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				count = rs.getString("datev");
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
	
	public String trouverDate(int identifiant) throws DaoException {
		String count = null;
		Connection connexion = null;
	    
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT date_format(visites.datevisite, '%d/%m/%Y') AS datev FROM `visites` INNER JOIN consultations on visites.idConsultations=consultations.idConsultations WHERE visites.idConsultations=? ORDER BY idvisites DESC LIMIT 1");
            
            preparedStatement.setInt(1, identifiant);
        
            
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				count = rs.getString("datev");
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
	
	public List<Patients> ListPatient(String search) throws DaoException{
		List<Patients> patients = new ArrayList<Patients>();
		
		Connection connexion = null;
	    Consultation patient= null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT patients.idpatients,titleP, nameP, fnameP, profession, adresseP, telP, emailP,date_format(dateRecord, '%d/%m/%Y') AS dateRecord,date_format(dateNaissance, '%d/%m/%Y') AS dateNaissance FROM patients WHERE patients.idpatients LIKE ? OR patients.nameP LIKE ? OR patients.fnameP LIKE ? OR patients.dateNaissance LIKE ? OR YEAR(patients.dateRecord) LIKE ? ");
            preparedStatement.setString(1, search );    
            preparedStatement.setString(2, search ); 
            preparedStatement.setString(3, search ); 
            preparedStatement.setString(4, search ); 
            preparedStatement.setString(5, search ); 
	            
            ResultSet resultat = preparedStatement.executeQuery();
	            
	            while (resultat.next()) {
	            	
	            	String identifiant = resultat.getString("idpatients");
	            	String titre = resultat.getString("titleP");
	                String nom = resultat.getString("nameP");
	                String prenom = resultat.getString("fnameP");
	                String tel = resultat.getString("telP");
	                String email = resultat.getString("emailP");
	                String profession = resultat.getString("profession");
	                String mdp = resultat.getString("dateRecord");
	                String adressU = resultat.getString("adresseP");
	                String idfonction = resultat.getString("dateNaissance");
	                String datevisite = this.trouverDate1(identifiant);
	               int count = this.trouver(identifiant);
		           
		           
	                Patients patient1 = new Patients();
	                patient1.setCodePatient(identifiant);
	                patient1.setNomPatient(nom);
	                patient1.setPrenomPatient(prenom);
	                patient1.setTitrePatient(titre);
	                patient1.setTelPatient(tel);
	                patient1.setEmailPatient(email);
	                patient1.setProfessionPatient(profession);
	                patient1.setAdressePatient(adressU);
	                patient1.setDateNaissance(idfonction);
	                patient1.setDateEnregist(mdp);
	                patient1.setNombreCons(count);
	                patient1.setDatev(datevisite);
	                patients.add(patient1);
	                
	                
	             
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
	        return patients;
	}
	
	public void ajouterPatient(Patients patient) throws DaoException {
		Connection connexion = null;   
        PreparedStatement preparedStatement = null;
        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO patients(idpatients, titleP, nameP, fnameP, profession, adresseP, telP, emailP, dateNaissance, dateRecord) VALUES (?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, patient.getCodePatient());
            preparedStatement.setString(2, patient.getTitrePatient());
            preparedStatement.setString(3, patient.getNomPatient());
            preparedStatement.setString(4, patient.getPrenomPatient());
            preparedStatement.setString(5, patient.getProfessionPatient());
            preparedStatement.setString(6, patient.getAdressePatient());
            preparedStatement.setString(7, patient.getTelPatient());
            preparedStatement.setString(8, patient.getEmailPatient());
            preparedStatement.setString(9, patient.getDateNaissance());
            preparedStatement.setString(10, patient.getDateEnregist());
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
	
	/*
	 * 
	 */
	
	 public List<Consultation> SearchAllPatient(String search) throws DaoException{
		List<Consultation> patients = new ArrayList<Consultation>();
		Connection connexion = null;
	    Consultation patient= null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT idConsultations, patients.idpatients, nameP, fnameP, numConsultations, datedebutC, etatTraitement, diagnostique, traitement FROM `consultations` INNER JOIN patients on consultations.idpatients=patients.idpatients  WHERE patients.idpatients LIKE ?");
            preparedStatement.setString(1, search );    
          
         // executing the query
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				patient = new Consultation();
				patient.setIdConsultation(rs.getInt("idConsultations"));
				patient.setIdPatientCons(rs.getString("idpatients"));
				patient.setNamePatient(rs.getString("nameP"));
				patient.setPrenomPatient(rs.getString("fnameP"));
				patient.setNumConsultation(rs.getString("numConsultations"));
				patient.setDateNow(rs.getString("datedebutC"));
				patient.setEtatTraitement(rs.getString("etatTraitement"));
				patient.setDiagnostique(rs.getString("diagnostique"));
				patient.setTraitement(rs.getString("traitement"));
				String datelast = this.trouverDate(rs.getInt("idConsultations"));
				String dateRv = this.trouverDateRdv(rs.getInt("idConsultations"));
				int countvs = this.countVisites(rs.getInt("idConsultations"));
				patient.setNombreVisite(countvs);
				patient.setDateLast(datelast);
				patient.setDateRv(dateRv);
				patients.add(patient);
				
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
        return patients;
	}
	 
}

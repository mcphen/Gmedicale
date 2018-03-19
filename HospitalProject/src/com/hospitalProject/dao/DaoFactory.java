package com.hospitalProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/hospitalproject?useUnicode=yes&characterEncoding=UTF-8", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion; 
    }

    // Récupération du Dao
    public FonctionDao getFonctionDao() {
        return new FonctionDaoImpl(this);
    }
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDaoImpl(this);
    }
    
    public PatientDao getPatientDao() {
        return new PatientDaoImpl(this);
    }
    
    public ConsultationDao getConsultationDao() {
        return new ConsultationDaoImpl(this);
    }
    
    public VisitesDao getVisitesDao() {
        return new VisitesDaoImpl(this);
    }
}
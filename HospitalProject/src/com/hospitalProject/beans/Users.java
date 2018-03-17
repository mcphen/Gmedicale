package com.hospitalProject.beans;

public class Users {
	
	private int identifantUsers;
	
	private String titleUser;
	private String nomUser;
	private String prenomUser;
	private String telUser;
	private String emailUser;
	private String login;
	private String mdp;
	private int idFonctions;
	private String adresse;
	private String libelleFonctions;
	public String getLibelleFonctions() {
		return libelleFonctions;
	}
	public void setLibelleFonctions(String libelleFonctions) {
		this.libelleFonctions = libelleFonctions;
	}
	public int getIdentifantUsers() {
		return identifantUsers;
	}
	public void setIdentifantUsers(int identifantUsers) {
		this.identifantUsers = identifantUsers;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTitleUser() {
		return titleUser;
	}
	public void setTitleUser(String titleUser) {
		this.titleUser = titleUser;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public String getTelUser() {
		return telUser;
	}
	public void setTelUser(String telUser) {
		this.telUser = telUser;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getIdFonctions() {
		return idFonctions;
	}
	public void setIdFonctions(int f) {
		this.idFonctions = f;
	}
}

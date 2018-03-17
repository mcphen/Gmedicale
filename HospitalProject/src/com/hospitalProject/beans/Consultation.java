package com.hospitalProject.beans;

public class Consultation {
	private String numConsultation;
	private int idUserCons;
	private String idPatientCons;
	private String symptome;
	private String diagnostique;
	private String traitement;
	private String etatTraitement;
	private String dateRv;
	private String dateNow;
	private String dateLast;
	private String namePatient;
	private String prenomPatient;
	private int idConsultation;
	private int nombreVisite;
	
	
	
	
	public int getNombreVisite() {
		return nombreVisite;
	}
	public void setNombreVisite(int nombreVisite) {
		this.nombreVisite = nombreVisite;
	}
	public int getIdConsultation() {
		return idConsultation;
	}
	public void setIdConsultation(int idConsultation) {
		this.idConsultation = idConsultation;
	}
	public String getDateLast() {
		return dateLast;
	}
	public void setDateLast(String dateLast) {
		this.dateLast = dateLast;
	}
	public String getNamePatient() {
		return namePatient;
	}
	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}
	public String getPrenomPatient() {
		return prenomPatient;
	}
	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}
	public String getNumConsultation() {
		return numConsultation;
	}
	public void setNumConsultation(String numConsultation) {
		this.numConsultation = numConsultation;
	}
	public int getIdUserCons() {
		return idUserCons;
	}
	public void setIdUserCons(int idUserCons) {
		this.idUserCons = idUserCons;
	}
	public String getIdPatientCons() {
		return idPatientCons;
	}
	public void setIdPatientCons(String idPatientCons) {
		this.idPatientCons = idPatientCons;
	}
	public String getSymptome() {
		return symptome;
	}
	public void setSymptome(String symptome) {
		this.symptome = symptome;
	}
	public String getDiagnostique() {
		return diagnostique;
	}
	public void setDiagnostique(String diagnostique) {
		this.diagnostique = diagnostique;
	}
	public String getTraitement() {
		return traitement;
	}
	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}
	public String getEtatTraitement() {
		return etatTraitement;
	}
	public void setEtatTraitement(String etatTraitement) {
		this.etatTraitement = etatTraitement;
	}
	public String getDateRv() {
		return dateRv;
	}
	public void setDateRv(String dateRv) {
		this.dateRv = dateRv;
	}
	public String getDateNow() {
		return dateNow;
	}
	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
	}
	
	
}

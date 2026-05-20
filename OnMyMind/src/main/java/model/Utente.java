package model;

import java.util.ArrayList;

public class Utente {

	private int id_utente;
	private String nome;
	private String cognome;
	private String mail;
	private String password;
	private String telefono;
	private Ruolo ruolo;
	private ArrayList<Ordine> ordini;
	private ArrayList<Recensione> recensioni;
	private ArrayList<Indirizzo> indirizzi;
	
	public enum Ruolo{
		UTENTE, ADMIN
	}

	public int getId_utente() {
		return id_utente;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public ArrayList<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(ArrayList<Ordine> ordini) {
		this.ordini = ordini;
	}

	public ArrayList<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(ArrayList<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public ArrayList<Indirizzo> getIndirizzi() {
		return indirizzi;
	}

	public void setIndirizzi(ArrayList<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	};
	
	
	
}

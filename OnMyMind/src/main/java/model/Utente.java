package model;

import java.util.ArrayList;

public class Utente {

	private int id_utente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String telefono;
	private Ruolo ruolo;
	private ArrayList<Ordine> ordini= new ArrayList<Ordine>();
	private ArrayList<Recensione> recensioni= new ArrayList<Recensione>();
	private ArrayList<Indirizzo> indirizzi= new ArrayList<Indirizzo>();
	
	public enum Ruolo{
		UTENTE("utente"),
		ADMIN("admin");
		
		private String dbValue;

		Ruolo(String dbValue){
		    this.dbValue = dbValue;
		}

		public String getDbValue(){
		    return dbValue;
		}
		
		public static Ruolo fromDb(String value) {
		    for (Ruolo r : values()) {
		        if (r.dbValue.equalsIgnoreCase(value)) {
		            return r;
		        }
		    }
		    throw new IllegalArgumentException("Ruolo non valido: " + value);
		}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	}
	
	
	
}

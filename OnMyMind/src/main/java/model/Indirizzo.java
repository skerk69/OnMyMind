package model;

public class Indirizzo {

	private int id_indirizzo;
	private String paese;
	private String provincia;
	private String cap;
	private String citta;
	private String via;
	private Utente utente;
	
	public int getId_indirizzo() {
		return id_indirizzo;
	}
	public void setId_indirizzo(int id_indirizzo) {
		this.id_indirizzo = id_indirizzo;
	}
	public String getPaese() {
		return paese;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
}

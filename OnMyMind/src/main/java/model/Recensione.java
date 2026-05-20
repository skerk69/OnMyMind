package model;

import java.time.LocalDateTime;

public class Recensione {

	private int id_recensione;
	private int voto;
	private LocalDateTime data_recensione;
	private String commento;
	private Utente utente;
	private Cappello cappello;
	
	public int getId_recensione() {
		return id_recensione;
	}
	public void setId_recensione(int id_recensione) {
		this.id_recensione = id_recensione;
	}
	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		if (voto < 1 || voto > 5) {
		    throw new IllegalArgumentException("Voto non valido");
		}
		this.voto = voto;
	}
	public LocalDateTime getData_recensione() {
		return data_recensione;
	}
	public void setData_recensione(LocalDateTime data_recensione) {
		this.data_recensione = data_recensione;
	}
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Cappello getCappello() {
		return cappello;
	}
	public void setCappello(Cappello cappello) {
		this.cappello = cappello;
	}
	
	
}

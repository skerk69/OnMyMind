package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cappello {

	private int id_cappello;
	private String nome;
	private double prezzo;
	private char taglia;
	private String materiale;
	private String colore;
	private int quantita;
	private LocalDate data_aggiunta;
	private String immagine;
	private String descrizione;
	private ArrayList<Categoria> categorie;
	private ArrayList<Recensione> recensioni;
	private ArrayList<DettaglioOrdine> dettagliordini;
	
	public int getId_cappello() {
		return id_cappello;
	}
	public void setId_cappello(int id_cappello) {
		this.id_cappello = id_cappello;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public char getTaglia() {
		return taglia;
	}
	public void setTaglia(char taglia) {
		this.taglia = taglia;
	}
	public String getMateriale() {
		return materiale;
	}
	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public LocalDate getData_aggiunta() {
		return data_aggiunta;
	}
	public void setData_aggiunta(LocalDate data_aggiunta) {
		this.data_aggiunta = data_aggiunta;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public ArrayList<Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(ArrayList<Categoria> categorie) {
		this.categorie = categorie;
	}
	public ArrayList<Recensione> getRecensioni() {
		return recensioni;
	}
	public void setRecensioni(ArrayList<Recensione> recensioni) {
		this.recensioni = recensioni;
	}
	public ArrayList<DettaglioOrdine> getDettagliordini() {
		return dettagliordini;
	}
	public void setDettagliordini(ArrayList<DettaglioOrdine> dettagliordini) {
		this.dettagliordini = dettagliordini;
	}
	
	
	
}

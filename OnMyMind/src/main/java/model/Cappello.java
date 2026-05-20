package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cappello {

	private int id_cappello;
	private String nome;
	private double prezzo;
	private String taglia;
	private String materiale;
	private String colore;
	private int quantita_magazzino;
	private LocalDateTime data_aggiunta;
	private String immagine;
	private String descrizione;
	private Categoria categoria;
	private ArrayList<Recensione> recensioni= new ArrayList<Recensione>();
	private ArrayList<DettaglioOrdine> dettagliordini= new ArrayList<DettaglioOrdine>();
	
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
	public String getTaglia() {
		return taglia;
	}
	public void setTaglia(String taglia) {
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
	public int getQuantitaMagazzino() {
		return quantita_magazzino;
	}
	public void setQuantitaMagazzino(int quantita_magazzino) {
		this.quantita_magazzino = quantita_magazzino;
	}
	public LocalDateTime getData_aggiunta() {
		return data_aggiunta;
	}
	public void setData_aggiunta(LocalDateTime data_aggiunta) {
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

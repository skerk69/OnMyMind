package model;

import java.util.ArrayList;

public class Categoria {

	private int id_categoria;
	private String nome;
	private String descrizione;
	private ArrayList<Cappello> cappelli;
	
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public ArrayList<Cappello> getCappelli() {
		return cappelli;
	}
	public void setCappelli(ArrayList<Cappello> cappelli) {
		this.cappelli = cappelli;
	}
	
	
}

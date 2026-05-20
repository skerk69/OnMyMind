package model;

import java.util.ArrayList;

public class Categoria {

	private int id_categoria;
	private String nome_categoria;
	private String descrizione;
	private ArrayList<Cappello> cappelli= new ArrayList<Cappello>();
	
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNomeCategoria() {
		return nome_categoria;
	}
	public void setNomeCategoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
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

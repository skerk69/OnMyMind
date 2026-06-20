package model;

public class DettaglioOrdine {

	private double prezzo_unitario;
	private int quantita;
	private Cappello cappello;
	private int id_cappello;
	private Ordine ordine;
	private int id_ordine;
	public double getPrezzo_unitario() {
		return prezzo_unitario;
	}
	public void setPrezzo_unitario(double prezzo_unitario) {
		this.prezzo_unitario = prezzo_unitario;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public Cappello getCappello() {
		return cappello;
	}
	public void setCappello(Cappello cappello) {
		this.cappello = cappello;
		if (cappello != null) {
            this.id_cappello = cappello.getId_cappello();
        }
	}
	public int getId_cappello() {
		return id_cappello;
	}
	public void setId_cappello(int id_cappello) {
		this.id_cappello = id_cappello;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
		if (ordine != null) {
            this.id_ordine = ordine.getId_ordine();
        }
	}
	public int getId_ordine() {
		return id_ordine;
	}
	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}
	
	
}

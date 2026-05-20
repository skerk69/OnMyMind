package model;

public class DettaglioOrdine {

	private double prezzo_unitario;
	private int quantita_acquistata;
	private Cappello cappello;
	private Ordine ordine;
	public double getPrezzo_unitario() {
		return prezzo_unitario;
	}
	public void setPrezzo_unitario(double prezzo_unitario) {
		this.prezzo_unitario = prezzo_unitario;
	}
	public int getQuantita_acquistata() {
		return quantita_acquistata;
	}
	public void setQuantita_acquistata(int quantita_acquistata) {
		this.quantita_acquistata = quantita_acquistata;
	}
	public Cappello getCappello() {
		return cappello;
	}
	public void setCappello(Cappello cappello) {
		this.cappello = cappello;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	
	
}

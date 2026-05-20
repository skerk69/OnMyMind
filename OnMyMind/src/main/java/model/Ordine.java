package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ordine {

	private int id_ordine;
	private double totale;
	private LocalDateTime data_ordine;
	private StatoOrdine stato_ordine;
	private Pagamento pagamento;
	private ArrayList<DettaglioOrdine> dettagliordini= new ArrayList<DettaglioOrdine>();
	private Utente utente;
	
	public enum StatoOrdine{
		IN_ATTESA("in_attesa"),
		PAGATO("pagato"),
		SPEDITO("spedito"),
		CONSEGNATO("consegnato"),
		ANNULLATO("annullato");
		
		private String dbValue;
		
		StatoOrdine(String dbValue){
			this.dbValue=dbValue;
		}
		
		public String getDbValue() {
			return dbValue;
		}
		
		public static StatoOrdine fromDb(String value) {
		    for (StatoOrdine s : values()) {
		        if (s.dbValue.equalsIgnoreCase(value)) {
		            return s;
		        }
		    }
		    throw new IllegalArgumentException("Stato non valido: " + value);
		}
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public LocalDateTime getData_ordine() {
		return data_ordine;
	}

	public void setData_ordine(LocalDateTime data_ordine) {
		this.data_ordine = data_ordine;
	}

	public StatoOrdine getStato_ordine() {
		return stato_ordine;
	}

	public void setStato_ordine(StatoOrdine stato_ordine) {
		this.stato_ordine = stato_ordine;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public ArrayList<DettaglioOrdine> getDettagliordini() {
		return dettagliordini;
	}

	public void setDettagliordini(ArrayList<DettaglioOrdine> dettagliordini) {
		this.dettagliordini = dettagliordini;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
}

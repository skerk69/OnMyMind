package model;

public class Pagamento {

	private int id_pagamento;
	private double importo;
	private StatoPagamento stato_pagamento;
	private Ordine ordine;
	
	public enum StatoPagamento{
		IN_ATTESA,
		COMPLETATO,
		FALLITO,
		RIMBORSATO
	}

	public int getId_pagamento() {
		return id_pagamento;
	}

	public void setId_pagamento(int id_pagamento) {
		this.id_pagamento = id_pagamento;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public StatoPagamento getStato_pagamento() {
		return stato_pagamento;
	}

	public void setStato_pagamento(StatoPagamento stato_pagamento) {
		this.stato_pagamento = stato_pagamento;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	};
	
	
}

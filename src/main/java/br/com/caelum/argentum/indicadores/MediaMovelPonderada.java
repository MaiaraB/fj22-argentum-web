package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private Indicador outroIndicador;

	public MediaMovelPonderada(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
		
	}

	public double calcula(int posicao, int intervalo, SerieTemporal serie) {
		double soma = 0.0;
		int peso = intervalo;
		int divisor = peso;
		
		for (int i = posicao; i > posicao - intervalo; i--) {
			soma += (outroIndicador.calcula(i, intervalo, serie) * peso);
			peso--;
			divisor += peso;
		}
		return soma / divisor;
	}
	
	public String toString() {
		return "MMP de " + outroIndicador.toString();
	}
}

package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorFechamento implements Indicador {

	@Override
	public double calcula(int posicao, int intervalo, SerieTemporal serie) {
		return serie.getCandle(posicao).getFechamento();
	}
	
	public String toString() {
		return "Fechamento";
	}

}

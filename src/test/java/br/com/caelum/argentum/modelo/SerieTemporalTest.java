package br.com.caelum.argentum.modelo;

import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.indicadores.GeradorDeSerie;

public class SerieTemporalTest {

	@Test(expected=IllegalArgumentException.class)
	public void naoPodeReceberListNula() {
		List<Candle> candles = null;
		SerieTemporal serie = new SerieTemporal(candles);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void indiceDeCandleNaoExiste() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4);
		serie.getCandle(4);
	}

}

package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {

	private final List<Candle> candles;
	
	public SerieTemporal(List<Candle> candles) {
		if (candles == null) {
			throw new IllegalArgumentException("a lista de candles é nula");
		}
		this.candles = candles;
	}
	
	public Candle getCandle(int i) {
		if (i > candles.size() - 1) {
			throw new IndexOutOfBoundsException("Index ultrapassa o tamanho da Série Temporal");
		}
		return this.candles.get(i);
	}
	
	public int getUltimaPosicao() {
		return this.candles.size() - 1;
	}
}

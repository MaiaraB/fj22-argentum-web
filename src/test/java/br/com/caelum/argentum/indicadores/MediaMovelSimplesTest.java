package br.com.caelum.argentum.indicadores;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimplesTest {

	@Test
	public void sequenciaSimplesDeCandlesFechamento() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		Indicador mms = new MediaMovelSimples(new IndicadorFechamento());
		
		Assert.assertEquals(2.0, mms.calcula(2, 3, serie), 0.00001);
		Assert.assertEquals(3.0, mms.calcula(3, 3, serie), 0.00001);
		Assert.assertEquals(10.0/3, mms.calcula(4, 3, serie), 0.00001);
		Assert.assertEquals(11.0/3, mms.calcula(5, 3, serie), 0.00001);
		Assert.assertEquals(4.0, mms.calcula(6, 3, serie), 0.00001);
		Assert.assertEquals(13.0/3, mms.calcula(7, 3, serie), 0.00001);
		Assert.assertEquals(4.0, mms.calcula(8, 3, serie), 0.00001);
		
	}
	
	@Test
	public void sequenciaSimplesDeCandlesAbertura() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		Indicador mms = new MediaMovelSimples(new IndicadorAbertura());
		
		Assert.assertEquals(2.0, mms.calcula(2, 3, serie), 0.00001);
		Assert.assertEquals(3.0, mms.calcula(3, 3, serie), 0.00001);
		Assert.assertEquals(10.0/3, mms.calcula(4, 3, serie), 0.00001);
		Assert.assertEquals(11.0/3, mms.calcula(5, 3, serie), 0.00001);
		Assert.assertEquals(4.0, mms.calcula(6, 3, serie), 0.00001);
		Assert.assertEquals(13.0/3, mms.calcula(7, 3, serie), 0.00001);
		Assert.assertEquals(4.0, mms.calcula(8, 3, serie), 0.00001);
		
	}

}

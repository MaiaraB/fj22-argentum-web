package br.com.caelum.argentum.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	
	public ArgentumBean() {
		negociacoes = new ClienteWebService().getNegociacoes();
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 3, serie.getUltimaPosicao(), 4);
		geradorGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));
		geradorGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorAbertura()));
		geradorGrafico.plotaIndicador(new MediaMovelPonderada(new IndicadorFechamento()));
		geradorGrafico.plotaIndicador(new MediaMovelPonderada(new IndicadorAbertura()));
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
		
	}
	
	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}
	
	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}
}

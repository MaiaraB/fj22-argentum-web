package br.com.caelum.argentum.managedBean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.Indicador;
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
	private String nomeMedia;
	private String nomeIndicadorBase;

	public ArgentumBean() {
		negociacoes = new ClienteWebService().getNegociacoes();
		//geraGrafico();
		
	}

	public void geraGrafico() {
		System.out.println("PLOTANDO: " + nomeMedia + " de " + nomeIndicadorBase);
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 3, serie.getUltimaPosicao(), 4);
		geradorGrafico.plotaIndicador(defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}
	
	private Indicador defineIndicador() {
		if (nomeIndicadorBase == null || nomeIndicadorBase.isEmpty() || nomeMedia == null || nomeMedia.isEmpty()) {
			return new MediaMovelSimples(new IndicadorFechamento());
		}
		try {
			String pacote = "br.com.caelum.argentum.indicadores.";
			Class<?> classeIndicadorBase = Class.forName(pacote + nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();
			
			Class<?> classeMedia = Class.forName(pacote + nomeMedia);
			Constructor<?> construtorMedia = classeMedia.getConstructor(Indicador.class);
			Indicador indicador = (Indicador) construtorMedia.newInstance(indicadorBase);
			return indicador;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void teste() {
		System.out.println("TESTE");
	}
	
	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}
	
	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}
	
	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}
	
	public String getNomeMedia() {
		return nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}
}

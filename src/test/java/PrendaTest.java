
import Prendas.*;
import Prendas.Atuendo.Atuendo;
import Prendas.Material.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Api.AccuWeatherHandler;
import Exceptions.TipoDeParteIncorrectaException;

public class PrendaTest {

	  @Test
	  public void hayColorSecundario() {
		  Prenda remera = new Prenda(TipoDePrenda.PARTE_SUPERIOR,new Material(TipoDeMaterial.ALGODON),Color.AMARILLO,Color.AZUL,20,new ArrayList<>());
		  assert(remera.hayColorSecundario());
	  }
	  @Test
	  public void noHayColorSecundario() {
		  Prenda remera = new Prenda(TipoDePrenda.PARTE_SUPERIOR,new Material(TipoDeMaterial.ALGODON),Color.AMARILLO,20,new ArrayList<>());
		  assertFalse(remera.hayColorSecundario());
	  }
	  @Test
	  public void muyPocasPartesInferiores() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,20,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,20,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,20,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,20,new ArrayList<>())
				  );
		
		  assertThrows(TipoDeParteIncorrectaException.class, () -> new Atuendo(prendas));
	  }
	  

	  @Test
	  public void telaLisaPorDefecto() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  assertTrue(material.getTrama().equals(Trama.LISO));
	  }
	  
	  
	  @Test
	  public void construirPrendaConBuilder() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  Prenda prenda = new PrendaBuilder()
				  .setColorPrincipal(Color.AMARILLO)
				  .setTipoDePrenda(TipoDePrenda.CALZADO)
				  .setMaterial(material)
				  .setTemperaturaIdeal(20)
				  .setClimasApropiados(new ArrayList<>())
				  .buildPrenda();
		  assertFalse(prenda == null);
		  assertTrue(prenda.getTipoDePrenda()==TipoDePrenda.CALZADO);
	  }
	  
	  @Test
	  public void construirPrendaInvalidaConBuilder() {
		  Material material = new Material(TipoDeMaterial.ALGODON);

		  PrendaBuilder prendaBuilder = new PrendaBuilder().setColorPrincipal(Color.AMARILLO).setMaterial(material);

		  assertThrows(NullPointerException.class,() -> prendaBuilder.buildPrenda());
	  }

	  @Test
	  public void conseguirPrendasATemperaturaCorrecta() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,15,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,21,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,18,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,16,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,19,new ArrayList<>())
				  );
		  
		  assertTrue(new GuardaRopa().getPrendasATemperatura(20, 2,prendas).size()==3);
	  }
	  @Test
	  public void conseguirAtuendos() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,15,new ArrayList<>()),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,20,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,16,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,19,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,20,new ArrayList<>())
				  );

		  assertTrue(new GuardaRopa().sugerencias(prendas).size()==2);
	  }
	  @Test
	  public void conseguirMasAtuendos() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,15,new ArrayList<>()),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,20,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,16,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,19,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,19,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,20,new ArrayList<>())
				  );

		  assertTrue(new GuardaRopa().sugerencias(prendas).size()==4);
	  }
	  @Test
	  public void conseguirAtuendosATemperaturaCorrecta() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,19,new ArrayList<>()),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,20,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,21,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,16,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,19,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,20,new ArrayList<>())
				  );

		  assertTrue(new GuardaRopa().sugerencias(prendas,20,2).size()==2);
	  }
	  @Test
	  public void conseguirAtuendosATemperaturaCorrectaPorApi() {
		  Material material = new Material(TipoDeMaterial.ALGODON);
		  List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,11,new ArrayList<>()),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,13,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,8,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,7,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,15,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,12,new ArrayList<>())
				  );

		  assertTrue(new GuardaRopa().sugerencias(prendas,AccuWeatherHandler.getAccuWeatherHandler(),15).size()==4);
	  }
	  
	  
	  

}

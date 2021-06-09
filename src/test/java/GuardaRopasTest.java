import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Api.WeatherApiStrategy;
import Prendas.Color;
import Prendas.GuardaRopa;
import Prendas.Prenda;
import Prendas.TipoDePrenda;
import Prendas.Material.Material;
import Prendas.Material.TipoDeMaterial;
import Usuarios.RepositorioUsuario;
import Usuarios.Usuario;

public class GuardaRopasTest {

	Usuario usuario1 = new Usuario();
	Usuario usuario2 = new Usuario();
	Usuario usuario3 = new Usuario();
	Usuario usuario4 = new Usuario();
	
	
	GuardaRopa guardaRopa1 = new GuardaRopa();
	GuardaRopa guardaRopa2 = new GuardaRopa();

	List<Prenda> prendas;
	List<Prenda> prendas2;

	Material material = new Material(TipoDeMaterial.ALGODON);
	
	WeatherApiStrategy weatheApiMock;

	@BeforeEach
	public void agruegarUsuariosYGuadaropas()
	{

		List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,19 ,new ArrayList<>()),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,20 ,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,21 ,new ArrayList<>()),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,16 ,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,19 ,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,20 ,new ArrayList<>())
				  );
		guardaRopa1.addPrendas(prendas);
		List<Prenda> prendas2 = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,19 ,Arrays.asList("Aliens")),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,20 ,Arrays.asList("Aliens")),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,21 ,Arrays.asList("Aliens")),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,16 ,new ArrayList<>()),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,19 ,Arrays.asList("Aliens")),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,20 ,new ArrayList<>()),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,15 ,Arrays.asList("Aliens"))
				  );
		guardaRopa2.addPrendas(prendas2);
		weatheApiMock = mock(WeatherApiStrategy.class);
	}
	  @Test
	  public void conseguirSugerenciaFiltrada()
	  {
		  when (weatheApiMock.getCondicionesClimaticas("Buenos Aires")).thenReturn(Arrays.asList("Aliens"));
		  when (weatheApiMock.getTemp("Buenos Aires")).thenReturn((float) 20);
		  
		  assertEquals(1,guardaRopa2.sugerencias(weatheApiMock,2).size());
		  
	  }
	  
	  @Test
	  public void conseguirSugerenciaFiltradaSinAlertas()
	  {

		  when (weatheApiMock.getCondicionesClimaticas("Buenos Aires")).thenReturn(Arrays.asList());
		  when (weatheApiMock.getTemp("Buenos Aires")).thenReturn((float) 20);
		  
		  assertEquals(2,guardaRopa2.sugerencias(weatheApiMock,2).size());
	  }
	  
	  @Test
	  public void sugerenciasDiarias()
	  {

		  when (weatheApiMock.getCondicionesClimaticas("Buenos Aires")).thenReturn(Arrays.asList("Aliens"));
		  when (weatheApiMock.getTemp("Buenos Aires")).thenReturn((float) 20);
		  usuario1.agregarGuadaRopa(guardaRopa1);
		  usuario1.agregarGuadaRopa(guardaRopa2);
		  usuario2.agregarGuadaRopa(guardaRopa2);
		  usuario3.agregarGuadaRopa(guardaRopa1);
		  RepositorioUsuario.getRepositorioUsuario().ActualizarSugerenciasDeUsuarios(weatheApiMock,2);

		  assertEquals(1,usuario1.getSugerenciaDelDia().size());
		  assertEquals(1,usuario2.getSugerenciaDelDia().size());
		  assertEquals(0,usuario3.getSugerenciaDelDia().size());
	  }
}

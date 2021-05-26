import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Prendas.*;
import Prendas.Material.Material;
import Prendas.Material.TipoDeMaterial;
import Usuarios.Usuario;

public class PropuestasTest
{
	Usuario usuario1 = new Usuario();
	Usuario usuario2 = new Usuario();
	Usuario usuario3 = new Usuario();
	Usuario usuario4 = new Usuario();
	
	
	GuardaRopa guardaRopa1 = new GuardaRopa();
	GuardaRopa guardaRopa2 = new GuardaRopa();
	GuardaRopa guardaRopa3 = new GuardaRopa();
	
	List<Prenda> prendas;

	Material material = new Material(TipoDeMaterial.ALGODON);

	Prenda prenda1 =  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.BLANCO,12);
	Prenda prenda2 =  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AZUL,13);
	Prenda prenda3 =  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.VERDE,14);
	
	@BeforeEach
	public void agruegarUsuariosYGuadaropas()
	{

		usuario1.agregarGuadaRopa(guardaRopa1);
		usuario2.agregarGuadaRopa(guardaRopa2);
		usuario3.agregarGuadaRopa(guardaRopa3);
		usuario4.agregarGuadaRopa(guardaRopa3);

		List<Prenda> prendas = Arrays.asList(
				  new Prenda(TipoDePrenda.PARTE_SUPERIOR,material,Color.AMARILLO,19),
				  new Prenda(TipoDePrenda.PARTE_INFERIOR,material,Color.AMARILLO,20),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,21),
				  new Prenda(TipoDePrenda.CALZADO,material,Color.AMARILLO,16),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,19),
				  new Prenda(TipoDePrenda.ACCESORIO,material,Color.AMARILLO,20)
				  );

	}

	  @Test
	  public void hacerPropuesta()
	  {
		 usuario1.proponerPrenda(usuario2,guardaRopa2, prenda1,prenda2);
		 assertEquals(1,usuario2.getDeclaracionDePropuestas().size());
	  }
	  @Test
	  public void AceptarPropuesta()
	  {
			 usuario1.proponerPrenda(usuario2,guardaRopa2, prenda1,prenda2);
			 usuario1.proponerPrenda(usuario2,guardaRopa2, prenda1,prenda2);
			 usuario2.aceptarPropuesta(usuario2.getDeclaracionDePropuestas().get(0));
			 assertEquals(1,usuario2.getPropuestasAceptadas().size());
			 assertEquals(1,usuario2.getDeclaracionDePropuestas().size());
			 assertTrue(guardaRopa2.getPrendasClone().contains(prenda1));
			 assertTrue(guardaRopa2.getPrendasClone().contains(prenda2));
	  }
	  @Test
	  public void revertirPropuesta()
	  {
			 usuario1.proponerPrenda(usuario2,guardaRopa2, prenda1,prenda2);
			 usuario2.aceptarPropuesta(usuario2.getDeclaracionDePropuestas().get(0));
			 usuario2.revertirPropuesta(usuario2.getPropuestasAceptadas().get(0));
			 assertEquals(0,usuario2.getPropuestasAceptadas().size());
			 assertEquals(1,usuario2.getDeclaracionDePropuestas().size());
			 assertFalse(guardaRopa2.getPrendasClone().contains(prenda1));
			 assertFalse(guardaRopa2.getPrendasClone().contains(prenda2));
	  }
	  @Test
	  public void revertirUnaPropuesta()
	  {
			 usuario1.proponerPrenda(usuario2,guardaRopa2, prenda1,prenda2);
			 usuario2.aceptarPropuesta(usuario2.getDeclaracionDePropuestas().get(0));
			 
			 
			 assertTrue(guardaRopa2.getPrendasClone().contains(prenda1));
			 assertTrue(guardaRopa2.getPrendasClone().contains(prenda2));
			 
			 
			 usuario1.proponerPrenda(usuario2,guardaRopa2, prenda1,prenda2);
			 usuario2.aceptarPropuesta(usuario2.getDeclaracionDePropuestas().get(0));
			 
			 
			 assertEquals(4,guardaRopa2.getPrendasClone().size());
			 
			 usuario2.revertirPropuesta(usuario2.getPropuestasAceptadas().get(0));
			 
			 assertEquals(2,guardaRopa2.getPrendasClone().size());
	  }

	  @Test
	  public void PropuestaDeRemover()
	  {
			 usuario1.proponerPrenda(usuario2,guardaRopa2, prenda1,prenda2,prenda3);
			 usuario2.aceptarPropuesta(usuario2.getDeclaracionDePropuestas().get(0));
			 
			 usuario1.proponerQuitarPrenda(usuario2,guardaRopa2, prenda2);
			 usuario2.aceptarPropuesta(usuario2.getDeclaracionDePropuestas().get(0));

			 assertFalse(guardaRopa2.getPrendasClone().contains(prenda2));
		  
	  }
	  
}

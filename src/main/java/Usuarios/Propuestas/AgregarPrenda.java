package Usuarios.Propuestas;

import java.util.List;

import Prendas.GuardaRopa;
import Prendas.Prenda;

public class AgregarPrenda implements Propuesta {

	private List<Prenda> prendas;
	
	
	public AgregarPrenda(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	@Override
	public void aceptarPropuesta(GuardaRopa guardaRopa) {
		guardaRopa.addPrendas(prendas);
		
	}

	@Override
	public void revertirPropuesta(GuardaRopa guardaRopa) {
		guardaRopa.removePrendas(prendas);
	}

}

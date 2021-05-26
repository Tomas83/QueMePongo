package Usuarios.Propuestas;

import java.util.List;

import Prendas.GuardaRopa;
import Prendas.Prenda;

public class RemoverPrenda implements Propuesta {
	private List<Prenda> prendas;
	
	
	public RemoverPrenda(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	@Override
	public void aceptarPropuesta(GuardaRopa guardaRopa) {
		guardaRopa.removePrendas(prendas);
		
	}

	@Override
	public void revertirPropuesta(GuardaRopa guardaRopa) {
		guardaRopa.addPrendas(prendas);
	}
}

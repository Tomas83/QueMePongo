package Usuarios.Propuestas;

import java.util.Objects;

import Prendas.GuardaRopa;

public class DeclaracionDePropuesta {
	private GuardaRopa guardaRopa;
	private Propuesta propuesta;
	
	
	public GuardaRopa getGuardaRopa() {
		return guardaRopa;
	}

	public Propuesta getPropuesta() {
		return propuesta;
	}

	public DeclaracionDePropuesta(GuardaRopa guardaRopa, Propuesta propuesta)
	{

		this.guardaRopa = Objects.requireNonNull(guardaRopa,"Falta guardaropas");
		this.propuesta = Objects.requireNonNull(propuesta,"Falta propuesta");
	}

	public void aceptarPropuesta()
	{
		propuesta.aceptarPropuesta(guardaRopa);
	}
	public void rechazarPropuesta()
	{
		
	}
	public void revertirPropuesta()
	{
		propuesta.revertirPropuesta(guardaRopa);
	}
	
}

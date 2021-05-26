package Usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Prendas.*;
import Usuarios.Propuestas.AgregarPrenda;
import Usuarios.Propuestas.DeclaracionDePropuesta;
import Usuarios.Propuestas.Propuesta;
import Usuarios.Propuestas.RemoverPrenda;

public class Usuario {

	private List<GuardaRopa> guardaRopas = new ArrayList<>();
	
	private List<DeclaracionDePropuesta> declaracionDePropuestas = new ArrayList<>();
	
	private List<DeclaracionDePropuesta> propuestasAceptadas = new ArrayList<>();

	public void agregarGuadaRopa(GuardaRopa guardaRopa)
	{
		this.guardaRopas.add(guardaRopa);
	}
	
	
	
	public List<DeclaracionDePropuesta> getDeclaracionDePropuestas() {
		return declaracionDePropuestas;
	}



	public List<DeclaracionDePropuesta> getPropuestasAceptadas() {
		return propuestasAceptadas;
	}

//Hacer Propuestas________________________________________________________________________________________________

	public void proponerPrenda(Usuario usuario, GuardaRopa guardaRopa, Prenda ... prendas)
	{
		usuario.recibirPropuesta(new DeclaracionDePropuesta(guardaRopa, new AgregarPrenda(Arrays.asList(prendas))));
	}	public void proponerQuitarPrenda(Usuario usuario, GuardaRopa guardaRopa, Prenda ... prendas)
	{
		usuario.recibirPropuesta(new DeclaracionDePropuesta(guardaRopa, new RemoverPrenda(Arrays.asList(prendas))));
	}
//Recibir Propuestas_____________________________________________________________________________________________

	private boolean checkPropuesta (DeclaracionDePropuesta declaracionDePropuesta)
	{
		return declaracionDePropuestas.contains(declaracionDePropuesta) && guardaRopas.contains(declaracionDePropuesta.getGuardaRopa());
	}
	
	public void aceptarPropuesta(DeclaracionDePropuesta declaracionDePropuesta)
	{
		if (checkPropuesta(declaracionDePropuesta))
		{
			declaracionDePropuesta.aceptarPropuesta();
			propuestasAceptadas.add(declaracionDePropuesta);
			declaracionDePropuestas.remove(declaracionDePropuesta);
		}
	}
	public void rechazarPropuesta(DeclaracionDePropuesta declaracionDePropuesta)
	{
		if (checkPropuesta(declaracionDePropuesta))
		{
			declaracionDePropuesta.rechazarPropuesta();
			declaracionDePropuestas.remove(declaracionDePropuesta);
		}
	}
	public void revertirPropuesta(DeclaracionDePropuesta declaracionDePropuesta)
	{
		if (propuestasAceptadas.contains(declaracionDePropuesta) && guardaRopas.contains(declaracionDePropuesta.getGuardaRopa()))
		{
			declaracionDePropuesta.revertirPropuesta();
			propuestasAceptadas.remove(declaracionDePropuesta);
			declaracionDePropuestas.add(declaracionDePropuesta);
		}
	}
	public void recibirPropuesta(DeclaracionDePropuesta declaracionDePropuesta)
	{
		if (guardaRopas.contains(declaracionDePropuesta.getGuardaRopa()))
		this.declaracionDePropuestas.add(declaracionDePropuesta);
	}
}

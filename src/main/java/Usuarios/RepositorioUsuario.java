package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Api.AccuWeatherAPI;
import Api.WeatherApiStrategy;

public class RepositorioUsuario {
	private static RepositorioUsuario repositorioUsuario;
	
	private List<Usuario> usuarios;
	
	private RepositorioUsuario ()
	{

		this.usuarios = new ArrayList<>();
	}

	public static RepositorioUsuario getRepositorioUsuario() 
	{
		if (repositorioUsuario == null)
			repositorioUsuario = new RepositorioUsuario();
		return repositorioUsuario;
	}
	
	public void addUsuario(Usuario usuario)
	{
		usuarios.add(usuario);
	}
	
	public void ActualizarSugerenciasDeUsuarios(WeatherApiStrategy weatherApiStrategy, int rango)
	{
		usuarios.forEach(u->u.actualizarSugerenciaDelDia(u.generarTodasSugerenciasPosibles(weatherApiStrategy,rango)));
	}
	/*
	public List<Usuario> getUsuarios()
	{
		return this.usuarios;
	}
	*/
}

package Prendas;

import java.util.List;
import java.util.Objects;

import Prendas.Material.Material;

public class Prenda {
	private TipoDePrenda tipoDePrenda;
	private Material material;
	private Color colorPrincipal;
	private Color colorSecundario;
	private int temperaturaIdeal;
	private List<String> climasApropiados;

	public Prenda (TipoDePrenda tipoDePrenda, Material material, Color colorPrincipal, int temperaturaIdeal, List<String> climasApropiados)
	{
		this.tipoDePrenda = Objects.requireNonNull(tipoDePrenda,"Falta tipo de prenda");
		this.material = Objects.requireNonNull(material,"Falta material");
		this.colorPrincipal = Objects.requireNonNull(colorPrincipal,"Falta color Principal");
		this.colorSecundario = null;
		this.temperaturaIdeal = temperaturaIdeal;
		this.climasApropiados = climasApropiados;
		
	}
	public Prenda (TipoDePrenda tipoDePrenda, Material material, Color colorPrincipal, Color colorSecundario, int temperaturaIdeal,List<String> climasApropiados)
	{
		this.tipoDePrenda = Objects.requireNonNull(tipoDePrenda,"Falta tipo de prenda");
		this.material = Objects.requireNonNull(material,"Falta material");
		this.colorPrincipal = Objects.requireNonNull(colorPrincipal,"Falta color Principal");
		this.colorSecundario = colorSecundario;
		this.temperaturaIdeal = temperaturaIdeal;
		this.climasApropiados = climasApropiados;
	}
	
	public boolean hayColorSecundario() {	return this.colorSecundario != null;	}
	
	public TipoDePrenda getTipoDePrenda() {
		return tipoDePrenda;
	}
	public Color getColorPrincipal() {
		return colorPrincipal;
	}
	public Material getMaterial() {
		return material;
	}
	public int getTemperaturaIdeal() {
		return temperaturaIdeal;
	}
	public List<String> getClimasApropiados() {
		return climasApropiados;
	}
	
	
	
}

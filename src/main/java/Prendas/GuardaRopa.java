package Prendas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Api.WeatherApiStrategy;
import Exceptions.insuficientesPrendasARangoDeTemperaturaExceptionextends;
import Prendas.Atuendo.Atuendo;
import Usuarios.Propuestas.Propuesta;

public class GuardaRopa
{
	
	private List<Prenda> prendas;



	public GuardaRopa() {
		this.prendas = new ArrayList<>();
	}
	
	//adders y removers :^D______________________________________________________________________________________________________
	public void addPrendas(Prenda prenda)
	{
		this.prendas.add(prenda);
	}
	public void addPrendas(List<Prenda> prendas)
	{
		this.prendas.addAll(prendas);
	}
	public void removePrendas(Prenda prenda)
	{
		this.prendas.remove(prenda);
	}
	public void removePrendas(List<Prenda> prendas)
	{
		for(Prenda prenda: prendas)
			this.prendas.remove(prenda);
		//this.prendas.removeAll(prendas);
	}

	public GuardaRopa(List<Prenda> prendas)
	{
		this.prendas = prendas;
	}
	
	//_____________________________________________________________________________________________________________________________

	
	
	public List<Prenda> getPrendasClone() { return new ArrayList<>(prendas);}
	
	public List<Atuendo> sugerencias(List<Prenda> prendas,int temperatura, int rango)
	{
		return this.sugerencias(this.getPrendasATemperatura(temperatura,rango,prendas));
	}
	public List<Atuendo> sugerencias(List<Prenda> prendas, WeatherApiStrategy weatherApiStrategy,int rango)
	{
		return this.sugerencias(
								this.getPrendasParaClima(weatherApiStrategy.getCondicionesClimaticas("Buenos Aires"),
								this.getPrendasATemperatura((int)weatherApiStrategy.getTemp("Buenos Aires"),rango,
								prendas
								)));
	}
	public List<Atuendo> sugerencias(WeatherApiStrategy weatherApiStrategy,int rango)
	{
		return this.sugerencias(this.prendas,weatherApiStrategy,rango);
	}
	

	public List<Atuendo> sugerencias(List<Prenda> prendas)
	{
		if (prendas.size() < 4)
			throw new insuficientesPrendasARangoDeTemperaturaExceptionextends("Faltan prendas");
		List<int[]> posiblesCombinaciones = this.generate((int) prendas.size(), 4);
		
		List<Prenda[]> combinacionesDePrenda = posiblesCombinaciones.stream().
												map(combinacion -> getMultiplesPrendasDeLista(prendas, combinacion)).
												collect(Collectors.toList());
		
		List<Atuendo> atuendos = new ArrayList<>();
		for (int i = 0; i < combinacionesDePrenda.size(); i++)
		{
			Atuendo atuendo;
			try
			{
				atuendo = new Atuendo(combinacionesDePrenda.get(i));
				atuendos.add(atuendo);
			}
			catch (Exception e)	{}
		}
		return atuendos;
	}
	
	public List<Prenda> getPrendasParaClima(List<String> climas,List<Prenda> prendas)
	{
		return climas.size() == 0 ? prendas : prendas.stream().filter(p -> stringContains(p.getClimasApropiados(), climas)).collect(Collectors.toList());
	}
	
	
	
	private boolean stringContains(List<String> listOfStringsToSearch, List<String> listToSearchStrings)
	{
		return listOfStringsToSearch.size() == 0 ? false : listOfStringsToSearch.stream().allMatch(s->stringContains(s, listToSearchStrings));
	}
	private boolean stringContains(String search, List<String> list)
	{
		return list.stream().anyMatch(s->search.equals(s));
	}
	public List<Prenda> getPrendasATemperatura(int temperatura, int rango,List<Prenda> prendas)
	{
		return  prendas.stream().filter(p -> p.getTemperaturaIdeal() >= (temperatura -rango) && p.getTemperaturaIdeal() <= (temperatura + rango)).collect(Collectors.toList());

	}
	
	
	private void helper(List<int[]> combinations, int data[], int start, int end, int index) {
	    if (index == data.length) {
	        int[] combination = data.clone();
	        combinations.add(combination);
	    } else if (start <= end) {
	        data[index] = start;
	        helper(combinations, data, start + 1, end, index + 1);
	        helper(combinations, data, start + 1, end, index);
	    }
	}
	public List<int[]> generate(int n, int r) {
	    List<int[]> combinations = new ArrayList<>();
	    helper(combinations, new int[r], 0, n-1, 0);
	    return combinations;
	}
	
	public  Prenda[] getMultiplesPrendasDeLista(List<Prenda> items,int[] itemsIndex)
	{
		Prenda[] rescuedItems = new Prenda[itemsIndex.length];
		
		for (int i = 0; i < itemsIndex.length; i++)
			rescuedItems[i] = items.get(itemsIndex[i]);
		
		return  rescuedItems;
	}
}

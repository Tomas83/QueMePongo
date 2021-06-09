package Api;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class AccuWeatherHandler implements WeatherApiStrategy {
	
	private static AccuWeatherHandler accuWeatherHandler;
	private AccuWeatherAPI accuWeatherAPI;
	//private HashMap<LocalDate,List<String>> condicionesClimaticasGuardadas;
	private AccuWeatherHandler() {

		accuWeatherAPI = new AccuWeatherAPI();
		//condicionesClimaticasGuardadas = new HashMap<>();
	}
	
	public static AccuWeatherHandler getAccuWeatherHandler()
	{
		if(accuWeatherHandler == null)
			accuWeatherHandler = new AccuWeatherHandler();
		return accuWeatherHandler;
	}
	
	public float getTemp(String ciudad)
	{
		 HashMap<String, Object> temp = (HashMap<String, Object>)accuWeatherAPI.getWeather(ciudad).get(0).get("Temperature");
		 if(((String)temp.get("Unit")).equals("F"))
			 return fahrenheitToCelsius((int) temp.get("Value"));
		 return (int) temp.get("Value");
	}
	
	private float fahrenheitToCelsius(int fahrenheits)
	{
		return (fahrenheits - 32)*5/9;
	}
	public List<String> getCondicionesClimaticas(String ciudad)
	{
		return (List<String>) accuWeatherAPI.getWeather(ciudad).get(0).get("CurrentAlerts");
	}
	/*
	private void actualizarCondicionesClimaticasGuardadas(String ciudad)
	{
		condicionesClimaticasGuardadas.put(
				LocalDate.now(),
				 (List<String>) accuWeatherAPI.getWeather(ciudad).get(0).get("CurrentAlerts")
				);
	}
	public List<String> getCondicionesClimaticas(String ciudad)
	{
		List<String> condicionesList = condicionesClimaticasGuardadas.get(LocalDate.now());
		if (condicionesList == null)
		{
			actualizarCondicionesClimaticasGuardadas(ciudad);
			condicionesList = condicionesClimaticasGuardadas.get(LocalDate.now());
		}
		return condicionesList;

	}*/
}

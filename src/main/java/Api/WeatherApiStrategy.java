package Api;

import java.util.List;

public interface WeatherApiStrategy {
	
	public float getTemp(String ciudad);
	
	public List<String> getCondicionesClimaticas(String ciudad);
}

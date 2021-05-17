
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Api.*;

public class ApiTest {
	 @Test
	  public void conseguirCombinaciones()
	  {
		 assertEquals(13, new AccuWeatherHandler().getTemp("Buenos Aires"));
	  }
}

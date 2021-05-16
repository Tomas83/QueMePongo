import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import Api.*;

public class ApiTest {
	 @Test
	  public void conseguirCombinaciones()
	  {
		 assertEquals(13, new AccuWeatherHandler().getTemp("Buenos Aires"));
	  }
}

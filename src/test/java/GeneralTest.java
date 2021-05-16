import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import Prendas.GuardaRopa;

public class GeneralTest {

	  @Test
	  public void conseguirCombinaciones()
	  {
		  int[][] c = {{0,1},{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}};
		  List<int[]> combinaciones = Arrays.asList(c);
		  GuardaRopa guardaRopa = new GuardaRopa();
		  List<int[]> combinacionesGeneradas = guardaRopa.generate(5,2);
		  assertEquals(combinaciones.size(),combinacionesGeneradas.size());
		  for (int i = 0; i < combinaciones.size(); i++)
		  {
			  assertArrayEquals(combinaciones.get(i), combinacionesGeneradas.get(i));
		  }  
	  }

}

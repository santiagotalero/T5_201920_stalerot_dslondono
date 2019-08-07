package test.data_structures;

import static org.junit.Assert.*;
import model.data_structures.ArregloDinamico;

import org.junit.Before;
import org.junit.Test;

public class TestDarElemento {
	
	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	

	@Before
	public void setUp() throws Exception {
		arreglo= new ArregloDinamico(TAMANO);
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(""+i);
		}
	}

	@Test
	public void testDarElemento() {
		assertEquals(arreglo.darElemento(0), "0");
		assertEquals(arreglo.darElemento(TAMANO), ""+TAMANO);
		assertEquals(arreglo.darElemento(TAMANO*3), null);
	}

}

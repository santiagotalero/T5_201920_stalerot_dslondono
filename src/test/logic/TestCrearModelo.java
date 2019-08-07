package test.logic;

import static org.junit.Assert.*;
import model.logic.MVCModelo;

import org.junit.Before;
import org.junit.Test;

public class TestCrearModelo {
	
	private MVCModelo modelo;
	private static int TAMANO=10;
	@Before
	public void setUp() throws Exception {
		modelo= new MVCModelo(TAMANO);
	}

	@Test
	public void testMVCModeloInt() {
		assertTrue(modelo!=null);
	}

	@Test
	public void testDarTamano() {
		assertEquals(modelo.darTamano(),TAMANO);
	}

}

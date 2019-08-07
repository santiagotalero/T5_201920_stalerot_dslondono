package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCrearArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	@Before
	public void setUp() throws Exception {
		arreglo= new ArregloDinamico(TAMANO);
	}

	@Test
	public void testArregloDinamico() {
		assertEquals(arreglo.darTamano(),TAMANO);
	}

}

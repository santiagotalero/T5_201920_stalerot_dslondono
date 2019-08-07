package test.logic;

import static org.junit.Assert.*;
import model.logic.MVCModelo;

import org.junit.Before;
import org.junit.Test;

public class TestBuscarAgregarElemento {
	private MVCModelo modelo;
	private static int ELEMENTOS=1000;
	

	@Before
	public void setUp() throws Exception {
		modelo=new MVCModelo();
		for(int i =0; i< ELEMENTOS;i++){
			modelo.agregar(""+i);
		}
	}

	@Test
	public void testBuscar() {
		assertEquals(modelo.buscar("50"), "50");
		assertEquals(modelo.buscar("0"), "0");
		assertEquals(modelo.buscar("999"), "999");
		assertEquals(modelo.buscar("1000"), null);
	}

	@Test
	public void testEliminar() {
		try{
			for (int i = ELEMENTOS-1; i > ELEMENTOS/2; i--) {
				modelo.eliminar(""+i);
			}
			
			assertEquals(modelo.buscar("50"), "50");
			assertEquals(modelo.buscar("0"), "0");
			assertEquals(modelo.buscar("999"), null);
			assertEquals(modelo.buscar("700"), null);
		
		}
		catch(Exception e){
			fail(e.getMessage());
		}
		
	}

}

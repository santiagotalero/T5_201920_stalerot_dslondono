/**
 * 
 */
package test.logic;

import static org.junit.Assert.*;
import model.logic.MVCModelo;

import org.junit.Before;
import org.junit.Test;

/**
 * @author AndresM
 *
 */
public class TestAgregarElemento {
	private MVCModelo modelo;
	private static int ELEMENTOS=1000;
	
	
	@Before
	public void setUp() throws Exception {
		modelo=new MVCModelo();
	}

	/**
	 * Test method for {@link model.logic.MVCModelo#agregar(java.lang.String)}.
	 */
	@Test
	public void testAgregar() {
		try{
			for(int i =0; i< ELEMENTOS;i++){
				modelo.agregar(""+i);
			}
			
		}
		catch(Exception e){
			fail(e.getMessage());
		}
		
	}

}

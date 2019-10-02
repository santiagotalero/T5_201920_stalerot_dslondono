package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("Pulse 1 para cargar archivos. (SOLO SE PUEDE HACER UNA VEZ)");
			System.out.println("Pulse 2 para requerimiento 1. ");
			System.out.println("Pulse 3 para requerimiento 2. ");
			System.out.println("Pulsa 4 para requerimiento 3");
			System.out.println("Pulsa 5 para salir");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}

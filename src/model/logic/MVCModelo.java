package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;


import model.data_structures.HashTableLinearProbing;
import model.data_structures.HashTableSeparateChaining;

import model.data_structures.Queue;


/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private HashTableLinearProbing linearProb;
	private HashTableSeparateChaining separateChain;
	private Queue cola1;
	private Queue cola2;
	private Queue cola3;
	private Queue cola4;
	private int tamano;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		 tamano=0;
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return tamano;
	}

	
	public void cargarArchivos() throws IOException
	{
		CSVReader reader = null;
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-WeeklyAggregate.csv"));

			
			reader.readNext();
			
			cola1= new Queue<TravelTime>();
			for(String[] nextLine : reader)
			{
				TravelTime actual= new TravelTime(1, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				cola1.enqueue(actual);
			}
			tamano += cola1.size();
			TravelTime primero= (TravelTime)cola1.peek();
			TravelTime ultimo=(TravelTime)cola1.bottom();
			System.out.println("Numero de viajes cargados para el 1er trimestre: " + cola1.size()+"\n");
			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime() +"\n");
			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime()+"\n" );

			
//			cola1= new Queue<TravelTime>();
//			int i=0;
//			while(i<500)
//			{
//				String [] nextLine= reader.readNext();
//				TravelTime actual= new TravelTime(1, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
//				cola1.enqueue(actual);
//				i++;
//			}
//			tamano += cola1.size();
//			TravelTime primero= (TravelTime)cola1.peek();
//			TravelTime ultimo=(TravelTime)cola1.bottom();
//			System.out.println("Numero de viajes cargados para el 1er trimestre: " + cola1.size());
//			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime() );
//			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime() );

			

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-WeeklyAggregate.csv"));

			
			reader.readNext();

			
			cola2= new Queue<TravelTime>();
			for(String[] nextLine : reader)
			{
				TravelTime actual= new TravelTime(1, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				cola2.enqueue(actual);
			}
			tamano += cola2.size();
			TravelTime primero= (TravelTime)cola2.peek();
			TravelTime ultimo=(TravelTime)cola2.bottom();
			System.out.println("Numero de viajes cargados para el 2ndo trimestre: " + cola2.size()+"\n");
			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime() +"\n");
			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime()+"\n" );
			
			
			
//			cola2= new Queue<TravelTime>();
//			int i=0;
//			while(i<500)
//			{
//				String [] nextLine= reader.readNext();
//				TravelTime actual= new TravelTime(2, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
//				cola2.enqueue(actual);
//				i++;
//			}
//			tamano += cola2.size();
//			TravelTime primero= (TravelTime)cola2.peek();
//			TravelTime ultimo=(TravelTime)cola2.bottom();
//			System.out.println("Numero de viajes cargados para el 2ndo trimestre: " + cola2.size());
//			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime() );
//			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime() );

			

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-3-WeeklyAggregate.csv"));

			
			reader.readNext();

			
			cola3= new Queue<TravelTime>();
			for(String[] nextLine : reader)
			{
				TravelTime actual= new TravelTime(1, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				cola3.enqueue(actual);
			}
			tamano += cola3.size();
			TravelTime primero= (TravelTime)cola3.peek();
			TravelTime ultimo=(TravelTime)cola3.bottom();
			System.out.println("Numero de viajes cargados para el 3er trimestre: " + cola3.size()+"\n");
			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime()+"\n" );
			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime() +"\n");

			
//			cola3= new Queue<TravelTime>();
//			int i=0;
//			while(i<500)
//			{
//				String [] nextLine= reader.readNext();
//				TravelTime actual= new TravelTime(3, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
//				cola3.enqueue(actual);
//				i++;
//			}
//			tamano += cola3.size();
//			TravelTime primero= (TravelTime)cola3.peek();
//			TravelTime ultimo=(TravelTime)cola3.bottom();
//			System.out.println("Numero de viajes cargados para el 3er trimestre: " + cola3.size());
//			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime() );
//			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime() );

			

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-4-WeeklyAggregate.csv"));

			
			reader.readNext();

			
			cola4= new Queue<TravelTime>();
			for(String[] nextLine : reader)
			{
				TravelTime actual= new TravelTime(1, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				cola4.enqueue(actual);
			}
			tamano += cola4.size();
			TravelTime primero= (TravelTime)cola4.peek();
			TravelTime ultimo=(TravelTime)cola4.bottom();
			System.out.println("Numero de viajes cargados para el 4to trimestre: " + cola4.size()+"\n");
			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime()+"\n" );
			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime()+"\n" );

			
//			cola4= new Queue<TravelTime>();
//			int i=0;
//			while(i<500)
//			{
//				String [] nextLine= reader.readNext();
//				TravelTime actual= new TravelTime(4, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
//				cola4.enqueue(actual);
//				i++;
//			}
//			tamano += cola4.size();
//			TravelTime primero= (TravelTime)cola4.peek();
//			TravelTime ultimo=(TravelTime)cola4.bottom();
//			System.out.println("Numero de viajes cargados para el 4to trimestre: " + cola4.size());
//			System.out.println("Datos primer viaje leido: " + "zona origen: "+primero.getSourceID() +" zona destino: "+ primero.getDstID() + " tiempo promedio: "+primero.getMeanTravelTime() );
//			System.out.println("Datos último viaje leido: " + "zona origen: "+ultimo.getSourceID() +" zona destino: "+ ultimo.getDstID() + " tiempo promedio: "+ultimo.getMeanTravelTime() );


		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	
		linearProb= new HashTableLinearProbing(tamano);
		separateChain= new HashTableSeparateChaining(tamano);
		Queue colas= new Queue<Queue>();
		
		Iterator iter1=cola1.iterator();
		Iterator iter2=cola2.iterator();
		Iterator iter3=cola3.iterator();
		Iterator iter4=cola4.iterator();
		
		while(iter1.hasNext())
		{
			TravelTime actual= (TravelTime) iter1.next();
			String key= actual.getTrimestre()+"-"+actual.getSourceID()+"-"+actual.getDstID();
			boolean existeCola=false;
			Iterator iter=colas.iterator();
			
			while(iter.hasNext())
			{
				Queue colaActual= (Queue) iter.next();
				TravelTime viajeReferencia= (TravelTime) colaActual.bottom();
				String keyReferencia= viajeReferencia.getTrimestre()+"-"+viajeReferencia.getSourceID()+"-"+viajeReferencia.getDstID();
				if(keyReferencia.equals(key))
				{
					existeCola=true;
				}
				if(existeCola)
				{
					colaActual.enqueue(actual);
				}
			}
			if(!existeCola)
			{
				Queue nuevaCola= new Queue();
				nuevaCola.enqueue(actual);
				colas.enqueue(nuevaCola);
			}
		
			
		}
		while(iter2.hasNext())
		{
			TravelTime actual= (TravelTime) iter2.next();
			String key= actual.getTrimestre()+"-"+actual.getSourceID()+"-"+actual.getDstID();
			boolean existeCola=false;
			Iterator iter=colas.iterator();
			
			while(iter.hasNext())
			{
				Queue colaActual= (Queue) iter.next();
				TravelTime viajeReferencia= (TravelTime) colaActual.bottom();
				String keyReferencia= viajeReferencia.getTrimestre()+"-"+viajeReferencia.getSourceID()+"-"+viajeReferencia.getDstID();
				if(keyReferencia.equals(key))
				{
					existeCola=true;
				}
				if(existeCola)
				{
					colaActual.enqueue(actual);
				}
			}
			if(!existeCola)
			{
				Queue nuevaCola= new Queue();
				nuevaCola.enqueue(actual);
				colas.enqueue(nuevaCola);
			}
		
			
		}
		while(iter3.hasNext())
		{
			TravelTime actual= (TravelTime) iter3.next();
			String key= actual.getTrimestre()+"-"+actual.getSourceID()+"-"+actual.getDstID();
			boolean existeCola=false;
			Iterator iter=colas.iterator();
			
			while(iter.hasNext())
			{
				Queue colaActual= (Queue) iter.next();
				TravelTime viajeReferencia= (TravelTime) colaActual.bottom();
				String keyReferencia= viajeReferencia.getTrimestre()+"-"+viajeReferencia.getSourceID()+"-"+viajeReferencia.getDstID();
				if(keyReferencia.equals(key))
				{
					existeCola=true;
				}
				if(existeCola)
				{
					colaActual.enqueue(actual);
				}
			}
			if(!existeCola)
			{
				Queue nuevaCola= new Queue();
				nuevaCola.enqueue(actual);
				colas.enqueue(nuevaCola);
			}
		
			
		}
		while(iter4.hasNext())
		{
			TravelTime actual= (TravelTime) iter4.next();
			String key= actual.getTrimestre()+"-"+actual.getSourceID()+"-"+actual.getDstID();
			boolean existeCola=false;
			Iterator iter=colas.iterator();
			
			while(iter.hasNext())
			{
				Queue colaActual= (Queue) iter.next();
				TravelTime viajeReferencia= (TravelTime) colaActual.bottom();
				String keyReferencia= viajeReferencia.getTrimestre()+"-"+viajeReferencia.getSourceID()+"-"+viajeReferencia.getDstID();
				if(keyReferencia.equals(key))
				{
					existeCola=true;
				}
				if(existeCola)
				{
					colaActual.enqueue(actual);
				}
			}
			if(!existeCola)
			{
				Queue nuevaCola= new Queue();
				nuevaCola.enqueue(actual);
				colas.enqueue(nuevaCola);
			}
		
			
		}
		
		
		
		
		Iterator iter=colas.iterator();
		while(iter.hasNext())
		{
			Queue colaFinalActual= (Queue) iter.next();
			TravelTime viajeReferencia= (TravelTime) colaFinalActual.bottom();
			String keyReferencia=viajeReferencia.getTrimestre()+"-"+viajeReferencia.getSourceID()+"-"+viajeReferencia.getDstID();
			linearProb.put(keyReferencia, colaFinalActual );
			separateChain.put(keyReferencia, colaFinalActual);
		}
	}
	
	
	public Queue req1(int trimestre, int zonaOrigen, int zonaDestino)
	{
		String key=trimestre+"-"+zonaOrigen+"-"+zonaDestino;
		Queue<TravelTime> cola= (Queue<TravelTime>) linearProb.get(key);
		
		//ORDENAR POR DIA DE SEMANA
		
		return cola;
	}
	public Queue req2(int trimestre, int zonaOrigen, int zonaDestino)
	{
		String key=trimestre+"-"+zonaOrigen+"-"+zonaDestino;
		Queue<TravelTime> cola= (Queue<TravelTime>) separateChain.get(key);
		
		
		//ORDENAR POR DIA DE SEMANA
		
		
		return cola;
	}
	public long[] req3()
	{
		long[] tiempos= new long[2];
		// Linear Probing
		int i=0;
		Iterable iterable=linearProb.keys();
		Iterator iter=iterable.iterator();
		Queue llavesLinear= new Queue();
		while(iter.hasNext()&& i<8000)
		{
			String actual= (String) iter.next();
			llavesLinear.enqueue(actual);
			i++;
		}
		int j=0;
		while(j<2000)
		{
			String llave= "llaveNoExistente"+j;
			llavesLinear.enqueue(llave);
			j++;
		}
		
		
		long TInicio, TFin, tiempo1; //Variables para determinar el tiempo de ejecuciÛn
		  TInicio = System.currentTimeMillis();
		i=0;
		while(i<llavesLinear.size())
		{
			String llaveActual= (String) llavesLinear.dequeue();
			linearProb.get(llaveActual);
			i++;
		}
		TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizÛ el algoritmo y la almacenamos en la variable T
		  tiempo1 = TFin - TInicio; //Calculamos los milisegundos de diferencia
		  tiempos[0]=tiempo1;
		//Separate Chaining
		
		i=0;
		iterable=separateChain.keys();
		Iterator iter2=iterable.iterator();
		Queue llavesSeparate= new Queue();
		while(iter2.hasNext()&& i<8000)
		{
			String actual= (String) iter2.next();
			llavesSeparate.enqueue(actual);
			i++;
		}
		j=0;
		while(j<2000)
		{
			String llave= "llaveNoExistente"+j;
			llavesSeparate.enqueue(llave);
			j++;
		}
		
		long TInicio2, TFin2, tiempo2; //Variables para determinar el tiempo de ejecuciÛn
		  TInicio2 = System.currentTimeMillis();
		i=0;
		while(i<llavesSeparate.size())
		{
			String llaveActual= (String) llavesSeparate.dequeue();
			separateChain.get(llaveActual);
			i++;
		}
		TFin2 = System.currentTimeMillis(); //Tomamos la hora en que finalizÛ el algoritmo y la almacenamos en la variable T
		  tiempo2 = TFin2 - TInicio2; //Calculamos los milisegundos de diferencia
		  tiempos[1]=tiempo2;
		  
		  
		  return tiempos;
	}
	
}

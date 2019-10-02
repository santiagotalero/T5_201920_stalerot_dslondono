package model.data_structures;

import java.util.Iterator;



public class HashTableSeparateChaining <Key, Value> implements ISymbolTable<Key, Value>
{
	//------------------------------------
	//Constantes
	//------------------------------------

	public static final double LIMITE_FACTOR_CARGA = 5.0;

	//------------------------------------
	// Atributos
	//------------------------------------

	/*
	 * Atrivuto que almacena el factor de carga
	 */
	private double factorCarga;

	/*
	 * Atrivuto que lamcanea el n�mero de llamso almacenadas
	 */
	private int numLlaves;

	/**
	 * Atributo que almacena el n�mero de casillas
	 */
	private int numCasillas;

	/**
	 * Atributo que almacena los nodos que contien los arreglos de llaves y valores para cada hash. 
	 */
	private Node<Key, Value>[] elementos;

	/**
	 * Almacena en numero de rehash realizados
	 */
	private int numReHash;
	
	//------------------------------------
	//Constructor
	//------------------------------------

	/**
	 * Construye una tabla con el numero de casillas dado por aprametro
	 * @param tam numero de casillas inicial. Pre: es n�mero impar.
	 */
	public HashTableSeparateChaining(int tam)
	{
		numLlaves=0;
		numCasillas= nextPrime(tam);
		factorCarga=0;
		elementos = new Node[numCasillas];
		numReHash=0;
	}

	public HashTableSeparateChaining()
	{
		numLlaves=0;
		numCasillas= nextPrime(5000+1);
		factorCarga=0;

		elementos = new Node[numCasillas];
	}

	//------------------------------------
	// M�todos
	//------------------------------------

	/**
	 * Agrega un elemento a la tabla seg�n su has code. Si la llave ya est�, atualiza el valor.
	 *@param key: llave del elemento a agregar
	 *@param val: valor del elemnto a agregar
	 */
	public void put(Key key, Value val) 
	{
		//Priemero agregamos
		agregar(key,val);

		//Luego v�lidamos si hay que hacer rehash		
		if(factorCarga>LIMITE_FACTOR_CARGA)
		{
			numReHash++;
			rehash();
		}
	}

	/**
	 * Busca y restona el valor de la llave dada por parametro
	 * @param key: llave de la que se quiere el valor
	 * @return valor de la llave. Un objeto de la clase Object.
	 * 
	 */
	public Value get(Key key) {
		int hashVal = hash(key);
		Value valor = (Value) new Object();

		if (elementos[hashVal]!=null)
			valor=elementos[hashVal].buscar(key);

		return valor;
	}

	/**
	 * Eliminar un elemento de la tabla por llave. Si la llave no est� en la lista, no hace nada.
	 * @param llave de la tupla que se queire eliminar
	 */
	public void delete(Key key) 
	{
		int hashVal = hash(key);
		if (elementos[hashVal]!=null)
		{
			if (elementos[hashVal].eliminar(key))
			{
				numLlaves--;
				factorCarga= numLlaves/numCasillas;
				if (elementos[hashVal].isEmpty())
					elementos[hashVal]=null;
			}
		}

	}

	@Override
	public boolean contains(Key key) 
	{
		boolean contiene= false;
		int hashVal = hash(key);
		if (elementos[hashVal]!=null && elementos[hashVal].buscar(key)!=null) 
		{
			contiene=true;
		}
		return contiene;
	}

	@Override
	public boolean isEmpty() {

		if (numLlaves==0)
			return true;

		return false;
	}

	@Override
	/**
	 * Retorna el numero de tuplas almacenadas
	 */
	public int size() {
		return numLlaves;
	}

	

	/**
	 * Encuentra el siguente n�mero primo a el valor dado por parametro
	 * @param semilla: valor despu�s del cual se quiere hallar el n�mero primo. Es un n�mero impar. 
	 * @return seguiente n�mero primo
	 */
	private int nextPrime(int semilla)
	{
		int primo=semilla;
		boolean encontrado =false;

		while(!encontrado)
		{
			int count=2;
			boolean isPrime = true;

			//Validamos si es primo
			while (isPrime && count < primo)
			{
				if (primo%count==0)
					isPrime= false;

				count++;
			}

			if(!isPrime)
				primo= primo+1;
			else
				encontrado=true;

		}

		return primo;
	}


	private void agregar(Key key, Value val)
	{
		int hashVal = hash(key);
		if(elementos[hashVal]==null)
		{
			elementos[hashVal]= new Node(key,val);
			numLlaves ++;
			factorCarga= ((double)numLlaves)/numCasillas;
		}
		else
		{
			elementos[hashVal].agregar(key, val);
			numLlaves ++;
			factorCarga=((double)numLlaves)/numCasillas;
		}
	}

	/**
	 * Retorna el numero de casillas que tiene la tabla.
	 * @return tama�o de la tabla
	 */
	public int getNumCasillas()
	{
		return numCasillas;
	}
	
	/**
	 * Retorna el n�emro de veces que la tabla a aumentado su tama�o
	 * @return vecces que se ha hecho reHash
	 */
	public int getNumReHash()
	{
		return numReHash;
	}
	
	public double getFactorCarga()
	{
		return factorCarga;
	}

	/**
	 * Funci�n encargada de generar un identificador para cada llave.
	 * @param key Llave de la que se queire el identificador.
	 * @return N�mero entero correspondiente al c�digo identificador de la llave. 
	 */
	private int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % numCasillas;
	}

	/**
	 * Aumenta el tama�o de la tabla y re acomoda los elementos ya almacenados
	 */
	private void rehash()
	{
		//Reiniciamos el arreglo
		numCasillas= nextPrime(numCasillas*2 +1);
		numLlaves=0;
		factorCarga=0;
		Node<Key,Value>[] temElem= elementos;
		elementos=	new Node[numCasillas];

		//Agregamos nuevaente los elementos
		for(int i= 0; i <temElem.length; i ++)
		{
			if(temElem[i]!=null) 
			{
				Key [] keys= temElem[i].darLlaves();
				Value [] vals= temElem[i].darValores();

				for (int j=0; j < keys.length ;j++)
				{
					if (keys[j]!=null)
						agregar(keys[j], vals[j]);
				}
			}
		}


	}

	private class Node <Key, Value>

	{
		//------------------------------------
		// Atributos
		//------------------------------------		

		/**
		 * Atributo que almacena las llaves que est�n en en la casilla
		 */
		private Key[] hermanosKey;

		/**
		 * Atributo que almacena las llaves que est�n en en la casilla
		 */
		private Value[] hermanosVal;

		/**
		 * Atributo que alamacena el tama�ao del arreglo que tiene el nodo para guradar los elementos con el mismo hash. 
		 */
		private int tam;

		/**
		 * Almacena la cantidad de elementos con el mismo hash que se esta almacenando
		 */
		private int numHermanos;

		//------------------------------------
		// Constructor
		//------------------------------------

		/**
		 * M�todo constructor. Crea un nuevo nodo
		 * @param key llave del priemer elemento con el hash correspondiente al nodo.
		 * @param val valor del primer elemments con el hash correspodiente a nodo. 
		 */
		public Node(Key key, Value val)
		{
			tam=7;
			numHermanos=0;
			hermanosKey = (Key[]) new Object [tam];
			hermanosVal = (Value[]) new Object [tam];

			for (int i =0; i < hermanosKey.length; i ++) 
			{
				hermanosKey[i]=null;
				hermanosVal[i]=null;
			}

			agregar(key,val);

		}

		public boolean isEmpty() 
		{
			boolean vacio = true;

			for (int i =0; i < hermanosKey.length && vacio; i ++) 
			{
				if(hermanosKey[i]!=null)
				{
					vacio= false;
				}
			}

			return vacio;
		}

		/**
		 * Elimina la tupla con la llave dada. Si no ecuentra la llave y retorna false.
		 * @param key llave de la tupla a eliminar
		 * @return retorna true si elimina la llave, false de lo contrario. 
		 */
		public boolean eliminar(Key key) 
		{
			boolean eliminado=false;
			for (int i =0; i < hermanosKey.length && !eliminado ; i ++) 
			{
				if(hermanosKey[i]!=null&&hermanosKey[i].equals(key))
				{
					hermanosVal[i]= null;
					hermanosKey[i]= null;
					eliminado=true;
					numHermanos--;
					reAcomodar(hermanosKey, hermanosVal, hermanosKey,hermanosVal);
				}
			}

			return eliminado;

		}

		//------------------------------------
		// M�todos
		//------------------------------------

		/**
		 * Busca si llave dada por parametro est� o no en los elementos alamacenado
		 * @param key: llave que se est� buscando
		 * @return retorn el valor correspodiene a la llave dada. Null si no ecuntra la llave.
		 */
		public Value buscar(Key key) {

			Value valor = null;
			for (int i =0; i < hermanosKey.length && valor == null; i ++) 
			{
				if(hermanosKey[i]!=null)
				{
					if(hermanosKey[i].equals(key))
					{
						valor= hermanosVal[i];
					}
				}
			}
			
			if(valor==null)
				valor = (Value) new Object();

			return valor;
		}

		/**
		 * Agrega un elemento en la lisa de hermanos con el mimso has.
		 * @param key lleve del elemento a agregar
		 * @param val valor del elemento a agregar
		 */
		private void agregar(Key key, Value val)
		{
			if (numHermanos!=tam)
			{
				boolean agregado =false;
				int i;
				//Buscamos si elemento ya est�
				for (i=0;i <numHermanos && !agregado ; i++)
				{
					if(hermanosKey[i].equals(key))
					{
						hermanosVal[i]=val;
						agregado=true;
						numLlaves--;
					}
				}
				//Consideramso el caso de no econtrado y de que no haya hermanos
				if (!agregado && i==numHermanos)
				{
					hermanosKey[numHermanos]=key;
					hermanosVal[numHermanos]=val;
					numHermanos++;		
				}

			}
			else
			{
				crecer();
				agregar(key, val);
			}
		}

		/**
		 * Este metodo aumenta el tama�o del arreglo en el 50% y cambia los arreglos exitentes
		 */
		private void crecer() 
		{
			tam = (int) (tam + tam*0.5);
			Key[] temKey= (Key[]) new Object [tam];
			Value[] temVal = (Value[]) new Object [tam];

			reAcomodar(hermanosKey, hermanosVal, temKey, temVal);
			hermanosKey= temKey;
			hermanosVal= temVal;

		}

		/**
		 * Este m�todo cambia los elementos de un arreglo a otro, validando que no sean nulos los elementos de arrelgo viejo
		 * @param arregloOldKey arreglo viejo de las llaves
		 * @param arregloOldVal arreglo viejo de los valores
		 * @param arregloNewKey arreglo nuevo de las llevas
		 * @param arregloNewVal arreglo nuevo de los valores.
		 */
		private void  reAcomodar(Key[] arregloOldKey,Value[] arregloOldVal, Key[] arregloNewKey,Value[] arregloNewVal)
		{
			int j=0;
			for(int i=0;i< arregloOldKey.length; i++)
			{
				if(arregloOldKey[i] != null) 
				{
					arregloNewKey[j]=arregloOldKey[i];
					arregloNewVal[j]=arregloOldVal[i];
					j++;
				}
			}
		}

		/**
		 * Retorna las llaves de los elementos gurdados en el nodo. 
		 * @return llaves de los gaurdados
		 */
		private Key[] darLlaves()
		{
			return hermanosKey;
		}

		/**
		 * Retorna las valores de los elementos gurdados en el nodo. 
		 * @return valores de los elementos gurdados
		 */
		private Value[] darValores()
		{
			return hermanosVal;
		}


	}

	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < numCasillas; i++)
		{
			if(elementos[i] != null)
			{
				Key[] keys = elementos[i].darLlaves();
				for(Key j: keys)
				{
					if(j !=null)
						queue.enqueue(j);

				}

			}
		}
		return (Iterable<Key>) queue;
	}



}

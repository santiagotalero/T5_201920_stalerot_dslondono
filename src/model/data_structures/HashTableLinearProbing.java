package model.data_structures;

import java.util.Iterator;


public class HashTableLinearProbing<Key, Value> implements ISymbolTable<Key, Value>
{
	// Constantes
	
	/**
	 * Constante que representa la capacidad inicial de la hash table si no se especifica.
	 */
	public static final int CAPACIDAD_INICIAL = 97;
	
	// Atributos
	
	/**
	 * Número de tuplas llave-valor.
	 */
	private int N;
	
	/**
	 * Tamaño de la tabla.
	 */
	private int M;
	
	/**
	 * Arreglo de llaves.
	 */
	private Key[] keys;
	
	/**
	 * Arreglo de valores.
	 */
	private Value[] values;
	
	// Atributos extra
	
	private int numHash;
	
	// Constructor
	
	/**
	 * Construye la tabla de hash con linear probing.
	 */
	public HashTableLinearProbing()
	{
		this(CAPACIDAD_INICIAL);
	}
	
	/**
	 * Construye la tabla de hash con linear probing del tamaño pasado por parámetro.
	 * @param pCap Tamaño de la tabla de hash.
	 */
	public HashTableLinearProbing(int pCap)
	{
		N = 0;
		M = pCap;
		keys = (Key[]) new Object[M];
		values = (Value[]) new Object[M];
		// Inicialización extra
		numHash = 0;
	}
	
	// Métodos
	
	/**
	 * Pone la tupla llave-valor en la tabla (remueve la llave de la tabla si el valor es null).
	 * @param key La llave de la tupla a introducir. 
	 * @param val El valor de la tupla a introducir. Si es null, elimina la llave.
	 */
	public void put(Key key, Value val)
	{
		if(key == null)
			return;
		if(val == null)
		{
			delete(key);
			return;
		}
		if(N >= 0.75*M)
			resize(2*M);
		int i;
		for(i = hash(key); keys[i] != null; i = (i+1)%M)
		{
			if(keys[i].equals(key))
			{
				values[i] = val;
				return;
			}
		}
		keys[i] = key;
		values[i] = val;
		N++;
	}

	/**
	 * Retorna el valor asignado a la llave por parámetro.
	 * @param key Llave del valor a buscar.
	 * @return El valor de la llave correspondiente.
	 */
	public Value get(Key key)
	{
		if(key == null)
			return null;
		for(int i = hash(key); keys[i] != null; i = (i+1)%M)
		{
			if(keys[i].equals(key))
				return values[i];
		}
		return null;
	}

	/**
	 * Remueve la llave y su correspondiente valor de la tabla.
	 * @param key Llave de la tupla a eliminar.
	 */
	public void delete(Key key)
	{
		if(key == null)
			return;
		if(!contains(key))
			return;
		int i = hash(key);
		while(!key.equals(keys[i]))
			i = (i+1)%M;
		keys[i] = null;
		values[i] = null;
		i = (i+1)%M;
		while(keys[i] != null)
		{
			Key tempKey = keys[i];
			Value tempVal = values[i];
			keys[i] = null;
			values[i] = null;
			N--;
			put(tempKey, tempVal);
			i = (i+1)%M;
		}
		N--;
		if(N > 0 && N <= M/8)
			resize(M/2);
		assert invariant();
	}

	/**
	 * @param key Llave a revisar si está en la tabla.
	 * @return True si hay un valor correspondiente a la llave, false de lo contrario.
	 */
	public boolean contains(Key key)
	{
		return key != null ? get(key) != null : false;
	}

	/**
	 * @return True si la tabla está vacía, false de lo contrario.
	 */
	public boolean isEmpty()
	{
		return N == 0;
	}

	/**
	 * @return Número de pares llave-valor en la tabla. 
	 */
	public int size()
	{
		return N;
	}

	
	/**
	 * Función encargada de generar un identificador único por cada llave.
	 * @param key Llave a la que se le hará hash.
	 * @return Número entero correspondiente al código único de la llave. 
	 */
	private int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	/**
	 * Redimensiona la tabla a la capacidad dada, haciendo rehash a todas las llaves.
	 * @param pCap Nuevo tamaño máximo de la tabla.
	 */
	private void resize(int pCap)
	{
		HashTableLinearProbing<Key, Value> table = new HashTableLinearProbing<Key, Value>(pCap);
		for(int i = 0; i < M; i++)
		{
			if(keys[i] != null)
				table.put(keys[i], values[i]);
		}
		keys = table.keys;
		values = table.values;
		M = table.M;
		numHash++;
	}
	
	// Métodos extra
	
	public int darM()
	{
		return M;
	}
	
	public double darFactorDeCarga()
	{
		return N/M;
	}
	
	public int darNumeroDeRehashes()
	{
		return numHash;
	}
	
	// Invariante
	
	/**
	 * Revisa si la tabla de hash es correcta.
	 * @return True si está en buen estado, false de lo contrario.
	 */
	private boolean invariant()
	{
		if(M < 2*N)
			return false;
		for(int i = 0; i < M; i++)
		{
			if(keys[i] == null)
				continue;
			else if(get(keys[i]) != values[i])
				return false;
		}
		return true;
	}

	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0; i < M; i++)
		{
			if(keys[i] != null)
				queue.enqueue(keys[i]);
		}
		return (Iterable<Key>) queue;
	}

}

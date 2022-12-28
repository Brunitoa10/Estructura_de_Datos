package F_TDA_Mapeo;

import A_Excepciones.InvalidKeyException;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;

public class MapeoConHashAbierto<K,V> implements Map<K,V>{
	
	/**
	 * Arreglo de mapeos que modela la tabla hash.
	 */
	protected Map<K,V> [] A;
	
	/**
	 * Cantidad de entradas del mapeo.
	 */
	protected int size;
	
	/**
	 * Tamaño del arreglo A.
	 */
	protected int primo = 13;
	
	/**
	 * Factor de carga de la tabla hash.
	 */
	protected final float factorDeCarga=0.9f;
	
	/**
	 * Inicializa un mapeo con hash abierto vacío.
	 */
	public MapeoConHashAbierto() {
		size = 0;
		A=(Map<K,V> []) new MapeoConListaDoblementeEnlazada[primo];
		for (int i=0;i<A.length;i++) {
			A[i] = new MapeoConListaDoblementeEnlazada<K,V>();
		}
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Devuelve un número con el código hash de la clave key.
	 * @param key Clave a partir de la cual se calcula el código hash.
	 * @return Número con el código hash de la clave key.
	 */
	private int funcionHash(K key) {
		return Math.abs(key.hashCode() % primo);
	}
	
	@Override
	public V get(K key) throws InvalidKeyException {
		if (key==null) {
			throw new InvalidKeyException("Error (get(key)) --> Clave null.");
		}
		return A[funcionHash(key)].get(key);
	}
	
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Error (put(key,value)) --> Clave null.");
		}
		V retorna=null;
		if ((size/primo) >= factorDeCarga) {
			reHash();
		}
		retorna = A[funcionHash(key)].put(key,value);
		if (retorna == null) {//si retorna es igual a null, esto quiere decir que el put de MapeoConLista insertó la entrada
			size++;
		}
		return retorna;
	}
	
	/**
	 * Aumenta el tamaño del arreglo A con el siguiente numero primo del doble de su anterior tamaño.
	 */
	private void reHash(){
		primo = siguientePrimo(primo*2);
		Map<K,V>[] arregloNuevo = (Map<K,V>[]) new  MapeoConListaDoblementeEnlazada[primo];
		try{
			for(int i=0; i < arregloNuevo.length; i++) {
				arregloNuevo[i] = new MapeoConListaDoblementeEnlazada<K,V>();
			}
			for (Entry<K,V> entrada: entries()){
				//inserta con el codigo de hash actualizado
				arregloNuevo[Math.abs(entrada.getKey().hashCode()) % arregloNuevo.length].put(entrada.getKey(),entrada.getValue());
			}
			A=arregloNuevo;
		}
		catch (InvalidKeyException e){
			e.getStackTrace();
		}
	}

	/**
	 * Devuelve el siguiente numero primo al entero p.
	 * @param p Entero a partir del cual se busca su siguiente número primo.
	 * @return Siguiente numero primo al entero p.
	 */
	private int siguientePrimo(int p) {
		boolean encontro=false;
		int retorna = 0;
		if (esPrimo(p)) {//para el caso cuando N=1, donde p sería igual a 2
			encontro = true;
			retorna = p;
		}
		else {
			p++;
			for (int i = p; !encontro ;i++) {
				if (esPrimo(i)) {
					encontro = true;
					retorna = i;
				}
			}
		}
		return retorna;
	}
	
	/**
	 * Consulta si el entero p es un número primo.
	 * @param n Entero a determinar si un número primo.
	 * @return Verdadero si el entero p es un número primo, falso en caso contrario.
	 */
	private boolean esPrimo(int n){
		int cont = 0;
		  boolean primo = true;
		  for (int i=1 ; i<n && primo; i++) {
			  if (n%i == 0) {
				  cont++;
			  }
			  primo = cont < 2;
		  }
		  return primo;
	}		
	
	@Override
	public V remove (K k) throws InvalidKeyException {
		V retorna=null;
		if (k == null) {
			throw new InvalidKeyException("Clave inválida");
		}
		try {
			retorna = A[funcionHash(k)].remove(k);
			if (retorna!=null) {//si retorna no es nulo, esto quiere decir que el remove de MapeoConLista removió la entrada
				size--;
			}
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return retorna;
	}
	
	@Override
	public Iterable<K> keys() {
		PositionList<K> lista = new listaDoblementeEnlazada<K>();
		PositionList<K> claves = new listaDoblementeEnlazada<K>();
			for (int i=0;i<A.length;i++) {
				claves=(PositionList<K>) A[i].keys();
				for (K p:claves) {//por cada clave de la lista de claves de cada elemento del arreglo
					lista.addLast(p);
				}
			}
		return lista;
	}
	
	@Override
	public Iterable<V> values() {
		PositionList<V> lista = new listaDoblementeEnlazada<V>();
		PositionList<V> valores = new listaDoblementeEnlazada<V>();
		for (int i=0;i<A.length;i++) {
			valores=(PositionList<V>) A[i].values();
			for (V p:valores) {//por cada valor de la lista de valores de cada elemento del arreglo
				lista.addLast(p);
			}
		}
		return lista;
	}
	
	@Override
	public Iterable<Entry<K,V>> entries() {		
		PositionList<Entry<K,V>> lista = new listaDoblementeEnlazada<Entry<K,V>>();
		PositionList<Entry<K,V>> entradas = new listaDoblementeEnlazada<Entry<K,V>>();
		for (int i=0; i<A.length; i++) {
			entradas = (PositionList<Entry<K,V>>) A[i].entries();
			for (Entry<K,V> p:entradas) {//por cada entrada de la lista de entradas de cada elemento del arreglo
				lista.addLast(p);
			}
		}
		return lista;
	}
}

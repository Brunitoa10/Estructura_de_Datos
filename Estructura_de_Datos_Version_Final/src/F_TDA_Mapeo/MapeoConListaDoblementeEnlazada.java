package F_TDA_Mapeo;

import A_Excepciones.InvalidKeyException;
import A_Excepciones.InvalidPositionException;
import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;
import E_TDA_Iterable.Iterable;
import E_TDA_Iterable.Iterator;

public class MapeoConListaDoblementeEnlazada<K,V> implements Map<K,V>{
	
	//Atributos
	/* El estado interno del mapeo esta representado con una lista doblemente enlazada de entrada (clave, valor) donde
	 * clave tiene tipo K y valor tiene tipo V
	 */
	
	protected PositionList<Entrada<K,V>> list;
	
	//Constructor
	public MapeoConListaDoblementeEnlazada() {
		list = new listaDoblementeEnlazada<Entrada<K,V>> ();
	}
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public V get(K key) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("Error (get(key)) --> clave null.");
		}
		Iterator<Position<Entrada<K,V>>> it = list.positions().iterator();
		Position<Entrada<K,V>> p;
		V retorno = null;
		
		while(it.hasNext() && retorno == null) {
			p = it.next();
			if(p.element().getKey().equals(key)) {
				retorno = p.element().getValue();
			}
		}
		return retorno;
	}

	public V put(K key, V value) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("Error (put(key,value)) --> clave null.");
		}
		
		Iterator<Position<Entrada<K,V>>> it = list.positions().iterator();
		Position<Entrada<K,V>> p;
		V retorno = null;
		boolean entre = false;
		
		//Mientras tenga elementos para iterar y retorno sea null
		while(it.hasNext() && retorno == null) {
			p = it.next();
			//Si la clave de la entrada actual es key
			if(p.element().getKey().equals(key)) {
				//Marco como que encontre la entrada con clave key
				entre = true;
				//Guardo el valor de value en retorno
				retorno = p.element().getValue();
				//Setear el nuevo valor de la entrada a value
				p.element().setValue(value);
			}
		}
		//Si no la encontre
		if(!entre) {
			//Si sali del while entonces no encontre ninguna entrada con clave key
			list.addLast(new Entrada<K,V>(key,value));
		}
		return retorno;
	}
	
	public V remove(K key) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("Error (remove(key)) --> clave null.");
		}
		
		V retorno = null;
		Iterator<Position<Entrada<K,V>>> it = list.positions().iterator();
		Position<Entrada<K,V>> p;
		
		try {
			//Mientras exista siguiente y retorno sea null
			while(it.hasNext() && retorno == null) {
				//Obtengo el elemento
				p = it.next();
				//Si la clave this es igual a la pasada por parametro
				if(p.element().getKey().equals(key)) {
					retorno = p.element().getValue();
					list.remove(p);
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}		
		return retorno;
	}

	public Iterable<K> keys() {
		return null;
	}
	
	public Iterable<V> values() {
		return null;
	}
	
	public Iterable<Entry<K,V>> entries() {
		return null;
	}
}

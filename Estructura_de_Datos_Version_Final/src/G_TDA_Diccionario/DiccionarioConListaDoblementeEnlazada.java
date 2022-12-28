package G_TDA_Diccionario;

import java.util.Iterator;

import A_Excepciones.InvalidEntryException;
import A_Excepciones.InvalidKeyException;
import A_Excepciones.InvalidPositionException;
import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;
import F_TDA_Mapeo.Entrada;
import F_TDA_Mapeo.Entry;

public class DiccionarioConListaDoblementeEnlazada<K, V> implements Dictionary<K, V> {
	
	//Atributos de instancia
	
	protected listaDoblementeEnlazada<Entry<K,V>> list;
	
	//Constructor
	public DiccionarioConListaDoblementeEnlazada() {
		list = new listaDoblementeEnlazada<Entry<K,V>>();
	}
	
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("Error Diccionario (insert(key,value)) --> Clave null.");
		}
		Entry<K,V> retorno = null;  
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			 retorno = it.next().element();
			if(retorno.getKey().equals(key)) {
				encontre = true;
			}
		}
		return retorno;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("Error Diccionario (insert(key,value)) --> Clave null.");
		}
		PositionList<Entry<K,V>> retorno = new listaDoblementeEnlazada<Entry<K,V>>();  
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		Position<Entry<K, V>> elem = null;
		while(it.hasNext()) {
			 elem = it.next();
			if(elem.element().getKey().equals(key)) {
				retorno.addLast(elem.element());
			}
		}
		return retorno;
	}

	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("Error Diccionario (insert(key,value)) --> Clave null.");
		}
		Entry<K,V> e = new Entrada<K,V>(key,value);
		list.addLast(e);
		return e;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		boolean encontre = false;
		Position<Entry<K, V>> tmp = null; 
		try {
			while(it.hasNext() && !encontre) {
				tmp = it.next(); 
				if(tmp.element() == e) {
					encontre = true;
					list.remove(tmp);
				}
			}		
		} catch (InvalidPositionException e1) {
				e1.printStackTrace();
		}
		if(!encontre) {
			throw new InvalidEntryException("Error Diccionario (remove(e)) --> La entrada 'e' no pertenece al diccionario.");
		}
		return tmp.element();
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		return list;
	}

}

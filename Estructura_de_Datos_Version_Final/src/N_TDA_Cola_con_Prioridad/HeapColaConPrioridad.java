package N_TDA_Cola_con_Prioridad;

import java.util.Comparator;

import A_Excepciones.EmptyPriorityQueueException;
import A_Excepciones.InvalidKeyException;
import F_TDA_Mapeo.Entrada;
import F_TDA_Mapeo.Entry;

public class HeapColaConPrioridad<K,V> implements PriorityQueue<K,V> {
	
	//Atributos de instancia
	protected Entrada<K,V> [] arreglo;
	protected Comparator<K> comp;
	protected int size;
	
	//Constructor
	public HeapColaConPrioridad(int max, Comparador<K> comp) {
		arreglo = (Entrada<K,V>[]) new Entrada[max];
		this.comp = comp;
		size = 0;
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(size == 0) {
			throw new EmptyPriorityQueueException("Error (min()) --> Cola con prioridad vacia.");
		}
		return arreglo[1]; //Por teoria la componenete 0 esta vacia
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Entrada<K,V> entrada = null;
		int pos = 0;
		boolean seguir = true;;
		Entrada<K,V> elemActual = null;
		Entrada<K,V> elemPadre = null;
		Entrada<K,V> aux = null;
		
		if(key == null) {
			throw new InvalidKeyException("Error (insert(key,value)) --> La clave es null.");
		}
		
		entrada = new Entrada<K,V>(key,value);
		
		size++;
		if(size+1 == arreglo.length) {
			agrandar();
		}
		
		pos = size;
		arreglo[pos] = entrada;
		
		while(pos > 1 && seguir) {
			elemActual = arreglo[pos];
			elemPadre = arreglo[pos/2];
			if(comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) {
				aux = arreglo[pos];
				arreglo[pos] = arreglo[pos/2];
				arreglo[pos/2] = aux;
				pos = pos/2;
			}else {
				seguir = false;
			}
		}
		return entrada;
	}//T_insert(n) = O(log_2(n))
	
	private void agrandar() {
		Entrada<K,V> arregloAnterior[];
		arregloAnterior = arreglo;
		arreglo = (Entrada<K,V>[]) new Entrada[arregloAnterior.length*2];
		for(int i = 1 ; i <= size ; i++) {
			arreglo[i] = arregloAnterior[i];
		}
	}
	
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		int pos = 0;
		Entrada<K,V> tmp = null;
		Entry<K,V> entrada = null;
		int posHijoIzquierdo = 0;
		int posHijoDerecho = 0;
		int posMinima = 0;
		boolean seguir = true;
		boolean tieneHijoIzquierdo  = false;
		boolean tieneHijoDerecho = false;
		
		if(size == 0) {
			throw new EmptyPriorityQueueException("Error (removeMin()) --> Cola con prioridad vacia.");
		}
		
		entrada = arreglo[1];
		
		if(size == 1) {
			arreglo[1] = null; 
			size--;
		}else {
			arreglo[1] = arreglo[size];//El ultimo elemento del arreglo 
			arreglo[size] = null;
			size--;
			
			pos = 1;
			while(seguir) {
				
				posHijoIzquierdo = pos*2;
				posHijoDerecho = pos*2+1;
				tieneHijoIzquierdo = posHijoIzquierdo <= size;
				tieneHijoDerecho = posHijoDerecho <= size;
			
				if(!tieneHijoIzquierdo)//Indica que llego a una hoja
					seguir = false;
				else {
					if(tieneHijoDerecho) {//Debo calcular cual es el menor de los hijos
						if(comp.compare(arreglo[posHijoIzquierdo].getKey(), arreglo[posHijoDerecho].getKey()) < 0)
							posMinima = posHijoIzquierdo;
						else
							posMinima = posHijoDerecho;
					}else //no tiene hijo derecho la posmin es del hijo izq
						posMinima = posHijoIzquierdo;
				}
				//Ver si hay que intercambiar el actual con el menor de sus hijos
				if(seguir && comp.compare(arreglo[pos].getKey(),arreglo[posMinima].getKey()) > 0){
					tmp = arreglo[pos];//intercambio
					arreglo[pos] = arreglo[posMinima];
					arreglo[posMinima] = tmp;
					pos = posMinima;
				}else
					seguir = false;
			}
		}
		return entrada;
	}
}

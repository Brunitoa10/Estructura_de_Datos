package N_TDA_Cola_con_Prioridad;

import A_Excepciones.EmptyPriorityQueueException;
import A_Excepciones.InvalidKeyException;
import F_TDA_Mapeo.Entrada;
import F_TDA_Mapeo.Entry;

public class HeapColaConPrioridad<K,V> implements PriorityQueue<K,V> {
	
	//Atributos de instancia
	protected Entrada<K,V> [] arreglo;
	protected Comparador<K> comp;
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
		if(key == null) {
			throw new InvalidKeyException("Error (insert(key,value)) --> La clave es null.");
		}
		//Creo una entrada nueva
		Entrada<K, V> entrada = new Entrada<K, V> (key,value);
		//Incremento size e inserto la nueva entrada al final
		arreglo[++size] = entrada;
		int pos = size;
		boolean seguir = true;
		Entrada<K, V> elemActual = null;
		Entrada<K, V> elemPadre = null;
		Entrada<K, V> tmp = null;
		//Burbujeo hacia arrriba
		while(pos > 1 && seguir) {
			elemActual = arreglo[pos]; //Obtengo el elemento actual
			elemPadre = arreglo[pos/2]; //Obtengo el padre de la i-esima entrada
			if(comp.compare(elemActual.getKey() , elemPadre.getKey()) < 0) {
				//Intercambio entrada si estan desordenadas
				tmp = arreglo[pos];
				arreglo[pos] = arreglo[pos/2];
				arreglo[pos/2] = tmp;
				pos = pos/2; //Reinicio pos con el indice de su padre
			}else {
				//Si no puedo intercambiar la entrada ya esta ordenada
				seguir = false;
			}
		}
		return entrada;
	}//T_insert(n) = O(log_2(n))

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(size == 0) {
			throw new EmptyPriorityQueueException("Error (removeMin()) --> Cola con prioridad vacia.");
		}
		Entry<K, V> entrada = min(); //Salvo el valor a retornar
		Entry<K, V> retorno = null;
		if(size == 1) {
			arreglo[1] = null;
			size = 0;
			retorno = entrada;
		}else {
			//Paso la ultima entrada a la raiz y la borro del final del arreglo y decremento size
			arreglo[1] = arreglo[size];
			arreglo[size] = null;
			size--;
			//Burbujeo la nueva raiz hacia abajo buscando su ubicacion correcta
			int pos = 0;
			boolean seguir = true;
			while(seguir) {
				//Calculo la posicion de los hijos izquierdo y derecho de pos y analizo si existen verdaderamente
				int hijo_izquierdo = pos*2;
				int hijo_derecho = pos*2+1;
				boolean tieneHijoIzquierdo = hijo_izquierdo <= size;
				boolean tieneHijoDerecho = hijo_derecho <= size;
				if(!tieneHijoIzquierdo) {
					//Si no hay hijo izquierdo es porque llegue a una hoja
					seguir = false;
				}else {
					//En posMin voy a computar la posicion del minimo de los hijos de pos
					int posMinimo;
					if(tieneHijoDerecho) {
						//Calculo cual es el menor de los hijos usando el comparador de prioridades
						if(comp.compare(arreglo[hijo_izquierdo].getKey(), arreglo[hijo_derecho].getKey()) < 0){
							posMinimo = hijo_izquierdo;
						}else {
							posMinimo = hijo_derecho;
						}
					}else {
						posMinimo = hijo_izquierdo;
					}
				}
			}
		}
		return retorno;
	}
}

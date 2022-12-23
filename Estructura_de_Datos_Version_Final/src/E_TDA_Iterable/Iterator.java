package E_TDA_Iterable;

import Excepciones.NoSuchElementException;

public interface Iterator<E> {
	
	/* Metodo que devuelve true si existe un siguiente
	 * elemento a la hora de iterar sobre una coleccion
	 * 
	 * @return true si existe un siguiente elemento, false de lo contrario
	 */
	public boolean hasNext();
	
	/* Devuelve el siguiente elemento en una iteracion
	 * 
	 * @return Elemento siguiente
	 * */
	public E next() throws NoSuchElementException;
}

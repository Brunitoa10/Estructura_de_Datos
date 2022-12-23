package Logica;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Excepciones.*;
import Interfaces.Position;
import Interfaces.PositionList;

/**
 * Implementacion encargada de dictar la menera de la cual se va a recorrer 
 * la estructura que se quiere iterar.
 * 
 * Itera sobre la estructura original recibida por parametro.
 * @author unix77
 *
 * @param <E>
 */
public class ElementIterator<E> implements Iterator<E> {

	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	/**
	 * En caso de que la lista no este vacia, hace que el cursor apunte al 
	 * primer nodo de la lista. 
	 * Lanza EmptyListException si la lista esta vacia
	 * @param list
	 */
	public ElementIterator(final PositionList<E> list) {
        try {
            this.lista = list;
            if (list.isEmpty()) {
                this.cursor = null;
            }
            else {
                this.cursor = list.first();
            }
        }catch (EmptyListException e) {
        	e.getStackTrace();
        }
    }
	
	@Override
	public boolean hasNext() {
		return cursor != null;
	}

	@Override
	public E next() throws NoSuchElementException {
		if(cursor == null) {
			throw new NoSuchElementException("You run out of elements.");
		}
		E toReturn = cursor.element();
		try {
			cursor = (cursor == lista.last()) ? null : lista.next(cursor);
		} catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}

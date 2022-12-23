package Logica;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import Excepciones.NoSuchElementException;

import Interfaces.Iterator;
import Interfaces.Position;
import Interfaces.PositionList;

/* Implementacion de un iterador operando de forma
 * directa sibre ka cikeccuib a iterar.
 */

public class IteradorListaSobreEdOriginal<E> implements Iterator<E>{
	//Atributos
	private PositionList<E> lista;
	private Position<E> cursor;
	
	public IteradorListaSobreEdOriginal(PositionList<E> l) {
		lista = l;
		try {
			if(lista != null && !lista.isEmpty()) {
				cursor = l.first();
			}else {
				cursor = null;
			}
		} catch (EmptyListException e) {
			cursor = null;
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
		E retorno = cursor.element();
		try {
			cursor = (cursor == lista.last()) ? null : lista.next(cursor);
		} catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
/* ¿Que sucede si una vez creado el iterador la lista se modifica?
 * 
 * El iterador solo es valido si la lista no es modificada
 */

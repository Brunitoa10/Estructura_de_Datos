package Interfaces;

import Excepciones.*;

public interface PositionList<E> {
	
	/* Retorna la cantidad de elementos de la lista
	 * @return Cantidad de elementos de la lista
	 */
	public int size();
	
	/* Retorna true si la lista esta vacia o false de lo contrario.
	 * @return true or false
	 */
	public boolean isEmpty();
	
	/* Retorna la posicion del primer elemento de la lista, produce error si la lista esta vacia
	 * @return Posicion del primer elemeneto de la lista
	 * @throws EmptyListException
	 */
	public Position<E> first() throws EmptyListException;
	
	/* Retorna la posicion del ultimo elemento de la lista, produce error si la lista esta vacia
	 * @return Posicion del ultimo elemeneto de la lista
	 * @throws EmptyListException
	 */
	public Position<E> last() throws EmptyListException;
	
	/* Retorna la posicion del elemento que precede al elemento de la posicion p, produce error si p = first()
	 * @return Previo a p
	 * @throws BoundaryViolationException o InvalidPositionException
	 */
	public Position<E> prev(Position<E> p) throws BoundaryViolationException,InvalidPositionException;

	/* Retorna la posicion del elemento que sigue al elemento de la posicion p, produce error si p = last()
	 * @return Siguiente a p
	 * @throws BoundaryViolationException o InvalidPositionException
	 */
	public Position<E> next(Position<E> p) throws BoundaryViolationException,InvalidPositionException;
	
	/* Reemplaza al elemento en la posicion p con e, retorna el elemento que estaba antes en la posicion p
	 * @return Elemento 'e' de la posicion 'p' antes de ser modificado
	 * @throws InvalidPositionException
	 */
	public E set(Position<E> p, E e) throws InvalidPositionException;

	/* Elimina y retorna el elemento en la posicion p, invalidando la posicion p
	 * @return Elemento 'e' de la posicion 'p'
	 * @throws InvalidPositionException
	 */
	public E remove(Position<E> p) throws InvalidPositionException;

	/* Inserta un nuevo elemento 'e' como primer elemento
	 */
	public void addFirst(E e);

	/* Inserta un nuevo elemento 'e' como ultimo elemento
	 */
	public void addLast(E e);
	
	/* Inserta un nuevo elemento 'e' antes de la posicion 'p'
	 * @throws InvalidPositionException
	 */
	public void addBefore(Position<E> p,E e) throws InvalidPositionException;

	/* Inserta un nuevo elemento 'e' luego de la posicion 'p'
	 * @throws InvalidPositionException
	 */
	public E addAfter(Position<E> p) throws InvalidPositionException;
}

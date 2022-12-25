package D_TDA_Lista;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyListException;
import A_Excepciones.InvalidPositionException;
import E_TDA_Iterable.Iterable;
import E_TDA_Iterable.IteradorListaSobreEdOriginal;
import E_TDA_Iterable.Iterator;

//Es mas eficiente que la listaSimplementeEnlazada

public class listaDoblementeEnlazada<E> implements PositionList<E>{
	//Atributos de instancia
	protected DNodo<E> head, tail;
	protected int size;
	
	//Constructor
	public listaDoblementeEnlazada() {
		head = new DNodo<E>();
		tail = new DNodo<E>();
		head.setSiguiente(tail);
		tail.setPrevio(head);
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
	public Position<E> first() throws EmptyListException {
		if(size == 0) {
			throw new EmptyListException("Error (first()) --> Lista vacia.");
		}
		return head.getSiguiente(); //Retorno el siguiente xq el primero es dummy(nodo centinela)
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(size == 0) {
			throw new EmptyListException("Error (last()) --> Lista vacia.");
		}
		return tail.getPrevio(); //Retorno el anterior xq el ultimo es dummy(nodo centinela)
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> retorno = checkPosition(p);
		if(retorno == tail.getPrevio()) {
			throw new BoundaryViolationException("Error (next(p)) --> No tiene siguiente.");
		}
		return retorno.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> retorno = checkPosition(p);
		if(retorno == head.getSiguiente()) {
			throw new BoundaryViolationException("Error (next(p)) --> No tiene siguiente.");
		}
		return retorno.getPrevio();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> insercion = new DNodo<E> (head,head.getSiguiente(),element);
		head.getSiguiente().setPrevio(insercion);
		head.setSiguiente(insercion);
		size++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> insercion = new DNodo<E> (tail.getPrevio(),tail,element);
		tail.getPrevio().setSiguiente(insercion);
		tail.setPrevio(insercion);
		size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo= new DNodo<E> (pos,pos.getSiguiente(),element);//previo, siguiente, elemento
		pos.getSiguiente().setPrevio(nuevo);
		pos.setSiguiente(nuevo);
		size++;
	}//T_addAfter(n) = O(1)

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E> (pos.getPrevio(), pos, element);
		pos.getPrevio().setSiguiente(nuevo);
		pos.setPrevio(nuevo);
		size++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> pos =checkPosition(p);
		E retorno = pos.element();
		pos.getPrevio().setSiguiente(pos.getSiguiente());
		pos.getSiguiente().setPrevio(pos.getPrevio());
		pos.setElemento(null);
		pos.setSiguiente(null);
		pos.setPrevio(null);
		size--;
		return retorno;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> pos = checkPosition(p);
		E retorno = pos.element();
		pos.setElemento(element);
		return retorno;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteradorListaSobreEdOriginal<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> retorno = new listaDoblementeEnlazada<Position<E>>();
		if(!isEmpty()) {
			Position<E> pos;
			try {
				for(pos = first(); pos != last(); pos = this.next(pos)) {
					retorno.addLast(pos);
				}
				retorno.addLast(pos);
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if(p == null) {
			throw new InvalidPositionException("The position is invalid");
		}
		if(p.element() == null) {
			throw new InvalidPositionException("The element has been previously removed");
		}
		if(size == 0) {
			throw new InvalidPositionException("The list is empty");
		}
		if(p == head || p == tail) {
			throw new InvalidPositionException("Header and Trailer arent valid positions");
		}
		try {
			DNodo<E> temp = (DNodo<E>) p;
			if (temp.getPrevio() == null || temp.getSiguiente() == null)
				throw new InvalidPositionException(
						"The position isnt from this list");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException(
					"La posicion es de un tipo incorrecto de la lista");
		}
	}
}

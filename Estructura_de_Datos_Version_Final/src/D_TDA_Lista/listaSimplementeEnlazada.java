package D_TDA_Lista;

import java.util.Iterator;
import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyListException;
import A_Excepciones.InvalidPositionException;
import E_TDA_Iterable.IteradorListaSobreEdOriginal;

public class listaSimplementeEnlazada<E> implements PositionList<E>{

	//Atributos de instancia
		protected NodoLista<E> head;
		protected int size;
		
		//Constructor
		public listaSimplementeEnlazada() {
			head = null;
			size = 0;
		}
		
		/**
		 * @return O(1)
		 */
		
		public int size() {
			return size;
		}
		
		/**
		 * @return O(1)
		 */
		public boolean isEmpty() {
			return head == null;
		}

		/**
		 * @return O(1)
		 */
		public Position<E> first() throws EmptyListException{
			if(size == 0 ) {
				throw new EmptyListException("Error (last()) --> Lista Vacia.");
			}
			return head;
		}
		
		/**
		 * @return O(n) 
		 */
		public Position<E> last() throws EmptyListException{
			if (size == 0) {
				throw new EmptyListException("Error (last()) --> Lista Vacia.");
			}	
			NodoLista<E> retorno = head;
			while (retorno.getSiguiente() != null) {
				retorno = retorno.getSiguiente();
			}				
			return retorno;
		}

		public void addFirst(E e) {
			head = new NodoLista<E>(e,head);
			size++;
		}
		
		public void addLast(E e) {//O(n)
			if (size == 0) {
				addFirst(e);
			}else {
				NodoLista<E> aux = head;
				while(aux.getSiguiente() != null) {
					aux = aux.getSiguiente();
				}
				aux.setSiguiente(new NodoLista<E>(e));
				size++;
			}
		}
		
		/**
		 * @return O(1)
		 */
		public void addAfter(Position<E> p,E e) throws InvalidPositionException{
			if(size == 0) {
				throw new InvalidPositionException("Error (addAfter(p,e)) --> Lista Vacia.");
			}
			NodoLista<E> n = checkPosition(p);
			NodoLista<E> nuevo = new NodoLista<E>(e,n.getSiguiente());
			n.setSiguiente(nuevo);
			size++;
		}
		
		/**
		 * @return O(1)
		 */
		public E set(Position<E> p, E e) throws InvalidPositionException{
			if(size == 0) {
				throw new InvalidPositionException("Error (set(p,e)) --> Posicion Invalida");
			}
			NodoLista<E> nodo = checkPosition(p); // Verifico si P es valido
			E retorno = p.element();
			nodo.setElemento(e);	
			return retorno;
		}
		
		/**
		 * @return O(1)
		 */
		public Position<E> next (Position<E> p) throws InvalidPositionException,BoundaryViolationException{
			NodoLista<E> n = checkPosition(p);
			
			if(n.getSiguiente() == null) {
				throw new BoundaryViolationException("Error (next(p)) --> No se puede pedir el siguiente al ultimo elemento.");
			}
			return n.getSiguiente();
		} 
		/**
		 * @return O(1)
		 */
		private NodoLista<E> checkPosition(Position<E> n) throws InvalidPositionException {
			try {
				if (n==null) {
					throw new InvalidPositionException("Error, posición nula");
				}
				if (n.element()==null) {
					throw new InvalidPositionException("El elemento fue eliminado previamente");
				}
				return (NodoLista<E>) n;
			}
			catch (InvalidPositionException e) {
				throw new InvalidPositionException("n no es un nodo de esta lista");
			}
		}
		
		public Position<E> prev (Position<E> p) throws InvalidPositionException, BoundaryViolationException{
			if(size == 0) {
				throw new InvalidPositionException("Error (prev(p)) --> Lista Vacia.");
			}
			checkPosition(p); //O(1)
			try {
				if(p == first()) {
					throw new BoundaryViolationException("Error (prev(p)) --> Posicion primera.");
				}
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //C1
			
			NodoLista<E> aux = head; //C2
			while(aux.getSiguiente() != p && aux.getSiguiente() != null) { //O(n) +c3
				aux = aux.getSiguiente(); //c4
			}
			
			if(aux.getSiguiente() == null) {//C5
				throw new InvalidPositionException("Error (prev(p)) --> La posicion 'p' no pertence a lista.");
			}
			
			return aux;
		}
		
		public void addBefore(Position<E> p, E e) throws InvalidPositionException{
			if(size == 0) {
				throw new InvalidPositionException("Error (prev(p)) --> Lista vacia.");
			}
			checkPosition(p);
	        if( p == head ) { 
	        	addFirst(e);
	        }else {
		        try {
					addAfter( prev(p), e );
				} catch (InvalidPositionException | BoundaryViolationException e1) {
					e1.printStackTrace();
				}
	        }
	        size++;
		}
		
		public E remove(Position<E> p) throws InvalidPositionException{
	        if(size == 0) {
	        	throw new InvalidPositionException("Error (prev(p)) --> Lista Vacia.");
	        }
	        NodoLista<E> n = checkPosition(p);
	        E aux = p.element();
	        if ( n ==  head ) {
	        	head = head.getSiguiente();
	        }else {
	        	NodoLista<E> it = head;
	        	while(it.getSiguiente() != n) {
	        		it = it.getSiguiente();
	        	}
	        	it.setSiguiente(n.getSiguiente());
			} 
	        n.setElemento(null);
	        n.setSiguiente(null);
	        size--;
	        return aux;
	    }

		//ver video de ana (Listo ya chequeado, todo ok)
		@Override
		public Iterator<E> iterator() {
			//Genero un iterador de la lista actual
			return (new IteradorListaSobreEdOriginal<E>(this));
		}

		@Override
		public Iterable<Position<E>> positions() {
			//Creo una lista que guarde las posiciones
			PositionList<Position<E>> p = new listaSimplementeEnlazada<Position<E>>();
			try {
				if(!isEmpty()) {
					Position<E> pos = first();
					while(pos != last()) {
						p.addLast(pos);
						pos = next(pos);
					}
					p.addLast(pos);
				}	
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
					e.printStackTrace();
			}
			return p;
		}		
}

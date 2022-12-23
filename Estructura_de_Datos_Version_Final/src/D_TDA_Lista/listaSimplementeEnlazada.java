package D_TDA_Lista;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyListException;
import A_Excepciones.InvalidPositionException;
import E_TDA_Iterable.Iterator;
import E_TDA_Iterable.Iterable;
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
		public Position<E> first(){
			return head;
		}
		
		/**
		 * @return O(n) 
		 */
		public Position<E> last() throws EmptyListException{
			NodoLista<E> retorno;
			if (isEmpty()) {
				throw new EmptyListException("Error (last()) --> Lista Vacia.");
			}else {
				NodoLista<E> aux = head;
				while (aux.getSiguiente() != null) {
					aux = aux.getSiguiente();
				}	
				retorno = aux;
			}
			return retorno;
		}

		public void addFirst(E e) {
			head = new NodoLista<E>(e,head);
			size++;
		}
		
		public void addLast(E e) {//O(n)
			if (isEmpty()) {
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
			NodoLista<E> n = checkPosition(p);
			NodoLista<E> nuevo = new NodoLista<E>(e);
			nuevo.setSiguiente(n.getSiguiente());
			n.setSiguiente(nuevo);
			size++;
		}
		
		/**
		 * @return O(1)
		 */
		public E set(Position<E> p, E e) throws InvalidPositionException{
			NodoLista<E> nodo = checkPosition(p); // Verifico si P es valido
			
			if(isEmpty()) {
				throw new InvalidPositionException("Error (set(p,e)) --> Posicion Invalida");
			}
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
		private NodoLista<E> checkPosition(Position<E> p) throws InvalidPositionException{
			NodoLista<E> toReturn;
			
			try{
				toReturn = (NodoLista<E>) p;
			}catch(ClassCastException e) {
				throw new InvalidPositionException("Error (checkPosition(p)) --> Posicion Invalida.");
			}
			return toReturn;
		}
		
		public Position<E> prev (Position<E> p) throws InvalidPositionException, BoundaryViolationException{
			checkPosition(p); //O(1)
			if(p == first()) {
				throw new BoundaryViolationException("Error (prev(p)) --> Posicion primera.");
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
			checkPosition(p);
	        if( p == first() ) { 
	        	addFirst(e);
	        }else {
		        try {
					addAfter( prev(p), e );
				} catch (InvalidPositionException | BoundaryViolationException e1) {
					e1.printStackTrace();
				}
	        }
		}
		
		public E remove(Position<E> p) throws InvalidPositionException{
			NodoLista<E> n = checkPosition(p);
	        if(isEmpty()) {
	        	throw new InvalidPositionException("Error (prev(p)) --> Lista Vacia.");
	        }
			if ( p == first() ) {
	        	head = n.getSiguiente();
	        }else {
				try {
					checkPosition(prev(p)).setSiguiente( n.getSiguiente() );
				} catch (BoundaryViolationException e) {
					e.printStackTrace();
				} 
			}
	        size--; 
	        E aux = p.element();
	        n.setElemento(null);
	        n.setSiguiente(null);
	        
	        return aux;
	    }

		//ver video de ana
		@Override
		public Iterator<E> iterator() {
			return (new IteradorListaSobreEdOriginal<E>(this));
		}

		@Override
		public Iterable<Position<E>> positions() {
			PositionList<Position<E>> col = new listaSimplementeEnlazada<Position<E>>();
			Position<E> p = null;
			
			try{
				if(!isEmpty()){
					p = first();
				}else{
					return col;
				}
				while(p != last()){
					col.addLast(p);
					p = next(p);
				}
				col.addLast(p);
			}catch(BoundaryViolationException | InvalidPositionException | EmptyListException e){
				e.printStackTrace();
			}
			return col;
		}	
}

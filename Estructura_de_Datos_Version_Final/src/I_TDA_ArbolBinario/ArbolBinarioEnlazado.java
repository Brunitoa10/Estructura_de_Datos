package I_TDA_ArbolBinario;

import java.util.Iterator;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyTreeException;
import A_Excepciones.InvalidOperationException;
import A_Excepciones.InvalidPositionException;
import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;

public class ArbolBinarioEnlazado<E> implements BinaryTree<E> {
/*
	@Override
	

}*/	

	
	protected BTPosition<E> raiz;
	protected int size;
	
	public ArbolBinarioEnlazado(){
		size = 0;
		raiz = null;
	}
	
	public ArbolBinarioEnlazado(E rot){
		size = 1;
		raiz = new BTNode<E>(rot, null);
	}
	
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public Iterator<E> iterator() {
		Iterable<Position<E>> positions=positions();
		PositionList<E> l=new listaDoblementeEnlazada<E>();
		for (Position<E> p:positions) {
			l.addLast(p.element());
		}
		return l.iterator();
	}
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new listaDoblementeEnlazada<Position<E>>();
		if (size != 0) {
			pre(raiz, lista);
		}
		return lista;
	}
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		if(size == 0) {
			throw new InvalidPositionException("Error (replace(v,e)) --> Arbol vacio.");
		}
		BTNode<E> nodo = checkPosition(v);
		E retorno = nodo.element();
		nodo.setElement(e);
		return retorno;
	}
	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size == 0) {
			throw new EmptyTreeException("Error (root()) --> Arbol vacio");
		}
		return raiz;
	}
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> nodo = checkPosition(v);
		if(nodo == raiz) {
			throw new BoundaryViolationException("Error (parent(v)) --> Le esta pidiendo el padre al nodo raiz.");
		}
		return nodo.getParent();
	}
	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		PositionList<Position<E>> hijos=new listaDoblementeEnlazada<Position<E>>();
		try {
			if (hasLeft(v)) {
				hijos.addLast(left(v));
			}
			if (hasRight(v)) {
				hijos.addLast(right(v));
			}
		}
		catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
		return hijos;
	}
	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return hasLeft(v) || hasRight(v);
	}
	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !hasLeft(v) && !hasRight(v);
	}
	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTNode<E> nodo = checkPosition(v);
		return nodo.getParent() == null;
	}
	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> nodo = checkPosition(v);
		if(nodo.getLeft() == null) {
			throw new BoundaryViolationException("Error (left(v)) --> No tiene hijo derecho.");
		}
		return nodo.getLeft();
	}
	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNode<E> nodo = checkPosition(v);
		if(nodo.getRight() == null) {
			throw new BoundaryViolationException("Error (left(v)) --> No tiene hijo derecho.");
		}
		return nodo.getRight();
	}
	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		return nodo.getLeft()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPosition<E> nodo = checkPosition(v);
		return nodo.getRight() != null;
	}
	
	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(size != 0) {
			throw new InvalidOperationException("Error (creatRoot(r)) --> El arbol ya tiene raiz.");
		}
		BTNode<E> padre = new BTNode<E> (r);
		raiz = padre;
		size++;
		return padre;
	}
	
	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if(size == 0) {
			throw new InvalidPositionException("Error (addLeft(v,r)) --> Arbol vacio.");
		}
		BTNode<E> nodo = checkPosition(v);
		if(this.hasLeft(nodo)) {
			throw new InvalidOperationException("Error (addLeft(v,r)) --> El arbol ya tiene hijo izquierdo.");
		}
		BTNode<E> nuevoNodo = new BTNode<E> (r,nodo);  
		nodo.setLeft(nuevoNodo);
		size++;
		return nuevoNodo;
	}
	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if(size == 0) {
			throw new InvalidPositionException("Error (addRight(v,r)) --> Error arbol vacio.");
		}
		BTNode<E> nodo = checkPosition(v);
		if(this.hasRight(nodo)) {
			throw new InvalidOperationException("Error (addRight(v,r)) --> El arbol ya tiene hijo derecho");
		}
		BTNode<E> nuevoNodo = new BTNode<E> (r,nodo);  
		nodo.setRight(nuevoNodo);
		size++;
		return nuevoNodo;
	}
	
	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		if (size == 0) { 
			throw new InvalidPositionException("Error (remove(v)) --> Arbol vacio.");
		}
		BTNode<E> nodo = checkPosition(v);
		if (this.hasLeft(nodo) && this.hasRight(nodo)) { 
			throw new InvalidOperationException("Error (remove(v)) --> No es posible realizar esta operacion, ya que tiene 2 hijos");
		}
		BTPosition<E> padre = nodo.getParent();
		E retorno = nodo.element();
		if (nodo.equals(raiz)) {
			if (this.hasLeft(nodo)) { 
				raiz = (BTNode<E>) nodo.getLeft();
			}else { 
            	if(this.hasRight(nodo)) { 
            		raiz = (BTNode<E>) nodo.getRight();
            	}else { 
					raiz = null;
            	}
			}
		}else {
			if (this.hasLeft(v)) {
				if (padre.getLeft().equals(nodo)) { 
					padre.setLeft(nodo.getLeft());
				}else { 
					padre.setRight(nodo.getLeft());
				}
			}else { 
				if (this.hasRight(nodo)){
					if (padre.getLeft().equals(nodo)) { 
						padre.setLeft(nodo.getRight());
					}else { 
						padre.setRight(nodo.getRight());
					}
				}else{
					if (padre.getLeft() == nodo) { 
						padre.setLeft(null);
					}else { 
						padre.setRight(null);
					}
				}
			}
		}	  
		size--;
		return retorno;
	}
	
	@Override
	public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2) throws InvalidPositionException {
		BTPosition<E> raiz_local = checkPosition(p), hi_raiz_local, hd_raiz_local;
		Position<E> raiz_t1, raiz_t2;
		if (raiz_local.getLeft() != null || raiz_local.getRight() != null) {
			throw new InvalidPositionException("Error (attach(p,t1,t2)) --> La posicion no corresponde a un nodo hoja");
		}
		try {
			//Clonación de T1 como subárbol izquierdo
			if (!t1.isEmpty()) {
				raiz_t1 = t1.root();
				hi_raiz_local = new BTNode<E>(raiz_t1.element(), (BTNode<E>) raiz_local);
				raiz_local.setLeft(hi_raiz_local);
				clonar(hi_raiz_local, raiz_t1, t1);
			}
			//Clonación de T2 como subárbol derecho
			if (!t2.isEmpty()) {
				raiz_t2 = t2.root();
				hd_raiz_local = new BTNode<E>(raiz_t2.element(), (BTNode<E>) raiz_local);
				raiz_local.setRight(hd_raiz_local);
				clonar(hd_raiz_local, raiz_t2, t2);
			}
			size += t1.size() + t2.size();
		}catch(EmptyTreeException e) {
			raiz_local.setLeft(null); 
			raiz_local.setRight(null); 
		}
	}
	
	private void clonar(BTPosition<E> padre_local, Position<E> padre_t, BinaryTree<E> t) {
		BTPosition<E> hi_padre_local, hd_padre_local;
		Position<E> hi_padre_t, hd_padre_t;
		try {
			//Si existe hijo izquierdo en T de padre_t, se clona este y el subárbol a partir del hijo izquierdo de padre_t.
			if (t.hasLeft(padre_t)) {
				hi_padre_t = t.left(padre_t);
				hi_padre_local = new BTNode<E>(hi_padre_t.element(), (BTNode<E>) padre_local);
				padre_local.setLeft(hi_padre_local);
				clonar(hi_padre_local, hi_padre_t, t);
			}
			//Si existe hijo derecho en T de padre_t, se clona este y el subárbol a partir del hijo derecho de padre_t.
			if (t.hasRight(padre_t)) {
				hd_padre_t = t.right(padre_t);
				hd_padre_local = new BTNode<E>(hd_padre_t.element(), (BTNode<E>) padre_local);
				padre_local.setRight(hd_padre_local);
				clonar(hd_padre_local, hd_padre_t, t);
			}
		}catch(InvalidPositionException | BoundaryViolationException e) {
			padre_local.setLeft(null); padre_local.setRight(null);
		}
	}
	
	private void pre(BTPosition<E> v, PositionList<Position<E>> l) {
		try {
			l.addLast(v);
			if (hasLeft(v)) {
				pre(v.getLeft(), l);
			}
			if (hasRight(v)) {
				pre(v.getRight(), l);
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Devuelve un nodo de árbol creado a partir de la posición n.
	 * @param n Posición a partir de la cual se crea el nodo de árbol.
	 * @return Nodo de árbol creado a partir de la posición n.
	 * @throws InvalidPositionException si la posición es nula, si fue eliminada anteriormente o si no es de este árbol.
	 */
	private BTNode<E> checkPosition(Position<E> n) throws InvalidPositionException {
		try {
			if (n==null) {
				throw new InvalidPositionException("Error (checkPosition(v)) --> posición nula.");
			}
			if (n.element()==null) {
				throw new InvalidPositionException("Error (checkPosition(v)) --> El elemento fue eliminado previamente.");
			}
			if (size==0) {
				throw new InvalidPositionException("Error (checkPosition(v)) --> El árbol está vacío.");
			}
			return (BTNode<E>) n;
		}
		catch (ClassCastException e) {
			throw new InvalidPositionException("n no es un nodo de este árbol.");
		}
	}
}

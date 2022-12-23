package D_TDA_Lista;

public class NodoLista<E> implements Position<E> {
	
	private NodoLista<E> siguiente;
	private E elemento;
	
	
	public NodoLista(E item, NodoLista<E> sig) {
		this.elemento= item;
		this.siguiente = sig;
	}
	
	public NodoLista(E item) {
		this(item,null);
	}
	
	//SETTERS
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	public void setSiguiente(NodoLista<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	//GETTERS
	public E getElemento() {
		return this.elemento;
	}
	
	public NodoLista<E> getSiguiente(){
		return this.siguiente;
	}
	
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return this.elemento;
	}
}

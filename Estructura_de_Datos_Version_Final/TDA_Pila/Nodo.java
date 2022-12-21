package TDAPila;

public class Nodo <E>
{
	private E elemento;
	private Nodo <E> siguiente;
	
	public Nodo(E elem, Nodo<E> sig)//(1) 
	{
		elemento = elem;
		siguiente = sig;
	}
	
	//Invoca el constructor (1)
	public Nodo(E elem) {this(elem,null);}

	//Getters
	public E getElem() {return elemento;}

	public Nodo<E> getSig() {return siguiente;}

	//Seters
	public void setSig(Nodo<E> siguiente) {this.siguiente = siguiente;}
	
	public void setElem(E elemento) {this.elemento = elemento;}
}

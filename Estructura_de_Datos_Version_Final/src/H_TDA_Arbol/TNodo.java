package H_TDA_Arbol;

import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;

public class TNodo<E> implements Position<E> {
	
	//Atributos de instancia
	private E elemento;
	private TNodo<E> padre;
	private PositionList<TNodo<E>> hijos;
	
	//Constructor
	public TNodo(E elemento, TNodo<E> padre) {
		this.elemento = elemento;
		this.padre = padre;
		hijos = new listaDoblementeEnlazada<TNodo<E>>();
	}
	
	public TNodo(E elemento) {
		this(elemento,null);
	}
	
	@Override
	public E element() {
		return this.elemento;
	}

	public void setElemento(E elemento) {
		this.elemento = elemento;
	}

	public TNodo<E> getPadre() {
		return padre;
	}

	public void setPadre(TNodo<E> padre) {
		this.padre = padre;
	}

	public PositionList<TNodo<E>> getHijos() {
		return hijos;
	}

	public void setHijos(PositionList<TNodo<E>> hijos) {
		this.hijos = hijos;
	}
}

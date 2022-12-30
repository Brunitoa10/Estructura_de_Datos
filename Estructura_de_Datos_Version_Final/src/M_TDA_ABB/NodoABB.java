package M_TDA_ABB;

public class NodoABB<E extends Comparable<E>> {
	//Atributos de instancia
	private NodoABB<E> padre,izq,der;
	private E rotulo;
	
	//Constructor
	public NodoABB(E rotulo,NodoABB<E> padre) {
		this.padre = padre;
		this.rotulo = rotulo;
		this.der = this.izq = null;
	}
	
	public NodoABB(E rotulo) {
		this(rotulo,null);
	}

	public NodoABB<E> getPadre() {
		return padre;
	}

	public void setPadre(NodoABB<E> padre) {
		this.padre = padre;
	}

	public NodoABB<E> getIzq() {
		return izq;
	}

	public void setIzq(NodoABB<E> izq) {
		this.izq = izq;
	}

	public NodoABB<E> getDer() {
		return der;
	}

	public void setDer(NodoABB<E> der) {
		this.der = der;
	}

	public E getRotulo() {
		return rotulo;
	}

	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}
}

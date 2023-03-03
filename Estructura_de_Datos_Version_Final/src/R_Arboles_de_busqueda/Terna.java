package R_Arboles_de_busqueda;

public class Terna<V, E> {
	//Atributos
	protected V nodoIzq,nodoDer;
	protected E clave;
	
	//Constructor
	public Terna(V nodoIzq, E clave, V nodoDer) {
		this.nodoIzq = nodoIzq;
		this.clave = clave;
		this.nodoDer = nodoDer;
	}

	public V getNodoIzq() {
		return nodoIzq;
	}

	public void setNodoIzq(V nodoIzq) {
		this.nodoIzq = nodoIzq;
	}

	public V getNodoDer() {
		return nodoDer;
	}

	public void setNodoDer(V nodoDer) {
		this.nodoDer = nodoDer;
	}

	public E getClave() {
		return clave;
	}

	public void setClave(E clave) {
		this.clave = clave;
	}
}

package R_Arboles_de_busqueda;

public class NodoAVL<E> {
	//Atributos
	private NodoAVL<E> padre;
	private E rotulo;
	private int altura;
	private boolean eliminado;
	private NodoAVL<E> izq,der;
	
	//Constructor
	public NodoAVL(E rotulo, NodoAVL<E> padre){
		altura = 0;
		this.rotulo = rotulo;
		eliminado = false;
		this.padre = padre;
		izq = der = null;
	}
	
	public NodoAVL(E rotulo){
		this(rotulo,null);
	}

	public NodoAVL<E> getPadre() {
		return padre;
	}

	public void setPadre(NodoAVL<E> padre) {
		this.padre = padre;
	}

	public E getRotulo() {
		return rotulo;
	}

	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public NodoAVL<E> getIzq() {
		return izq;
	}

	public void setIzq(NodoAVL<E> izq) {
		this.izq = izq;
	}

	public NodoAVL<E> getDer() {
		return der;
	}

	public void setDer(NodoAVL<E> der) {
		this.der = der;
	}
}

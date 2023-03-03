package R_Arboles_de_busqueda;

public class Nodo_2_3<E> {
	//Atributos
	private Nodo_2_3 <E> hijoDer,hijoIzq,hijoMed,padre;
	private E claveA,claveB;
	
	
	public Nodo_2_3() {
		hijoDer = hijoIzq = hijoMed = padre = null;
		claveA = claveB = null;
	}
	
	
	public Nodo_2_3<E> getHijoDer() {
		return hijoDer;
	}
	public void setHijoDer(Nodo_2_3<E> hijoDer) {
		this.hijoDer = hijoDer;
	}
	public Nodo_2_3<E> getHijoIzq() {
		return hijoIzq;
	}
	public void setHijoIzq(Nodo_2_3<E> hijoIzq) {
		this.hijoIzq = hijoIzq;
	}
	public Nodo_2_3<E> getHijoMed() {
		return hijoMed;
	}
	public void setHijoMed(Nodo_2_3<E> hijoMed) {
		this.hijoMed = hijoMed;
	}
	public Nodo_2_3<E> getPadre() {
		return padre;
	}
	public void setPadre(Nodo_2_3<E> padre) {
		this.padre = padre;
	}
	public E getClaveA() {
		return claveA;
	}
	public void setClaveA(E claveA) {
		this.claveA = claveA;
	}
	public E getClaveB() {
		return claveB;
	}
	public void setClaveB(E claveB) {
		this.claveB = claveB;
	}
	public boolean estaClave(E clave) {
		boolean esta = false;
		if(claveA.equals(clave) || claveB.equals(clave)) {
			esta = true;
		}
		return esta;
	}
	public int cantClave() {
		int cant = 0;
		cant += (claveA != null) ? 1 : 0;
		cant += (claveB != null) ? 1 : 0;
		return cant;
	}
}

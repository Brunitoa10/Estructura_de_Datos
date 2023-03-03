package R_Arboles_de_busqueda;

import java.util.Comparator;

public class arbol_2_3<E extends Comparable<E>> {
	//Atributos
	protected Nodo_2_3<E> raiz;
	protected Comparator<E> comp;
	
	public arbol_2_3(Comparator<E> comp) {
		raiz = new Nodo_2_3<E>();
		this.comp = comp;
	}
	
	public Nodo_2_3<E> getRaiz(){
		return raiz;
	}
	
}

package P_TDA_Grafo_Dirigido;

import D_TDA_Lista.Position;
import E_TDA_Iterable.Iterator;

public class ArcoD<V,E> implements EdgeD<E> {
	
	protected E rotulo;
	protected VerticeD<V,E> cola, punta;
	//tiene su posicion en la lista de arcos del grafo, y 2 posiciones mas
	//que indican la posicion del arco en cada una de las listas de arcos, 
	//de los vertices que une ese arco
	protected Position<ArcoD<V,E>> posEnListaVerticeA;
	protected Position<ArcoD<V,E>> posEnListaVerticeB;
	protected Position<ArcoD<V,E>> posEnListaArcosGrafo;
		
	public ArcoD(E rotulo,VerticeD<V,E> cola, VerticeD<V,E> punta ) {
		this.rotulo = rotulo;
		this.cola = cola;
		this.punta = punta;
	}
		
	//setters
	public void setRotulo(E e) {
		rotulo = e;
	}
	
	public void setcola(VerticeD<V,E> v) {
		cola = v;
	}
		
	public void setpunta(VerticeD<V,E> v) {
		punta = v;
	}
	
	public void setposEnListaVerticeA(Position<ArcoD<V,E>> p) {
		posEnListaVerticeA = p;
	}
		
	public void setposEnListaVerticeB(Position<ArcoD<V,E>> p) {
		posEnListaVerticeB = p;
	}
		
	public void setPosEnListaArcosGrafo(Position<ArcoD<V,E>> p) {
		posEnListaArcosGrafo = p;
	}
		
	//getters
	/**
	 * Obtiene la posicion del arco, de la lista de posiciones 
	 * del vertice que tiene la lista de arcos que estan en 
	 * contacto con el
	 * 
	 */
	public Position<ArcoD<V,E>> getPoscola(){
		return posEnListaVerticeA;
	}
		
	public Position<ArcoD<V,E>> getPospunta(){
		return posEnListaVerticeB;
	}
		
	public Position<ArcoD<V,E>> getPosEnListaArcosGrafo(){
		return posEnListaArcosGrafo;
	}
		
		
	public VerticeD<V,E> getcola(){
		return cola;
	}
		
	public VerticeD<V,E> getpunta(){
		return punta;
	}
		
	public E element() {
		return rotulo;
	}
}

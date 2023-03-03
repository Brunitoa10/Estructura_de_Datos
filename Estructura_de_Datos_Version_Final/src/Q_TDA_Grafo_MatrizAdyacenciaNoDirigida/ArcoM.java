package Q_TDA_Grafo_MatrizAdyacenciaNoDirigida;

import D_TDA_Lista.Position;
import O_TDA_Grafo_No_Dirigido.Edge;

public class ArcoM<V, E> implements Edge<E> {
	
	//Atributos
	private Position<Edge<E>> posicionEnArcos;
	private VerticeM<V> vertA,vertB;
	private E rotulo;
	
	//Constructor
	public ArcoM(E rotulo, VerticeM<V> vertA, VerticeM<V> vertB) {
		this.rotulo = rotulo;
		this.vertA = vertA;
		this.vertB = vertB;
	}
	
	public Position<Edge<E>> getPosicionEnArcos() {
		return posicionEnArcos;
	}

	public void setPosicionEnArcos(Position<Edge<E>> posicionEnArcos) {
		this.posicionEnArcos = posicionEnArcos;
	}

	public VerticeM<V> getVertA() {
		return vertA;
	}

	public void setVertA(VerticeM<V> vertA) {
		this.vertA = vertA;
	}

	public VerticeM<V> getVertB() {
		return vertB;
	}

	public void setVertB(VerticeM<V> vertB) {
		this.vertB = vertB;
	}

	public E getRotulo() {
		return rotulo;
	}

	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}

	@Override
	public E element() {
		return rotulo;
	}

}

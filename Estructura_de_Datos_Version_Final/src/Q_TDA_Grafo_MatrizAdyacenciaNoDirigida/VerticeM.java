package Q_TDA_Grafo_MatrizAdyacenciaNoDirigida;

import D_TDA_Lista.Position;
import O_TDA_Grafo_No_Dirigido.Vertex;

public class VerticeM<V> implements Vertex<V> {
	
	//Atributos
	private Position<Vertex<V>> posicionEnVertices;
	private V rotulo;
	private int indice;
	
	//Constructor
	public VerticeM(V rotulo, int indice) {
		this.rotulo = rotulo;
		this.indice = indice;
		posicionEnVertices = null;
	}
		
	public Position<Vertex<V>> getPosicionEnVertices() {
		return posicionEnVertices;
	}

	public void setPosicionEnVertices(Position<Vertex<V>> posicionEnVertices) {
		this.posicionEnVertices = posicionEnVertices;
	}

	public V getRotulo() {
		return rotulo;
	}

	public void setRotulo(V rotulo) {
		this.rotulo = rotulo;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	@Override
	public V element() {
		return rotulo;
	}

}

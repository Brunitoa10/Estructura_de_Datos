package O_TDA_Grafo;

import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;

public class Vertice<V, E> implements Vertex<V> {
	
	//Atributos de instancia
	private V rotulo;
	private Position<Vertice<V,E>> posicionEnListaVertices;
	private PositionList<Arco<V,E>> adyacentes;
	
	//Constructor
	public Vertice(V rotulo) {
		this.rotulo = rotulo;
		adyacentes = new listaDoblementeEnlazada<Arco<V,E>>();
	}
	@Override
	public V element() {
		return rotulo;
	}

	public V getRotulo() {
		return rotulo;
	}

	public void setRotulo(V rotulo) {
		this.rotulo = rotulo;
	}

	public Position<Vertice<V, E>> getPosicionEnListaVertices() {
		return posicionEnListaVertices;
	}

	public void setPosicionEnListaVertices(Position<Vertice<V, E>> posicionEnListaVertices) {
		this.posicionEnListaVertices = posicionEnListaVertices;
	}

	public PositionList<Arco<V, E>> getAdyacentes() {
		return adyacentes;
	}

	public void setAdyacentes(PositionList<Arco<V, E>> adyacentes) {
		this.adyacentes = adyacentes;
	}

}

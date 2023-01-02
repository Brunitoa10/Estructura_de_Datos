package O_TDA_Grafo;

import D_TDA_Lista.Position;

public class Arco<V, E> implements Edge<E> {

	//Atributos de instancia
	private E rotulo;
	private Position<Arco<V,E>> posicionEnArcos;
	private Vertice<V,E> v1,v2;
	private Position<Arco<V,E>> posicionEnListaV1,posicionEnListaV2;
	
	//Constructor
	public Arco(Vertice<V,E> v1, Vertice<V,E> v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Arco(E rotulo,Vertice<V,E> v1, Vertice<V,E> v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.rotulo = rotulo; 
	}
	@Override
	public E element() {
		return rotulo;
	}

	public E getRotulo() {
		return rotulo;
	}

	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}

	public Position<Arco<V, E>> getPosicionEnArcos() {
		return posicionEnArcos;
	}

	public void setPosicionEnArcos(Position<Arco<V, E>> posicionEnArcos) {
		this.posicionEnArcos = posicionEnArcos;
	}

	public Vertice<V, E> getV1() {
		return v1;
	}

	public void setV1(Vertice<V, E> v1) {
		this.v1 = v1;
	}

	public Vertice<V, E> getV2() {
		return v2;
	}

	public void setV2(Vertice<V, E> v2) {
		this.v2 = v2;
	}

	public Position<Arco<V, E>> getPosicionEnListaV1() {
		return posicionEnListaV1;
	}

	public void setPosicionEnListaV1(Position<Arco<V, E>> posicionEnListaV1) {
		this.posicionEnListaV1 = posicionEnListaV1;
	}

	public Position<Arco<V, E>> getPosicionEnListaV2() {
		return posicionEnListaV2;
	}

	public void setPosicionEnListaV2(Position<Arco<V, E>> posicionEnListaV2) {
		this.posicionEnListaV2 = posicionEnListaV2;
	}

}

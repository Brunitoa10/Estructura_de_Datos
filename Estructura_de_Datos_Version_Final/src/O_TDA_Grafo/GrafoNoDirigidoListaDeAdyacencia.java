package O_TDA_Grafo;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyListException;
import A_Excepciones.InvalidEdgeException;
import A_Excepciones.InvalidPositionException;
import A_Excepciones.InvalidVertexException;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;

/*	El grafo conoce una lista de vertices y una lista de arcos y 
 * 	cada vertice conoce su rotulo y los arcos que inciden en el.
 *	Los arcos conocen los vertices que unen y su peso.
 */

public class GrafoNoDirigidoListaDeAdyacencia<V, E> implements GraphNoDirigido<V, E> {
	
	//Atributos de instancia
	protected PositionList<Vertice<V,E>> nodos;
	protected PositionList<Arco<V,E>> arcos;
	
	//Constructor
	public GrafoNoDirigidoListaDeAdyacencia() {
		nodos = new listaDoblementeEnlazada<Vertice<V,E>>();
		arcos = new listaDoblementeEnlazada<Arco<V,E>>();
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> list = new listaDoblementeEnlazada<Vertex<V>>();
		for(Vertex<V> v : nodos) {
			list.addLast(v);
		}
		return list;
	}//O(n)

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> list = new listaDoblementeEnlazada<Edge<E>>();
		for(Edge<E> e : arcos) {
			list.addLast(e);
		}
		return list;
	}//O(n)

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		if(v == null) {
			throw new InvalidVertexException("Error (incidentEdges(v)) --> Vertice null.");
		}
		PositionList<Edge<E>> list = new listaDoblementeEnlazada<Edge<E>>();
		Vertice<V,E> vert = (Vertice<V,E>) v;
		for(Edge<E> e : vert.getAdyacentes()) {
			list.addLast(e);
		}
		return list;
	}//O(deg(v))

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		
		return null;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		
		return null;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		
		return false;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		
		return null;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		try {
			nodos.addLast(v);
			v.setPosicionEnListaVertices(nodos.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return v;
	}//O(1)

	@Override
	
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		if(v == null || w == null) {
			throw new InvalidVertexException("Error (incidentEdges(v)) --> Vertice null.");
		}
		Arco<V,E> nuevoArco = null;
		Vertice<V,E> verticeV = null; 
		Vertice<V,E> verticeW = null;
		try {
			//Obtengo los vertices V y W
			verticeV = (Vertice<V, E>) v;
			verticeW = (Vertice<V, E>) w;
			//Construyo un arco
			nuevoArco = new Arco<V,E>(e,verticeV,verticeW);
			//Agrego el arco al final de la lista de adyacentes de V y anoto su posicion
			verticeV.getAdyacentes().addLast(nuevoArco);
			nuevoArco.setPosicionEnListaV1(verticeV.getAdyacentes().last());
			//Agrego el arco al final de la lista de adyacentes de W y anoto su posicion
			verticeW.getAdyacentes().addLast(nuevoArco);
			nuevoArco.setPosicionEnListaV2(verticeW.getAdyacentes().last());
			//Agrego el arco al final de la lista de ARCOS y anoto su posicion
			arcos.addLast(nuevoArco);
			nuevoArco.setPosicionEnArcos(arcos.last());
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		return nuevoArco;
	}//O(1)

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		
		return null;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		
		return null;
	}

}

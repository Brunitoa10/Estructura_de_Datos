package Q_TDA_Grafo_MatrizAdyacencia;

import A_Excepciones.InvalidEdgeException;
import A_Excepciones.InvalidVertexException;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;
import O_TDA_Grafo_No_Dirigido.Arco;
import O_TDA_Grafo_No_Dirigido.Edge;
import O_TDA_Grafo_No_Dirigido.Vertex;


public class GrafoMatrizAdyacencia<V,E> implements Graph<V,E> {

	//Atributos
	protected PositionList<Vertex<V>> vertices; 
	protected PositionList<Edge<E>> arcos;
	protected Edge<E> [][] matriz;
	protected int cantVertices;
	
	//Constructor
	public GrafoMatrizAdyacencia(int n) {
		vertices = new listaDoblementeEnlazada<Vertex<V>>();
		arcos = new listaDoblementeEnlazada<Edge<E>>();
		matriz = (Edge<E>[][]) new Arco[n][n]; //Armo una matriz cuadrada
		cantVertices = 0;
	}
	
	public GrafoMatrizAdyacencia() {
		this(100);
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> list = new listaDoblementeEnlazada<Vertex<V>>();
		for(Vertex<V> v : vertices) {
			list.addLast(v);
		}
		return list;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> list = new listaDoblementeEnlazada<Edge<E>>();
        for (Edge<E> e : arcos){
            list.addLast(e);
        }
        return list;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		// TODO Auto-generated method stub
		return null;
	}

}

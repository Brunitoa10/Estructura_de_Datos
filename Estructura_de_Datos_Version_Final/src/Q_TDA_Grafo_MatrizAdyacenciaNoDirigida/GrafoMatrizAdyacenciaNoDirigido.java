package Q_TDA_Grafo_MatrizAdyacenciaNoDirigida;

import A_Excepciones.EmptyListException;
import A_Excepciones.InvalidEdgeException;
import A_Excepciones.InvalidPositionException;
import A_Excepciones.InvalidVertexException;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;
import O_TDA_Grafo_No_Dirigido.Edge;
import O_TDA_Grafo_No_Dirigido.Vertex;



public class GrafoMatrizAdyacenciaNoDirigido<V,E> implements Graph<V,E> {

	//Atributos
	protected PositionList<Vertex<V>> vertices; 
	protected PositionList<Edge<E>> arcos;
	protected Edge<E> [][] matriz;
	protected int cantVertices;
	
	//Constructor
	public GrafoMatrizAdyacenciaNoDirigido(int n) {
		vertices = new listaDoblementeEnlazada<Vertex<V>>();
		arcos = new listaDoblementeEnlazada<Edge<E>>();
		matriz = (Edge<E>[][]) new ArcoM[n][n]; //Armo una matriz cuadrada
		cantVertices = 0;
	}
	
	public GrafoMatrizAdyacenciaNoDirigido() {
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
		if(v == null) {
			throw new InvalidVertexException("Error incidentEdges(v) --> Vertice invalido.");
		}
		VerticeM<V> vertA = (VerticeM<V>) v;
		int fila = vertA.getIndice();
		PositionList<Edge<E>> lista = new listaDoblementeEnlazada<Edge<E>>();
		//
		for(int col = 0; col < cantVertices; col++) {
			if(matriz[fila][col] != null) {
				lista.addLast(matriz[fila][col]);
			}
		}
		return lista;
	}//O(n)

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		if(v == null) {
			throw new InvalidVertexException("Error opposite(v,e) --> Vertice invalido.");
		}
		if(e == null) {
			throw new InvalidEdgeException("Error opposite(v,e) --> Arco Invalido.");
		}
		VerticeM<V> vertA = (VerticeM<V>) v;
		VerticeM<V> retorno = null;
		ArcoM<V,E> arco = (ArcoM<V,E>) e;

		if(arco.getVertA() == vertA) {
			retorno  =  arco.getVertB();
		}else {
			if(arco.getVertB() == vertA) {
				retorno = arco.getVertA();
			}else {
				throw new InvalidEdgeException("Error opposite(v,e) --> Vertice y Arco no relacionados");
			}
		}
		return retorno;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		if(e == null) {
			throw new InvalidEdgeException("Error endVertice(e) --> Arco invalido");
		}
		ArcoM<V,E> arco = (ArcoM<V,E>) e;
		Vertex<V> [] vertA = (Vertex<V>[]) new VerticeM[2];
		vertA[0] = arco.getVertA();
		vertA[1] = arco.getVertB();
		return vertA;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if(v == null || w == null) {
			throw new InvalidVertexException("Error areAdjacent(v,e) --> Vertice A o B invalido");
		}
		VerticeM<V> vertA = (VerticeM<V>) v;
		VerticeM<V> vertB = (VerticeM<V>) w;
		return matriz[vertA.getIndice()][vertB.getIndice()] != null;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if(v == null) {
			throw new InvalidVertexException("Error replace(v,e) --> Vertice Invalido.");
		}
		VerticeM<V> vertA = (VerticeM<V>) v;
		V retorno = vertA.getRotulo();
		vertA.setRotulo(x);
		return retorno;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		VerticeM<V> nuevoVertice = null;
		try {
			nuevoVertice = new VerticeM<V>(x,cantVertices++);
			vertices.addLast(nuevoVertice);
			nuevoVertice.setPosicionEnVertices(vertices.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return nuevoVertice;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		if(v == null || w == null) {
			throw new InvalidVertexException("insertEdge(v,w,e) --> Error vertice V o W invalido.");
		}
		
		//Cargo arco en la matriz
		VerticeM<V> vertA = (VerticeM<V>) v;
		VerticeM<V> vertB = (VerticeM<V>) w;
		int fila = 0;
		int col = 0;
		ArcoM<V,E> arco = null;
		try {
			if(vertA.getIndice() >= matriz.length || vertB.getIndice() >= matriz[0].length || cantVertices >= matriz.length) {
				System.out.println("Entre "+vertA.getIndice()+" "+vertB.getIndice()+" "+cantVertices+" "+matriz.length+" "+matriz[0].length);
				expandirMatriz();
				System.out.println("Entreee "+vertA.getIndice()+" "+vertB.getIndice()+" "+cantVertices+" "+matriz.length+" "+matriz[0].length);
			}
			fila = vertA.getIndice();
			col = vertB.getIndice();
			System.out.println("NO fila "+fila+" col "+col+" cantVertices "+cantVertices);
			System.out.println("NO col "+col+" fila "+fila+" cantVertices "+cantVertices);
			
			arco = new ArcoM<V,E>(e,vertA,vertB);
			//Como es una matriz no dirigida es simetrica
			matriz[fila][col] = matriz[col][fila] = arco;
			arcos.addLast(arco);
			arco.setPosicionEnArcos(arcos.last());
			
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
		return arco;
	}
	
	private void expandirMatriz() {
		Edge<E>[][] matriz_anterior = matriz.clone();
		matriz = new ArcoM[matriz_anterior.length * 2][matriz_anterior.length * 2];

		for (int fila = 0; fila < matriz_anterior.length; fila++) {
			for (int col = 0; col < matriz_anterior[0].length; col++)
				if (matriz_anterior[fila][col] != null)
					matriz[fila][col] = matriz_anterior[fila][col];
		}
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if(v == null) {
			throw new InvalidVertexException("Error remove(v) --> Vertice invalido.");
		}
		VerticeM<V> vertA = (VerticeM<V>) v;
		V retorno = null;
		int fila = 0;
		try {
			retorno = vertA.getRotulo();
			vertices.remove(vertA.getPosicionEnVertices());
			fila = vertA.getIndice();
			for(int col = 0; col < cantVertices; col++) {
				if(matriz[fila][col] != null) {
					this.removeEdge(matriz[fila][col]);
				}
			}
		} catch (InvalidPositionException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if(e == null) {
			throw new InvalidEdgeException("Error remove(e) --> Arco invalido.");
		}
		ArcoM<V,E> arco = (ArcoM<V,E>) e;
		int fila = 0;
		int col = 0;
		try {
			fila = arco.getVertA().getIndice();
			col = arco.getVertB().getIndice();
			matriz[fila][col] = matriz[col][fila] = null;
			arcos.remove(arco.getPosicionEnArcos());
		} catch (InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		return arco.getRotulo();
	}
}

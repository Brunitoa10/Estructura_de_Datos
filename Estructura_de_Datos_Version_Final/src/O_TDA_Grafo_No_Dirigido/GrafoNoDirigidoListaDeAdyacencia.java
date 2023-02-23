package O_TDA_Grafo_No_Dirigido;

import java.util.Iterator;

import A_Excepciones.BoundaryViolationException;
import A_Excepciones.EmptyListException;
import A_Excepciones.InvalidEdgeException;
import A_Excepciones.InvalidKeyException;
import A_Excepciones.InvalidPositionException;
import A_Excepciones.InvalidVertexException;
import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;
import F_TDA_Mapeo.Map;
import F_TDA_Mapeo.MapeoConListaDoblementeEnlazada;
import Z_Operaciones_Grafos.Par;

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
		if(v == null) {
			throw new InvalidVertexException("Error (opposite(v,e)) --> Vertice null.");
		}
		if(e == null) {
			throw new InvalidEdgeException("Error (opposite(v,e)) --> Arco null.");
		}
		Vertice<V,E> nuevoVertice = (Vertice<V,E>) v;
		Arco<V, E> nuevoArco = (Arco<V,E>) e;
		Vertex<V> retornar = null;
		boolean existeRelacion = false;
		
		if(nuevoArco.getV1().equals(nuevoVertice)) {
			retornar = nuevoArco.getV2();
			existeRelacion = true;
		}else {
			if(nuevoArco.getV2().equals(nuevoVertice)) {
				retornar = nuevoArco.getV1();
				existeRelacion = true;
			}
		}
		
		if(!existeRelacion) {
			throw new InvalidEdgeException("Error (opposite(v,e)) --> El vertice y el arco no estan relacionados.");
		}
		return retornar;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		if(e == null) {
			throw new InvalidEdgeException("Error (endVertices(e)) --> Arco null.");
		}
		Vertex<V>[] retornar = (Vertex<V>[]) new Vertex[2];
		Arco<V,E> nuevoArco = (Arco<V, E>) e;
		/*retornar[0] = (Vertex<V>) nuevoArco.getPosicionEnListaV1();
		retornar[1] = (Vertex<V>) nuevoArco.getPosicionEnListaV2();*/
		retornar[0] = (Vertex<V>) nuevoArco.getV1();
		retornar[1] = (Vertex<V>) nuevoArco.getV2();
		return retornar;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if(v == null || w == null) {
			throw new InvalidVertexException("Error (areAdjacent(v,w)) --> Vertice null.");
		}
		Vertice<V,E> verticeV = (Vertice<V, E>) v;
		Vertice<V,E> verticeW = (Vertice<V, E>) w;
		boolean retorno = false;
		Iterator<Arco<V,E>> it = verticeV.getAdyacentes().iterator();
		Arco<V,E> tmpArco = null;
		try {
			while(!retorno && it.hasNext()) {
				tmpArco = it.next();
				if(this.opposite(verticeV, tmpArco).equals(verticeW)) {
					retorno = true;
				}else {
					retorno = false;
				}
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if(v == null || x == null) {
			throw new InvalidVertexException("Error (replace(v,x)) --> Vertice null.");
		}
		Vertice<V,E> verticeV = (Vertice<V, E>) v;
		V retorno = verticeV.element();
		verticeV.setRotulo(x);
		return retorno;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		try {
			nodos.addLast(v);
			v.setPosicionEnListaVertices(nodos.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
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
			System.out.println(e1.getMessage());
		}
		return nuevoArco;
	}//O(1)

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if(v == null) {
			throw new InvalidVertexException("Error (removeVertex(v)) --> Vertice null.");
		}
		Vertice<V,E> verticeAretornar = (Vertice<V, E>) v;
		
		try {
			for(Edge<E> e : verticeAretornar.getAdyacentes()) {
				this.removeEdge(e);
			}	
			nodos.remove(verticeAretornar.getPosicionEnListaVertices());
		} catch (InvalidEdgeException | InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		return verticeAretornar.element();
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if(e == null) {
			throw new InvalidEdgeException("Error (removeEdge(e)) --> Arco null.");
		}
		Arco<V,E> nuevoArco = null;
		Vertice<V,E> verticeV1 = null;
		Vertice<V,E> verticeV2 = null;
		Position<Arco<V,E>> posicionNuevoArco = null; 
		E retorno = null;
		try {
			//Recupero extremos del arco
			nuevoArco = (Arco<V, E>) e;
			verticeV1 = nuevoArco.getV1(); 
			verticeV2 = nuevoArco.getV2();
			//Elimino a e de la lista de adyacencia de v1
			verticeV1.getAdyacentes().remove(nuevoArco.getPosicionEnListaV1());
			//Elimino a e de la lista de adyacencia de v1
			verticeV2.getAdyacentes().remove(nuevoArco.getPosicionEnListaV2());
			//Elimino a e de la lista de arcos y retorno el rotulo del arco
			posicionNuevoArco = nuevoArco.getPosicionEnArcos();
			retorno = arcos.remove(posicionNuevoArco).element();
		} catch (InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		return retorno;
	}//O(1)
	
	//Ejercicios
	public PositionList<Vertex<V>> caminoEconomico(GraphNoDirigido<V, E> g, Vertex<V> origen, Vertex<V> destino) {

		// Almacenamos un mapeo de los vertices visitados
		// <VerticeO,false> --> el vertice O no fue visitado
		// <VerticeO,true> --> el vertice O  fue visitado
		
		Map<Vertex<V>,Boolean> visitados = new MapeoConListaDoblementeEnlazada<Vertex<V>,Boolean>();
		
		//Almaceno el costo de recorreer un camino y su costo tiene que ser el menor posible
		
		Par p = new Par();
		p.setPeso(Integer.MAX_VALUE);
		PositionList<Vertex<V>> L = new listaDoblementeEnlazada<Vertex<V>>();
		int pesoAux = 0;
		
		try {
			for(Vertex<V> v : g.vertices()) {
				visitados.put(v, false);
			}
			caminoEcoAux(origen,destino,g,visitados,p,L,pesoAux);
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return p.getCamino_minimo();
	}
	
	private Par caminoEcoAux(Vertex<V> origen, Vertex<V> destino, GraphNoDirigido<V, E> g,Map<Vertex<V>, Boolean> visitados, Par p, PositionList<Vertex<V>> caminoActual, int pesoAux) {
		
		try {
			caminoActual.addLast(origen);
			visitados.put(origen,true);
			if(origen.equals(destino)) {
				if(pesoAux < p.getPeso()) {
					p.setCamino_minimo(clonar(caminoActual));
					p.setPeso(pesoAux);
				}
			}else {
				for(Edge<E> arco : g.incidentEdges(origen)) {
					Vertex<V> opuesto = g.opposite(origen, arco);
					if(visitados.get(opuesto) == false) {
						int	pesoArco = pesoAux +(int) arco.element();
						caminoEcoAux(opuesto,destino,g,visitados,p,caminoActual,pesoAux+pesoArco);
					}
				}
			}
			caminoActual.remove(caminoActual.last());
			visitados.put(origen, false);
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		return p;
	}
	private PositionList<Vertex<V>> clonar(PositionList<Vertex<V>> camino_actual) {
		PositionList<Vertex<V>> clon = new listaDoblementeEnlazada<Vertex<V>>();
		PositionList<Vertex<V>> aux = new listaDoblementeEnlazada<Vertex<V>>();

		try {
			Vertex<V> vert, vert2;
			
			while (!camino_actual.isEmpty()) {
				vert = camino_actual.remove(camino_actual.first());
				clon.addLast(vert);
				aux.addLast(vert);
			}

			while (!aux.isEmpty()) {
				vert2 = aux.remove(aux.first());
				camino_actual.addLast(vert2);
			}
		} catch (EmptyListException | InvalidPositionException e) {
			System.out.println(e.getMessage());
		}

		return clon;
	}
}
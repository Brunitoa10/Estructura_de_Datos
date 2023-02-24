package O_TDA_Grafo_No_Dirigido;

import java.util.Iterator;

import A_Excepciones.*;
import B_TDA_Pila.PilaEnlazada;
import B_TDA_Pila.Stack;
import C_TDA_Cola.ColaEnlazada;
import C_TDA_Cola.Queue;
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
	
	/*	Ejercicios 5A
	 * 
	 * Implemente en Java un algoritmo que dado un grafo con arcos pesados y dos vértices A y B, encuentre el camino más 
	 * económico de A a B.
	 * 
	 * Determine el orden del tiempo de ejecución de su solución considerando la complejidad temporal de la implementación del grafo y 
	 * de cualquier otra clase que necesitara.
	 * 
	 */
	
	public PositionList<Vertex<V>> caminoEconomico(Vertex<V> origen, Vertex<V> destino) {

		// Creo un mapeo de los vertices donde almaceno si fueron visitados o no
		// <VerticeO,false> --> el vertice O no fue visitado
		// <VerticeO,true> --> el vertice O  fue visitado
		
		Map<Vertex<V>,Boolean> visitados = new MapeoConListaDoblementeEnlazada<Vertex<V>,Boolean>();
		
		//Almaceno el costo de recorreer un camino y su costo tiene que ser el menor posible
		Par p = new Par();
		p.setPeso(Integer.MAX_VALUE);
		
		//Creo una lista donde voy a almacenar los vertices que conforman el camino mas economico
		PositionList<Vertex<V>> list = new listaDoblementeEnlazada<Vertex<V>>();
		int pesoAux = 0;
		
		
		try {
			//Para cada vertice v del grafo g marcos el vertice v como no visitado
			for(Vertex<V> v : this.vertices()) {
				visitados.put(v, false);
			}
			//Llamo al metodo recursivo
			caminoEcoAux(origen,destino,this,visitados,p,list,pesoAux);
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return p.getCamino_minimo();
	}
	
	private Par caminoEcoAux(Vertex<V> origen, Vertex<V> destino, GraphNoDirigido<V, E> g,Map<Vertex<V>, Boolean> visitados, Par p, PositionList<Vertex<V>> caminoActual, int pesoAux) {
		try {
			//Agrego al final de la lista el vertice origen
			caminoActual.addLast(origen);
			
			//Marco al vertice origen como visitado
			visitados.put(origen,true);
			
			//Si solo hay un vertice y el peso < al peso del camino actual
			if(origen == destino) {
				if(pesoAux < p.getPeso()) {
					//Hago una clocacion del camino actual y actualizo su peso
					p.setCamino_minimo(clonar(caminoActual));
					p.setPeso(pesoAux);
				}
			}else {
				//Para cada arco arc de los arcos incidentes del origen en g
				for(Edge<E> arc : g.incidentEdges(origen)) {
					//Obtener el vertice opuesto del vertice origen y el arco arc
					Vertex<V> opuesto = g.opposite(origen, arc);
					//Si el vertice opuesto no esta visitado
					if(visitados.get(opuesto) == false) {
						//Actualizo el peso del arco y llamo al metodo recursivo
						pesoAux +=(int) arc.element();
						caminoEcoAux(opuesto,destino,g,visitados,p,caminoActual,pesoAux);
					}
				}
			}
			//Elimino el ultimo vertice de la lista y marco al vertice origen como no visitado
			caminoActual.remove(caminoActual.last());
			visitados.put(origen, false);
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return p;
	}
	
	private PositionList<Vertex<V>> clonar(PositionList<Vertex<V>> camino_actual) {
		PositionList<Vertex<V>> clon = new listaDoblementeEnlazada<Vertex<V>>();
		PositionList<Vertex<V>> aux = new listaDoblementeEnlazada<Vertex<V>>();
		Vertex<V> vert;
		try {
			while (!camino_actual.isEmpty()) {
				vert = camino_actual.remove(camino_actual.first());
				clon.addLast(vert);
				aux.addLast(vert);
			}

			while (!aux.isEmpty()) {
				camino_actual.addLast(aux.remove(aux.first()));
			}
		} catch (EmptyListException | InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return clon;
	}

	/* Ejercicio 5B
	 *  
	 * Implemente en Java un algoritmo que dado un grafo y dos vértices A y B, encuentre el camino más corto de A a B. 
	 * Determine el orden del tiempo de ejecución de su solución considerando la complejidad temporal de la implementación del grafo 
	 * y de cualquier otra clase que necesitara.
	 * 
	 * BFSsearch para hallar caminos
	 */
	public PositionList<Vertex<V>> caminoMasCorto(Vertex<V> origen, Vertex<V> destino){
		
		// Creo un mapeo de los vertices donde almaceno si fueron visitados o no
		Map<Vertex<V>,Boolean> visitados = new MapeoConListaDoblementeEnlazada<Vertex<V>,Boolean>();
		//Creo una lista donde voy a almacenar el camino mas corto
		PositionList<Vertex<V>> list = new listaDoblementeEnlazada<Vertex<V>>();
		
		try {
			//Para cada vertice v del grafo, marcar a v como no visitado
			for(Vertex<V> v : this.vertices()) {
				visitados.put(v, false);
			}
			list = caminoMasCortoAux(origen,destino,visitados,list);		
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	private PositionList<Vertex<V>> caminoMasCortoAux(Vertex<V> origen, Vertex<V> destino, Map<Vertex<V>,Boolean> visitados,PositionList<Vertex<V>> lista) {
		
		Map<Vertex<V>,Vertex<V>> previo = new MapeoConListaDoblementeEnlazada<Vertex<V>,Vertex<V>> ();
		//Creo una cola donde voy a almacenar los vertices
		Queue<Vertex<V>> cola = new ColaEnlazada<Vertex<V>>();
		boolean encontre = false;
		Vertex<V> tmp = null;
		Vertex<V> opuesto = null;
		
		try {
			//Encolo a origen y lo marco como visitado
			cola.enqueue(origen);
			visitados.put(origen, true);
			
			//Mientras haya elementos en la cola y no encuentre el elemento
			while(!cola.isEmpty() && !encontre) {
				tmp = cola.dequeue();
				if(tmp == destino) {
					encontre = true;
				}else {
					//Para cada vertice v adyacente de x hacer
					for(Edge<E> e : this.incidentEdges(tmp)) {
						opuesto = this.opposite(tmp, e);
						//v no esta visitado
						if(visitados.get(opuesto) == false) {
							cola.enqueue(opuesto);
							visitados.put(opuesto, true);
							previo.put(opuesto, tmp);
						}
					}
				}
			}
		} catch (InvalidKeyException | EmptyQueueException | InvalidVertexException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
		
		if(encontre) {
			lista = recuperar(origen,destino,previo);
		}
		return lista;
	}

	private PositionList<Vertex<V>> recuperar(Vertex<V> origen, Vertex<V> destino, Map<Vertex<V>, Vertex<V>> previo) {
		//Creo una pila
		Stack<Vertex<V>> pila = new <Vertex<V>> PilaEnlazada<Vertex<V>>();
		
		//Creo la lista a retornar
		PositionList<Vertex<V>> list = new listaDoblementeEnlazada<Vertex<V>>();
		 
		Vertex<V> tmp = destino;
		
		try {		
			while(tmp != null) {
				pila.push(tmp);
				tmp = previo.get(tmp);
			}
			while(!pila.isEmpty()) {
				list.addLast(pila.pop());
			}
		} catch (InvalidKeyException | EmptyStackException e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	/* Ejercicio 5C
	 *  
	 * Modifique la solución dada en el inciso (b) para hallar todos los caminos de A a B
	 * 
	 * BFSsearch para hallar caminos
	 */
	public PositionList<Vertex<V>> todosLosCaminos(Vertex<V> origen, Vertex<V> destino){
		// Creo un mapeo de los vertices donde almaceno si fueron visitados o no
		Map<Vertex<V>,Boolean> visitados = new MapeoConListaDoblementeEnlazada<Vertex<V>,Boolean>();
		//Creo una lista donde voy a almacenar el camino 
		PositionList<Vertex<V>> listResultado = new listaDoblementeEnlazada<Vertex<V>>();
		
		try {
			//Para cada vertice v del grafo, marcar a v como no visitado
			for(Vertex<V> v : this.vertices()) {
				visitados.put(v, false);
			}
			todosLosCaminosAux(origen,destino,visitados,listResultado);
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return listResultado;
	}

	private PositionList<Vertex<V>> todosLosCaminosAux(Vertex<V> origen, Vertex<V> destino, Map<Vertex<V>, Boolean> visitados, PositionList<Vertex<V>> listResultado) {
		Par<V> listTmp = new Par(); 
		try {
			listResultado.addLast(origen);
			visitados.put(origen, true);
			
			if(origen == destino) {
				listTmp.setCamino_minimo(clonar(listResultado));
				visitados.put(destino, false);
			}else {
				//Para cada arco arc de los arcos incidentes del origen en g
				for(Edge<E> arc : this.incidentEdges(origen)) {
					//Obtener el vertice opuesto del vertice origen y el arco arc
					Vertex<V> opuesto = this.opposite(origen, arc);
					//Si el vertice opuesto no esta visitado
					if(visitados.get(opuesto) == false) {
						todosLosCaminosAux(opuesto,destino,visitados,listResultado);
					}
				}
			}
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
		
		return listResultado;
	}
	
	/* Ejercicio 5D
	 *  
	 * Modifique la solución dada en el inciso (b) para hallar al menos un camino de A a B
	 * 
	 * DFSPinchado --> Permite hallar un camino entre dos vertices origen y destino
	 */
	public PositionList<Vertex<V>> alMenosUnCamino(Vertex<V> origen, Vertex<V> destino){
		// Creo un mapeo de los vertices donde almaceno si fueron visitados o no
		Map<Vertex<V>,Boolean> visitados = new MapeoConListaDoblementeEnlazada<Vertex<V>,Boolean>();
		//Creo una lista donde voy a almacenar el camino 
		PositionList<Vertex<V>> listResultado = new listaDoblementeEnlazada<Vertex<V>>();
	
		try {
			//Para cada vertice v del grafo, marcar a v como no visitado
			for(Vertex<V> v : this.vertices()) {
				visitados.put(v, false);
			}
			hallarCamino(origen,destino,visitados,listResultado);
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return listResultado;
	}
	//Esta forma de programar no es estructurada dado que el return romple el flujo de ejecucion, pero funciona
	private boolean hallarCamino(Vertex<V> origen, Vertex<V> destino, Map<Vertex<V>, Boolean> visitados,	PositionList<Vertex<V>> listResultado) {
		//boolean encontre = false;
		try {
			visitados.put(origen, true);
			listResultado.addLast(origen);
				
			if(origen == destino) {
				return true;
			}else {
				//Para cada arco arc de los arcos incidentes del origen en g
				for(Edge<E> arc : this.incidentEdges(origen)) {
					//Obtener el vertice opuesto del vertice origen y el arco arc
					Vertex<V> opuesto = this.opposite(origen, arc);
					//Si el vertice opuesto no esta visitado
					if(visitados.get(opuesto) == false) {
						if(hallarCamino(opuesto,destino,visitados,listResultado)) {
							return true;
						}
					}
				}
			}
			listResultado.remove(listResultado.last());
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException  e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}

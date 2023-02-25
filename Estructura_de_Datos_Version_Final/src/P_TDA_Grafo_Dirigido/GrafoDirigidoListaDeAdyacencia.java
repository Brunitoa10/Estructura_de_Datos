package P_TDA_Grafo_Dirigido;

import A_Excepciones.*;
import B_TDA_Pila.PilaEnlazada;
import B_TDA_Pila.Stack;
import C_TDA_Cola.*;
import D_TDA_Lista.*;
import F_TDA_Mapeo.*;
import O_TDA_Grafo_No_Dirigido.Edge;
import O_TDA_Grafo_No_Dirigido.Vertex;

import java.util.Iterator;

public class GrafoDirigidoListaDeAdyacencia<V, E> implements GraphDirigido<V, E> {
	
	//Atributos de instancia
	protected PositionList<VerticeD<V,E>> vertice;
	protected PositionList<ArcoD<V,E>> arco;
	
	//Constructor
	public GrafoDirigidoListaDeAdyacencia() {
		vertice = new listaDoblementeEnlazada<VerticeD<V,E>>();
		arco = new listaDoblementeEnlazada<ArcoD<V,E>>();
	}

	@Override
	public Iterable<VertexD<V>> vertices() {
		PositionList<VertexD<V>> list = new listaDoblementeEnlazada<VertexD<V>>();
		for (VertexD<V> v : vertice) {
			list.addLast(v);
		}
		return list;
	}

	@Override
	public Iterable<EdgeD<E>> edges() {
		PositionList<EdgeD<E>> list = new listaDoblementeEnlazada<EdgeD<E>>();
		for (EdgeD<E> a : arco) {
			list.addLast(a);
		}
		return list;
	}

	@Override
	public Iterable<EdgeD<E>> incidentEdges(VertexD<V> v) throws InvalidVertexException {
		VerticeD<V, E> vert = checkVertex(v);
		PositionList<EdgeD<E>> list = new listaDoblementeEnlazada<EdgeD<E>>();
		for (ArcoD<V, E> arco : vert.getListaIncidentes()) {
			list.addLast(arco);
		}
		return list;
	}

	@Override
	public Iterable<EdgeD<E>> succesorEdges(VertexD<V> v) throws InvalidVertexException {
		VerticeD<V, E> ver = checkVertex(v);
		PositionList<EdgeD<E>> lista = new listaDoblementeEnlazada<EdgeD<E>>();
		for (ArcoD<V, E> arco : ver.getListaEmergentes()) {
			lista.addLast(arco);
		}
		return lista;
	}

	@Override
	public VertexD<V> opposite(VertexD<V> v, EdgeD<E> e) throws InvalidVertexException, InvalidEdgeException {
		ArcoD<V, E> arco = checkEdge(e);
		VerticeD<V, E> vert = checkVertex(v);
		VerticeD<V, E> toReturn = null;
		//v1 -- cola v2 -- punta
		if (arco.getcola().equals(vert)) {
			toReturn = arco.getpunta();
		} else {
			if (arco.getpunta().equals(vert)) {
				toReturn = arco.getcola();
			} else {
				throw new InvalidEdgeException("El vertice y el arco no estan relacionados: opposite()");
			}
		}
		return toReturn;
	}

	@Override
	//v1 -- cola v2 -- punta
	public VertexD<V>[] endvertices(EdgeD<E> e) throws InvalidEdgeException {
		ArcoD<V, E> arco = checkEdge(e);
		VertexD<V>[] vert = (VertexD<V>[]) new VertexD[2];
		vert[0] = arco.getcola();
		vert[1] = arco.getpunta();
		return vert;
	}

	@Override
	//v1 -- cola v2 -- punta
	public boolean areAdjacent(VertexD<V> v, VertexD<V> w) throws InvalidVertexException {
		VerticeD<V, E> vv = checkVertex(v);
		VerticeD<V, E> ww = checkVertex(w);
		boolean encontre = false;
		ArcoD<V, E> arcos = null;
		Iterator<ArcoD<V, E>> it = arco.iterator();
		while (!encontre && it.hasNext()) {
			arcos = (ArcoD<V, E>) it.next();
			if (arcos.getcola().equals(vv) && arcos.getpunta().equals(ww) || arcos.getpunta().equals(vv) && arcos.getcola().equals(ww)) {
				encontre = true;
			}
		}

		return encontre;
	}

	@Override
	public V replace(VertexD<V> v, V x) throws InvalidVertexException {
		VerticeD<V, E> vert = checkVertex(v);
		V elem = vert.element();
		if (x == null) {
			throw new InvalidVertexException("El elemento nuevo del vertice, es nulo: replace()");
		}
		vert.setRotulo(x);
		return elem;
	}

	@Override
	public VertexD<V> insertVertex(V x) {
		VerticeD<V, E> vert = new VerticeD<V, E>(x);
		try {
			vertice.addLast(vert);
			vert.setPos(vertice.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());;
		}
		return vert;
	}

	@Override
	public EdgeD<E> insertEdge(VertexD<V> v, VertexD<V> w, E e) throws InvalidVertexException {
		VerticeD<V, E> vertA = (VerticeD<V, E>) v;
		VerticeD<V, E> vertB = (VerticeD<V, E>) w;
		ArcoD<V, E> nuevoArco = new ArcoD<V, E>(e, vertA, vertB);
		
		try {
			if (vertB == null || vertA == null) {
				throw new InvalidVertexException("Vértice inválido");
			} else {
				arco.addLast(nuevoArco);
				nuevoArco.setPosEnListaArcosGrafo(arco.last());// agrego los vertices a la lista de nodos
				vertA.getListaIncidentes().addLast(nuevoArco);// a v1 lo agrego en emergentes (pq es el origen del arco) y tambien lo agrego en incidentes
				vertA.getListaEmergentes().addLast(nuevoArco);
				vertB.getListaIncidentes().addLast(nuevoArco);
				nuevoArco.setposEnListaVerticeA(vertA.getListaIncidentes().last());
				nuevoArco.setposEnListaVerticeB(vertB.getListaIncidentes().last());
			}
		} catch (EmptyListException ex) {
			System.out.println(ex.getMessage());
		}
		return nuevoArco;
	}

	@Override
	public V removeVertex(VertexD<V> v) throws InvalidVertexException {
		VerticeD<V, E> vert = checkVertex(v);
		try {
			for (ArcoD<V, E> a : vert.getListaEmergentes()) {
				removeEdge(a);
			}
			vertice.remove(vert.getPosEnListaNodos());
		} catch (InvalidEdgeException | InvalidPositionException ex) {
			throw new InvalidVertexException("Sucedio un error al eliminar el vertice :removeVertex()");
		}
		return vert.element();
	}

	@Override
	//v1 -- cola v2 -- punta
	public E removeEdge(EdgeD<E> e) throws InvalidEdgeException {
		ArcoD<V, E> arcos = checkEdge(e);
		E toReturn = arcos.element();
		try {
			// elimino la posicion del arco que esta en la lista de los vertices que une
			arcos.getcola().getListaIncidentes().remove(arcos.getPoscola());
			arcos.getpunta().getListaIncidentes().remove(arcos.getPospunta());
			arco.remove(arcos.getPosEnListaArcosGrafo());
		} catch (InvalidPositionException e1) {
			throw new InvalidEdgeException("Posicion Invalida en removeEdge()");
		}
		return toReturn;
	}
	/**
	 * CheckVertex
	 * 
	 * @param VertexD v
	 * @throws InvalidVertexException
	 * @returns Metodo que controla si el vertice es nulo y devuelve el vertice casteado
	 * 
	 */
	private VerticeD<V, E> checkVertex(VertexD<V> v) throws InvalidVertexException {
		if (v == null) {
			throw new InvalidVertexException("El vertice recibido es nulo");
		}
		return (VerticeD<V, E>) v;
	}

	/**
	 * CheckEdge
	 * 
	 * @param EdgeD e
	 * @throws InvalidEdgeException
	 * @returns Metodo que controla si el arco es nulo y devuelve el arco casteado
	 */
	private ArcoD<V, E> checkEdge(EdgeD<E> e) throws InvalidEdgeException {
		if (e == null) {
			throw new InvalidEdgeException("El edge recibido es nulo");
		}
		return (ArcoD<V, E>) e;
	}
	
	//Ejercicios
	
	/*	Ejercicio 6B
	 * 
	 * Escriba un método que, dado un rótulo R y un digrafo G, encuentre el primer vértice cuyo	rótulo es R.
	 * 
	 */
	public boolean buscarRotulo(V rot) {
		boolean encontre = false;
		Iterator<VertexD<V>> it = this.vertices().iterator(); 

		while(!encontre && it.hasNext()) {
			encontre = (it.next().element() == rot) ? true : false;
		}
		return encontre;
	}
	
	/*	Ejercicio 6D
	 * 
	 * Escriba un método que reciba un digrafo G y el rótulo R de un vértice, y que elimine de G todos los vértices que encuentre 
	 * con rótulo R.
	 * 
	 */
	
	public boolean eliminarVeticesConRotulosR(V rot) throws InvalidVertexException{
		int apariciones = 0;
		int eliminaciones = 0;
		
		for(VertexD<V> vert : this.vertices()) {
			if(vert.element().equals(rot)) {
				apariciones++;
			}
		}
		
		apariciones = (apariciones == 0) ? -1 : apariciones;
		
		for(VertexD<V> vert : this.vertices()) {
			if(vert.element().equals(rot)) {
				this.removeVertex(vert);
				eliminaciones++;
			}
		}
		return apariciones == eliminaciones;
	}

	/*	Ejercicio 6E
	 * 
	 *  Escriba un método que reciba un digrafo G y un vértice A, y establezca si G es una lista cabeza A.
	 * 
	 */
	public boolean esListaConCabezaA(VertexD<V> rot){
		
		// Creo un mapeo de los vertices donde almaceno si fueron visitados o no
		Map<VertexD<V>,Boolean> visitados = new MapeoConListaDoblementeEnlazada<VertexD<V>,Boolean>();
		
		boolean resultado = false;
		try {
			//Para cada vertice v del grafo, marcar a v como no visitado
			for(VertexD<V> v : this.vertices()) {
				visitados.put(v, false);
			}
			resultado = BFSAux(rot,visitados);		
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	private boolean BFSAux(VertexD<V> rot, Map<VertexD<V>, Boolean> visitados) {
		Queue<VertexD<V>> cola = new ColaEnlazada<VertexD<V>>();
		VertexD<V> tmpVert = null;
		VertexD<V> opuesto = null;
		EdgeD<E> tmpArco = null;
		try {
			cola.enqueue(rot);
			visitados.put(rot, true);
			while(!cola.isEmpty()) {
				tmpVert = cola.dequeue();
				Iterator<EdgeD<E>> itAdyacentes = this.succesorEdges(tmpVert).iterator();
				if(!itAdyacentes.hasNext()) {
					tmpArco = itAdyacentes.next();
					if(itAdyacentes.hasNext()) {
						return false;
					}else {
						opuesto = this.opposite(tmpVert, tmpArco);
						if(visitados.get(opuesto) == false) {
							visitados.put(opuesto, true);
							cola.enqueue(opuesto);
						}else {
							return false;
						}
					}
				}
			}
		}catch(InvalidKeyException | InvalidVertexException | InvalidEdgeException | EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	
}

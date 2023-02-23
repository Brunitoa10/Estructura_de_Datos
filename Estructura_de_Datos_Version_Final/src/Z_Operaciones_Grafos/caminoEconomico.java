package Z_Operaciones_Grafos;

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
import O_TDA_Grafo_No_Dirigido.Edge;
import O_TDA_Grafo_No_Dirigido.GrafoNoDirigidoListaDeAdyacencia;
import O_TDA_Grafo_No_Dirigido.GraphNoDirigido;
import O_TDA_Grafo_No_Dirigido.Vertex;

public class caminoEconomico<V,E> {
	
	public PositionList<Vertex<V>> caminoEconomico(GraphNoDirigido<V, E> g,Vertex<V> origen, Vertex<V> destino) {

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
			for(Vertex<V> v : g.vertices()) {
				visitados.put(v, false);
			}
			//Llamo al metodo recursivo
			caminoEcoAux(origen,destino,g,visitados,p,list,pesoAux);
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
}

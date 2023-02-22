package O_TDA_Grafo_No_Dirigido;

import A_Excepciones.InvalidEdgeException;
import A_Excepciones.InvalidVertexException;

public class testerTeoriaADTGrafo {

	public static void main(String[] args) {
		/*
		 * Creo un grafo con rotulo de vertice de tipo String y rotulos de arco de tipo entero
		 */
		
		GraphNoDirigido<String,Integer> g = new GrafoNoDirigidoListaDeAdyacencia<String,Integer>();
		
		try{
			
			//Inicializo los vertices
			Vertex<String> bb = g.insertVertex("Bahia Blanca");
			Vertex<String> ba = g.insertVertex("Buenos Aires");
			Vertex<String> pa = g.insertVertex("Punta Alta");
			Vertex<String> mdp = g.insertVertex("Mar del Plata");
			
			//Inicializo los arcos
			Edge<Integer> vueloBB2PA = g.insertEdge(bb, pa, 15);
			Edge<Integer> vueloBB2MDP = g.insertEdge(bb, mdp, 470);
			Edge<Integer> vueloMDP2BA = g.insertEdge(mdp, ba, 400);
			Edge<Integer> vueloBA2PA = g.insertEdge(ba, pa, 150);
			
			//Elimino 1 arco y 1 vertice
			g.removeEdge(vueloBB2PA);
			g.removeVertex(pa);
		} catch (InvalidVertexException | InvalidEdgeException e) {
			System.out.println("Error --> "+e.getMessage());
		}
	}
	
}

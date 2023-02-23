package Z_Operaciones_Grafos;

import A_Excepciones.*;
import D_TDA_Lista.*;
import O_TDA_Grafo_No_Dirigido.*;

/* Implemente un metodo que dado un Grado G, un Vertice V, Vertice W
 * calcule el camino minimo de v a w. Imprimir el camino.*/

public class caminoEconomicoTester<V, E> {
	
	private static void salto() {
		System.out.println();
		System.out.println();
	}

	private static void testerCompleto2(GrafoNoDirigidoListaDeAdyacencia<String, Integer> g) {
		try {
			//Creo los vertices
			Vertex<String> origen = g.insertVertex("Origen");
			Vertex<String> nodoA = g.insertVertex("nodo(A)");
			Vertex<String> nodoB = g.insertVertex("nodo(B)");
			Vertex<String> nodoC = g.insertVertex("nodo(C)");
			Vertex<String> nodoE = g.insertVertex("nodo(E)");
			Vertex<String> nodoF = g.insertVertex("nodo(F)");
			Vertex<String> destino = g.insertVertex("Destino");
		
			//Creo los arcos
			g.insertEdge(origen, nodoA, 1);
			g.insertEdge(origen, nodoB, 4);
			g.insertEdge(nodoB, nodoF, 3);
			g.insertEdge(nodoA, nodoC, 2);
			g.insertEdge(nodoF, destino, 5);
			g.insertEdge(nodoC, nodoE, 1);
			g.insertEdge(nodoE, destino, 2);
			
			PositionList<Vertex<String>> list = g.caminoMasCorto(origen, destino);
			

			/* Resultado esperado
			 * 
			 * Origen ---> nodoB ---> nodoF ---> Destino
			 * 
			 */
			
			if(list.size() == 0) {
				System.out.println("oh! Algo salio mal");
			}else {
				Iterable<Position<Vertex<String>>> run = list.positions();
				for (Position<Vertex<String>> position : run) {
					System.out.print(" ---> "+position.element().element());
				}				
			}
				
		} catch (InvalidVertexException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void testerCompleto(GrafoNoDirigidoListaDeAdyacencia<String, Integer> g) {
		try {
			//Creo los vertices
			Vertex<String> origen = g.insertVertex("Origen");
			Vertex<String> nodoA = g.insertVertex("nodo(A)");
			Vertex<String> nodoB = g.insertVertex("nodo(B)");
			Vertex<String> nodoC = g.insertVertex("nodo(C)");
			Vertex<String> nodoE = g.insertVertex("nodo(E)");
			Vertex<String> nodoF = g.insertVertex("nodo(F)");
			Vertex<String> destino = g.insertVertex("Destino");
		
			//Creo los arcos
			g.insertEdge(origen, nodoA, 1);
			g.insertEdge(origen, nodoB, 4);
			g.insertEdge(nodoB, nodoF, 3);
			g.insertEdge(nodoA, nodoC, 2);
			g.insertEdge(nodoF, destino, 5);
			g.insertEdge(nodoC, nodoE, 1);
			g.insertEdge(nodoE, destino, 2);
			
			PositionList<Vertex<String>> list = g.caminoEconomico(origen, destino);
			

			/* Resultado esperado
			 * 
			 * Origen ---> nodoA ---> nodoC ---> nodoE ---> Destino
			 * 
			 */
			
			if(list.size() == 0) {
				System.out.println("oh! Algo salio mal");
			}else {
				Iterable<Position<Vertex<String>>> run = list.positions();
				for (Position<Vertex<String>> position : run) {
					System.out.print(" ---> "+position.element().element());
				}				
			}
				
		} catch (InvalidVertexException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void testerSimple(GrafoNoDirigidoListaDeAdyacencia<String, Integer> g) {
		try {
			//Creo los vertices
			Vertex<String> origen = g.insertVertex("Origen");
			Vertex<String> nodoA = g.insertVertex("nodo(A)");
			Vertex<String> destino = g.insertVertex("Destino");
		
			//Creo los arcos
			g.insertEdge(origen, nodoA, 2);
			g.insertEdge(nodoA, destino, 1);
			
			PositionList<Vertex<String>> list = g.caminoEconomico(origen, destino);
			
			if(list.size() == 0) {
				System.out.println("oh! Algo salio mal");
			}else {
				Iterable<Position<Vertex<String>>> run = list.positions();
				for (Position<Vertex<String>> position : run) {
					System.out.print(" ---> "+position.element().element());
				}				
			}
				
		} catch (InvalidVertexException e) {
			System.out.println(e.getMessage());
		}
	}
	private static void imprimir(String string) {
		System.out.println(string);
	}
	public static void main(String[] args) {
		GrafoNoDirigidoListaDeAdyacencia<String,Integer> g = new GrafoNoDirigidoListaDeAdyacencia<String,Integer>();
		
		imprimir("Testeando Camino mas economico simple");
			salto();
		testerSimple(g);
			salto();
			imprimir("Testeando Camino mas economico completo");
			salto();
		testerCompleto(g);
			salto();
			imprimir("Testeando Camino mas corto");
			salto();
		testerCompleto2(g);
	}

	
}


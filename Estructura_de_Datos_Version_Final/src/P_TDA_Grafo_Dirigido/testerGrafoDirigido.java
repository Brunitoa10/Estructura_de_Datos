package P_TDA_Grafo_Dirigido;

import A_Excepciones.InvalidVertexException;

public class testerGrafoDirigido {
	private static void ejercicio6B(GrafoDirigidoListaDeAdyacencia<String, Integer> g) {
		try {
			VertexD<String> a = g.insertVertex("A");
			VertexD<String> b = g.insertVertex("B");
			VertexD<String> c = g.insertVertex("C");
			VertexD<String> d = g.insertVertex("D");
			VertexD<String> e = g.insertVertex("E");
			VertexD<String> f = g.insertVertex("F");
			
			
			g.insertEdge(a, b, 4);
			g.insertEdge(a, c, 2);
			g.insertEdge(c, b, 4);
			g.insertEdge(c, d, 8);
			g.insertEdge(b, d, 5);
			g.insertEdge(c, e, 10);
			g.insertEdge(d, e, 2);
			g.insertEdge(e, f, 3);
			g.insertEdge(d, f, 6);
			
			imprimir("Testeando buscarRotulo");
				salto();
			imprimir("¿El nodo con rotulo A esta? --> "+g.buscarRotulo("A"));
				salto();
			imprimir("¿El nodo con rotulo B esta? --> "+g.buscarRotulo("B"));
				salto();	
			imprimir("¿El nodo con rotulo C esta? --> "+g.buscarRotulo("C"));
				salto();
			imprimir("¿El nodo con rotulo D esta? --> "+g.buscarRotulo("D"));
				salto();
			imprimir("¿El nodo con rotulo E esta? --> "+g.buscarRotulo("E"));
				salto();
			imprimir("¿El nodo con rotulo Z esta? --> "+g.buscarRotulo("Z"));
				salto();	
		} catch (InvalidVertexException e1) {
			System.out.println(e1.getMessage());
		}	
	}

	private static void ejercicio6D(GrafoDirigidoListaDeAdyacencia<String, Integer> g) {
		try {
			VertexD<String> a = g.insertVertex("A");
			VertexD<String> b = g.insertVertex("B");
			VertexD<String> c = g.insertVertex("C");
			VertexD<String> d = g.insertVertex("D");
			VertexD<String> e = g.insertVertex("E");
			VertexD<String> f = g.insertVertex("F");
			
			
			g.insertEdge(a, b, 4);
			g.insertEdge(a, c, 2);
			g.insertEdge(c, b, 4);
			g.insertEdge(c, d, 8);
			g.insertEdge(b, d, 5);
			g.insertEdge(c, e, 10);
			g.insertEdge(d, e, 2);
			g.insertEdge(e, f, 3);
			g.insertEdge(d, f, 6);
			
			imprimir("Testeando buscarRotulo");
				salto();
			imprimir("Eliminar nodo con rotulo A --> "+g.eliminarVeticesConRotulosR("A"));
				salto();
			imprimir("Eliminar nodo con rotulo Z --> "+g.eliminarVeticesConRotulosR("Z"));
				salto();	
			imprimir("Eliminar nodo con rotulo D --> "+g.eliminarVeticesConRotulosR("D"));
				salto();
		} catch (InvalidVertexException e1) {
			System.out.println(e1.getMessage());
		}	
	}
	
	private static void ejercicio6E(GrafoDirigidoListaDeAdyacencia<String, Integer> g) {
		try {
			VertexD<String> a = g.insertVertex("A");
			VertexD<String> b = g.insertVertex("B");
			VertexD<String> c = g.insertVertex("C");
			VertexD<String> d = g.insertVertex("D");
			VertexD<String> e = g.insertVertex("E");
			VertexD<String> f = g.insertVertex("F");
			
			
			g.insertEdge(a, b, 4);
			g.insertEdge(a, c, 2);
			g.insertEdge(c, b, 4);
			g.insertEdge(c, d, 8);
			g.insertEdge(b, d, 5);
			g.insertEdge(c, e, 10);
			g.insertEdge(d, e, 2);
			g.insertEdge(e, f, 3);
			g.insertEdge(d, f, 6);
			
			imprimir("Testeando esListaConCabezaA");
				salto();
			imprimir("Rotulo A es cabeza de lista --> "+g.esListaConCabezaA(a));
				salto();
			imprimir("Rotulo B es cabeza de lista --> "+g.esListaConCabezaA(b));
				salto();	
			imprimir("Rotulo F es cabeza de lista --> "+g.esListaConCabezaA(f));
				salto();
		} catch (InvalidVertexException e1) {
			System.out.println(e1.getMessage());
		}	
	}
	
	private static void imprimir(String string) {
		System.out.print(string);
	}

	private static void salto() {
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) {
		GrafoDirigidoListaDeAdyacencia<String, Integer> g = new GrafoDirigidoListaDeAdyacencia<String, Integer>();
		ejercicio6B(g);
		ejercicio6D(g);
		ejercicio6E(g);
	}
}

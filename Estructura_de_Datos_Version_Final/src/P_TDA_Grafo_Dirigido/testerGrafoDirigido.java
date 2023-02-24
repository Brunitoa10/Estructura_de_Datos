package P_TDA_Grafo_Dirigido;

import A_Excepciones.InvalidVertexException;

public class testerGrafoDirigido {
	private static void ejercicio6(GrafoDirigidoListaDeAdyacencia<String, Integer> g) {
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
			imprimir("¿El nodo con rotulo 4 esta? --> "+g.buscarRotulo(4));
				salto();
			imprimir("¿El nodo con rotulo 11 esta? --> "+g.buscarRotulo(11));
				salto();	
			imprimir("¿El nodo con rotulo 8 esta? --> "+g.buscarRotulo(8));
				salto();
			imprimir("¿El nodo con rotulo 10 esta? --> "+g.buscarRotulo(10));
				salto();
			imprimir("¿El nodo con rotulo 6 esta? --> "+g.buscarRotulo(6));
				salto();
			imprimir("¿El nodo con rotulo 25 esta? --> "+g.buscarRotulo(25));
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
		ejercicio6(g);
	}
}

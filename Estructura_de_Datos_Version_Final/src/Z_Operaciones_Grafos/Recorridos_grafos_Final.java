package Z_Operaciones_Grafos;

import A_Excepciones.InvalidKeyException;
import F_TDA_Mapeo.Map;
import F_TDA_Mapeo.MapeoConHashAbierto;
import P_TDA_Grafo_Dirigido.GraphDirigido;
import P_TDA_Grafo_Dirigido.VertexD;

public class Recorridos_grafos_Final<V,E> {
	public static <V,E> void DFSShell(GraphDirigido<V,E> g){
		Map<VertexD<V>,Boolean> visitados = new MapeoConHashAbierto<VertexD<V>,Boolean>(); 
		
		try {
			//Para cada vertice v de g marcar v como no visitado
			for(VertexD<V> v : g.vertices()) {
				visitados.put(v, false);
			}
			//Para cada vertice v de g si v no esta visitado
			for(VertexD<V> v : g.vertices()) {
				if(visitados.get(v) == false) {
					DFS(g,v);
				}
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
	}

	private static <V,E> void DFS(GraphDirigido<V, E> g, VertexD<V> v) {
		// TODO Auto-generated method stub
		
	}
}

package Z_Operaciones_Grafos;

import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;
import O_TDA_Grafo_No_Dirigido.Vertex;

/*	Almacena peso --> Costo del recorrido que hicimos (implementacion para grafos)
 * 	Almacena costo_minimo --> lista de vertices que guarda el camino mas economico
 * */
public class Par<V> {
	//Atributos de clase
	private float peso;
	private PositionList<Vertex<V>> camino_minimo;
	
	//Constructor
	public Par() {
		peso = 0;
		camino_minimo = new listaDoblementeEnlazada<Vertex<V>>();
	}
	
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public PositionList<Vertex<V>> getCamino_minimo() {
		return camino_minimo;
	}
	public void setCamino_minimo(PositionList<Vertex<V>> camino_minimo) {
		this.camino_minimo = camino_minimo;
	}
	
	
}

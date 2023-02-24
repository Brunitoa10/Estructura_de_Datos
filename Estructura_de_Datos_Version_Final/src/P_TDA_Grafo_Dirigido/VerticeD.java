package P_TDA_Grafo_Dirigido;

import D_TDA_Lista.Position;
import D_TDA_Lista.PositionList;
import D_TDA_Lista.listaDoblementeEnlazada;

public class VerticeD<V,E> implements VertexD<V> {

	//attributes
	protected V rotulo;
	protected Position<VerticeD<V,E>> pos;
	protected PositionList<ArcoD<V,E>> listaEmergentes;
	protected PositionList<ArcoD<V,E>> listaIncidentes;
	
	
	//constructor
	public VerticeD(V r) {
		rotulo = r;
		listaEmergentes = new listaDoblementeEnlazada<ArcoD<V,E>>();
		listaIncidentes = new listaDoblementeEnlazada<ArcoD<V,E>>();
	}
	
	//setters
	public void setRotulo(V v) {
		rotulo = v;
	}
	
	public void setPos(Position<VerticeD<V,E>> p) {
		pos = p;
	}
	
	public void listaEmergentes(PositionList<ArcoD<V,E>> l) {
		listaEmergentes = l;
	}
	
	public void listaIncidentes(PositionList<ArcoD<V,E>> l) {
		listaIncidentes = l;
	}
	
	//Lo agregue para q se puedan agregar arcos incidentes a la lista
	public void addEdgelistaIncidentes(ArcoD<V, E> v) {
		listaIncidentes.addLast(v);
	}
	
	public void addEdgelistaEmergentes(ArcoD<V, E> v) {
		listaEmergentes.addLast(v);
	}
	
	//getters
	public PositionList<ArcoD<V,E>> getListaEmergentes(){
		return listaEmergentes;
	}
	
	public PositionList<ArcoD<V,E>> getListaIncidentes(){
		return listaIncidentes;
	}
	
	public Position<VerticeD<V,E>> getPosEnListaNodos(){
		return pos;
	}
	
	public V element() {
		return rotulo;
	}
}

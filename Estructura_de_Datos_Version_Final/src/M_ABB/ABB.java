package M_ABB;

import java.util.Comparator;

import D_TDA_Lista.DefaultComparator;

public class ABB<E extends Comparable<E>> {
	
	//Atributos de instancia
	
	protected NodoABB<E> raiz;
	protected Comparator<E> comparador;
	
	//Constructor
	
	/*	Inicializa el ABB vacio con nodos dummy.
	 * 
	 *	En caso que no se parametrice un comparador
	 *	de elementos E definido por el cliente, se utiliza
	 *	un comparador por defecto que delega la tarea de la
	 *	comparacion al tipo E que es comparable.
	 */
	public ABB() {
		raiz = new NodoABB<E>(null,null);
		comparador = new DefaultComparator<E>();
	}
	
	/*	Inicializa el ABB vacio con nodos dummy
	 * 
	 *	Permite definir un comparador de elementos E que
	 *	los ordene de diferente manera
	 */
	
	public ABB(Comparator<E> comp) {
		raiz = new NodoABB<E>(null,null);
		comparador = comp;
	}
	
	/* 
	 *	@return Retorna el nodo raiz del ABB
	 */
	public NodoABB<E> getRaiz(){
		return raiz;
	}
	
	/*	Busca el NodoABB en el que debe almacenarse el elemento.
	 * 	Como el ABB contempla nodo dummy, siempre retorna un NodoABB.
	 * 
	 *	@param E elemento
	 *	@return Retorna el NodoABB en el que debe almacenarse el elemento
	 */
	public NodoABB<E> buscar(E elemento){
		return buscarAux(elemento,raiz); //Busqueda recursiva desde la raiz
	}
	
	private NodoABB<E> buscarAux(E x, NodoABB<E> nodo){
		NodoABB<E> retorno = null;
		if(nodo.getRotulo() == null) {//Si llegue al nodo dummy
			retorno = nodo;
		}else {//Estoy en un nodo con un dato no nulo
			int c = comparador.compare(x, nodo.getRotulo());
			if(c == 0) {//Lo encontre por que x == nodo.getRotulo()
				retorno = nodo;
			}else {
				if(c < 0) {//Busco a izquierda porque x < nodo.getRotulo()
					retorno = buscarAux(x,nodo.getIzq());
				}else {
					//Busco a derecha porque x > nodo.getRotulo()
					retorno = buscarAux(x,nodo.getDer());
				}
			}
		}
		return retorno;
	}
	/*	Expande un nodo dummu, reconvirtiendolo en un nodo
	 * 	con rotulo y dos hijos dummy
	 * 	
	 * 	@param NodoABB<E> n
	 * 	@param E elemento
	 */
	// 1) nodo contiene a e como elemento
	// 2) nodo tiene por hijos izquierdo y derecho dos nodos dummy
	
	public void expandir(NodoABB<E> nodo, E elemento) {
		if(nodo.getRotulo() == null) {
			//Creo dummy izquierdo
			nodo.setIzq(new NodoABB<E>(elemento));
			//Creo dummy derecho
			nodo.setDer(new NodoABB<E>(elemento));
		}
	}
	
	/*	Elimina el elemento, mantiendo el orden establecido por el ABB.
	 * 	Si el elemento no existe, no modifica el ABB.
	 *	
	 *	@param E elemento
	 */
	
	public void eliminar(E elemento) {
		// Busco e en el arbol para obtener el nodo que lo contiene
		NodoABB<E> nodo = buscar(elemento);
		if (nodo.getRotulo() != null) {
			if (isExternal(nodo)) {
				// CASO 1: nodo es hoja => convertirlo en dummy y soltar sus hijos dummy
				nodo.setRotulo(null);
				nodo.setDer(null);
				nodo.setIzq(null);
			} else { // nodo no es hoja
				if (nodo == raiz) {
					if (soloTieneHijoIzquierdo(raiz)) {
						raiz.setRotulo(null);
						raiz = raiz.getIzq();
						raiz.setPadre(null);
					} else if (soloTieneHijoDerecho(raiz)) {
						raiz.setRotulo(null);
						raiz = raiz.getDer();
						raiz.setPadre(null);
					} else {
						raiz.setRotulo(eliminarMinimo(raiz.getDer()));
					}
				} else 
					if (soloTieneHijoIzquierdo(nodo)) {
						// Caso 2: Enganchar al padre de p con el hijo izquierdo de p
						if (nodo.getPadre().getIzq() == nodo) { // p es el hijo izquierdo de su padre
							nodo.getPadre().setIzq(nodo.getIzq()); // el hijo izq del padre de p es ahora el hijo de p
							
						}else { // p es el hijo derecho de su padre
							nodo.getPadre().setDer(nodo.getIzq()); // el hijo derecho del padre de p es el hijo de p
							nodo.getIzq().setPadre(nodo.getPadre()); // Ahora el padre del hijo izq de p es su abuelo
							nodo.setPadre(null);
							nodo.setIzq(null);
							nodo.setDer(null);
						}
				} else { 
					if (soloTieneHijoDerecho(nodo)) {
						// Caso 3: Enganchar al padre de p con el hijo derecho de p
						if (nodo.getPadre().getDer() == nodo) { // p es hijo izquierdo de su padre
							nodo.getPadre().setDer(nodo.getDer()); // el hijo izq del padre de p es el hijo de p
							
						}else {
							nodo.getPadre().setIzq(nodo.getDer()); // el hijo derecho del padre de p es el hijo de
																				// p
							nodo.getDer().setPadre(nodo.getPadre()); // Ahora el padre del hijo der. de p es su abuelo
							nodo.setPadre(null);
							nodo.setDer(null);
							nodo.setIzq(null);
						}
					} else { // Caso 4: p tiene dos hijos => seteo como rótulo de p al rótulo del sucesor inorder de p.
						nodo.setRotulo(eliminarMinimo(nodo.getDer()));
					}
				}
			} // Fin else: p no es hoja
		}
	} // Fin método
	
	/* Elimina el nodo con rotulo minimo del subarbol que tiene como raiz a nodo
	 * El minimo rptulo del subarbol quetiene como raiz a nodo es el rtulo del primer nodo que
	 * encuentre yendo a la izquierda que no tiene hijo izquierdo
	 */
	
	private E eliminarMinimo(NodoABB<E> nodo) {
		E retorno = null;
		if(nodo.getIzq().getRotulo() == null) { //Si el hijo izquierdo es dummy
			retorno = nodo.getRotulo(); //Guardo rotulo a devolver
			if(nodo.getDer().getRotulo() == null) {//nodo es una hoja, sus hijos son dummy
				//Convierto a nodo en dummy haciendo nulo su rotulo
				nodo.setRotulo(null); 
				//Desengancho sus dos hijos dummy
				nodo.setIzq(null);
				nodo.setDer(null);
			}else {//nodo solo tiene hijo derecho(por que no tiene izquierdo)
				//Engancho al padre de nodo con el hijo derecho de nodo
				nodo.getPadre().setDer(nodo.getDer());
				nodo.getDer().setPadre(nodo.getPadre());
			}
		}else {
			retorno = eliminarMinimo(nodo.getIzq());
		}
		return retorno;
	}
	
	//nodo es una hoja si sus dos hijos son dummy
	private boolean isExternal(NodoABB<E> nodo) {
		return nodo.getIzq().getRotulo() == null && nodo.getDer().getRotulo() == null;
	}
	
	//nodo solo tiene hijo izquierdo si su hijo izquierdo no es dummy y su hijo derecho si
	private boolean soloTieneHijoIzquierdo(NodoABB<E> nodo) {
		return nodo.getIzq().getRotulo() != null && nodo.getDer().getRotulo() == null;
	}
	
	private boolean soloTieneHijoDerecho(NodoABB<E> nodo) {
		return nodo.getIzq().getRotulo() == null && nodo.getDer().getRotulo() != null;
	}
	
}
 
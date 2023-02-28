package R_Arboles_de_busqueda;

import java.util.Comparator;

import D_TDA_Lista.DefaultComparator;

public class AVL<E> {
	//Atributos
	protected NodoAVL<E> raiz;
	protected Comparator<E> comp;
	
	//Constructor
	public AVL(Comparator<E> comp) {
		raiz = new NodoAVL<E>(null);
		this.comp = comp;
	}
	
	public AVL() {
		this(new DefaultComparator<E>());
	}
	
	public NodoAVL<E> getRaiz() {
		return raiz;
	}
	
	public void insert(E x) {
		insertAux(raiz,x);
	}
	
	
	private void insertAux(NodoAVL<E> p, E item) {
		int comparacion, comp_item_y;
		NodoAVL<E> x, y, z;
		if (p != null)
			if (p.getRotulo() == null) {
				expandir(p, item);
			} else {
				comparacion = comp.compare(item, p.getRotulo());
				if (comparacion == 0) {
					p.setEliminado(false);/* ítem ya está en el árbol => no cambia nada */

				} else if (comparacion < 0) {
					insertAux(p.getIzq(), item);// Inserto recursivamente en el HI y a la vuelta rebalanceo
					if (Math.abs(p.getIzq().getAltura() - p.getDer().getAltura()) > 1) {

						x = p;
						y = p.getIzq();
						z = p.getIzq().getIzq();
						comp_item_y = comp.compare(item, y.getRotulo());
						if (comp_item_y < 0) {
							rotacion_simple_izquierda_a_derecha(x, y, z); // item < y => rotacion (i)
						} else {
							z = p.getIzq().getDer();
							rotacion_doble_izquierda_a_derecha(x, y, z); // item > y => rotacion (ii)

						}
					}

				} else {
					insertAux(p.getDer(), item);
					// testeo por las rotaciones (iii) e (iv)
					if (Math.abs(p.getIzq().getAltura() - p.getDer().getAltura()) > 1) {
						x = p;
						y = p.getDer();
						z = p.getDer().getDer();
						comp_item_y = comp.compare(item, y.getRotulo());
						if (comp_item_y > 0) { // rotacion 3 (rotulo > y){
							rotacion_simple_derecha_a_izquierda(x, y, z);

						} else {
							z = p.getDer().getIzq();
							rotacion_doble_derecha_a_izquierda(x, y, z);
						}
					}

				}
				p.setAltura(max(p.getIzq().getAltura(), p.getDer().getAltura()) + 1);
			}
	}
	
	// i
		private void rotacion_simple_izquierda_a_derecha(NodoAVL<E> x, NodoAVL<E> y, NodoAVL<E> z) {
			System.out.println("rotacion_simple_izquierda_a_derecha" + "");
			NodoAVL<E> t3 = null, nodoPadreX = x.getPadre();

			if (nodoPadreX != null) {
				if (nodoPadreX.getDer() == x) {
					nodoPadreX.setDer(y);
				}else {
					nodoPadreX.setIzq(y);
				}
			}else {
				raiz = y;
			}

			t3 = y.getDer();

			z.setPadre(y);
			y.setIzq(z);
			
			x.setIzq(t3);
			t3.setPadre(x);
			
			x.setPadre(y);
			y.setDer(x);

			y.setPadre(nodoPadreX);
		}

		private void rotacion_doble_izquierda_a_derecha(NodoAVL<E> x, NodoAVL<E> y, NodoAVL<E> z) {
			System.out.println("rotacion_doble_izquierda_a_derecha");
			NodoAVL<E> t2 = null, t3 = null, nodoPadreX = x.getPadre();

			if (nodoPadreX != null) {
				if (nodoPadreX.getDer() == x) {
					nodoPadreX.setDer(z);
				}else {
					nodoPadreX.setIzq(z);
					}
			}else {
				raiz = z;
			}

			t2 = z.getIzq();
			t3 = z.getDer();

			
			y.setDer(t2);
			if (t2 != null)
				t2.setPadre(y);
			
			y.setPadre(z);
			z.setIzq(y);
			
			x.setIzq(t3);
			if (t3 != null) {
				t3.setPadre(x);
			}
			
			x.setPadre(z);
			z.setDer(x);

			z.setPadre(nodoPadreX);
		}

	//3
		private void rotacion_simple_derecha_a_izquierda(NodoAVL<E> x, NodoAVL<E> y, NodoAVL<E> z) {
			System.out.println("rotacion_simple_derecha_a_izquierda");
			NodoAVL<E> t2 = null, nodoPadreX = x.getPadre();

			if (nodoPadreX != null) {
				if (nodoPadreX.getDer() == x) {
					nodoPadreX.setDer(y);
				}else {
					nodoPadreX.setIzq(y);
				}
			}else {
				raiz = y;
			}

			t2 = y.getIzq();

			y.setPadre(nodoPadreX);
			x.setDer(t2);
			if (t2 != null) {
				t2.setPadre(x);
			}

			y.setIzq(x);
			x.setPadre(y);

			y.setDer(z);
			z.setPadre(y);

		}

		// 4
		private void rotacion_doble_derecha_a_izquierda(NodoAVL<E> x, NodoAVL<E> y, NodoAVL<E> z) {
			System.out.println("rotacion_doble_derecha_a_izquierda");
			NodoAVL<E> t2 = null, t3 = null, nodoPadreX = x.getPadre();

			if (nodoPadreX != null) {
				if (nodoPadreX.getDer() == x) {
					nodoPadreX.setDer(z);
				}else {
					nodoPadreX.setIzq(z);
					}
			}else {
				raiz = z;
			}

			t2 = z.getIzq();
			t3 = z.getDer();
			if (t2 != null) {
				t2.setPadre(x);
			}
			x.setDer(t2);

			x.setPadre(z);
			z.setIzq(x);
			if (t3 != null) {
				t3.setPadre(y);
			}
			y.setIzq(t3);

			y.setPadre(z);
			z.setDer(y);

			z.setPadre(nodoPadreX);
		}

	private int max(int i, int j) {
		return i > j ? i : j;
	}
	private void expandir(NodoAVL<E> p, E item) {
		p.setRotulo(item);
		p.setAltura(1); // Le seteo la altura al dummy como 1 y le hago 2 hijos
		p.setIzq(new NodoAVL<E>(null, p));
		p.setDer(new NodoAVL<E>(null, p));

	}
	
	public E eliminar(E elem) {
		NodoAVL<E> tmpNodo = buscar(elem);
		E eliminado = null;
		if (tmpNodo.getRotulo() != null) {
			//Rotulo no nulo, hay posicion que eliminar		
			eliminado = tmpNodo.getRotulo();
			eliminarAux(tmpNodo);
			return eliminado;
		}else {
			return null;
		}
	}

	private void eliminarAux(NodoAVL<E> tmpNodo) {
		if (isExternal(tmpNodo)) {
			tmpNodo.setRotulo(null);
			tmpNodo.setIzq(null);
			tmpNodo.setDer(null);
		}else {
			if (SoloTieneHijoIzquierdo(tmpNodo)) {// hacemos bypass de hijo izq a padre
				if (tmpNodo.getPadre().getIzq() == tmpNodo) {
					tmpNodo.getPadre().setIzq(tmpNodo.getIzq());
				}else {
					tmpNodo.getPadre().setDer(tmpNodo.getIzq());
				}
			}else
				if (SoloTieneHijoDerecho(tmpNodo)) {
					if (tmpNodo.getPadre().getIzq() == tmpNodo) {
						tmpNodo.getPadre().setIzq(tmpNodo.getDer());
					}else {
						tmpNodo.getPadre().setDer(tmpNodo.getDer());
					}
				}else {
					tmpNodo.setRotulo(eliminarMinimo(tmpNodo.getDer()));
				}
		}
	}
	
	private boolean isExternal(NodoAVL<E> p) {
		return p.getIzq().getRotulo() == null && p.getDer().getRotulo() == null;
	}
	
	private boolean SoloTieneHijoIzquierdo(NodoAVL<E> p) {
		return p.getIzq().getRotulo() != null && p.getDer().getRotulo() == null;
	}
	
	private boolean SoloTieneHijoDerecho(NodoAVL<E> p) {
		return p.getIzq().getRotulo() == null && p.getDer().getRotulo() != null;
	}
	
	private E eliminarMinimo(NodoAVL<E> p) {
		E aRetornar = null;
		if (p.getIzq().getRotulo() == null) {
			aRetornar = p.getRotulo();
			if (p.getDer().getRotulo() == null) {
				p.setRotulo(null);
				p.setIzq(null);
				p.setDer(null);
			}else {
				p.getPadre().setDer(p.getDer());
				p.getDer().setPadre(p.getPadre());
			}
			return aRetornar;
		}else {
			return eliminarMinimo(p.getIzq());
		}
	}
	
	public NodoAVL<E> buscar(E e) {
		return buscarAux(e, raiz, null);
	}

	private NodoAVL<E> buscarAux(E element, NodoAVL<E> actual, NodoAVL<E> retorno) {
		int resultadoComparacion = 0;
		if (actual.getRotulo() == null)// llegue a un nodo dummy
			retorno = actual;
		else { // estoy en un nodo con un dato no nulo
			resultadoComparacion = comp.compare(element, actual.getRotulo());
			if (resultadoComparacion == 0) { // lo encontre porque element = actual.getElement()
				retorno = actual;
			}else {
				if (resultadoComparacion < 0){/* busco a la izquierda porque element < actual.getElement() */
					retorno = buscarAux(element, actual.getIzq(), retorno);
				}else{/* busco a la derecha porque element > actual.getElement() */
					retorno = buscarAux(element, actual.getDer(), retorno);
				}
			}
		}
		return retorno;
	}
}


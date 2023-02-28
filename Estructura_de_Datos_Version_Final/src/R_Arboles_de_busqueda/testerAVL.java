package R_Arboles_de_busqueda;

public class testerAVL<E> {

	public static void main(String[] args) {
		AVL<Integer> arbolAVL = new AVL<Integer>();
		
		
		
		arbolAVL.insert(45);
		arbolAVL.insert(96);
		arbolAVL.insert(23);
		arbolAVL.insert(7);
		arbolAVL.insert(2);
		arbolAVL.insert(38);
		arbolAVL.insert(1);
		arbolAVL.insert(7);
		arbolAVL.insert(32);
		arbolAVL.insert(47);
		arbolAVL.insert(44);
		arbolAVL.insert(52);
		arbolAVL.insert(47);
		arbolAVL.insert(46);
		arbolAVL.insert(48);
		arbolAVL.insert(70);
		arbolAVL.insert(65);
		
			System.out.println();
		System.out.println("Preorden: ");
			System.out.println();
			
			
			
		preOrden(arbolAVL.getRaiz());
		System.out.println();
	}
	
	private static void preOrden(NodoAVL<Integer> nodoActual) {
		if (nodoActual.getRotulo() != null) {
			if (nodoActual.getIzq().getRotulo() != null)
				preOrden(nodoActual.getIzq());

			if (!nodoActual.getEliminado())
				System.out.print(nodoActual.getRotulo() + "  ");

			if (nodoActual.getDer().getRotulo() != null)
				preOrden(nodoActual.getDer());
		}
	}
	
	
}

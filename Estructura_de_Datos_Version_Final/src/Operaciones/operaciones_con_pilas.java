package Operaciones;

import A_Excepciones.EmptyStackException;
import B_TDA_Pila.PilaEnlazada;
import B_TDA_Pila.Stack;

public class operaciones_con_pilas {
	public static void crear_y_mostrar() {
		Stack<Integer> pila = new PilaEnlazada<Integer>();
		
		//Inicializo la pila
		for(int pos = 1; pos <= 10; pos++) {
			pila.push(pos);
		}
		
		try {
			//Elimino e imprimo el elemento
			while(!pila.isEmpty()) {
				System.out.print(pila.pop()+"  ");
			}
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		crear_y_mostrar();
	}
}

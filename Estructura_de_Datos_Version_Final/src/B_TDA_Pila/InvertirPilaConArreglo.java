package B_TDA_Pila;

import Excepciones.EmptyStackException;

public class InvertirPilaConArreglo {
	
	public <E> void invertirPila(Stack<E> p) {
		Stack<E> Pila1 = new PilaArreglo<E>();
		Stack<E> Pila2 = new PilaArreglo<E>();
		pasar(p,Pila1);
		pasar(Pila1,Pila2);
		pasar(Pila2,p);
	} 
	//Pasa el contenido de Pila1 a Pila2
	private <E> void pasar (Stack<E> p1, Stack<E> p2) {
		try{
			while (!p1.isEmpty()) {
				p2.push(p1.pop());
			}
		}catch(EmptyStackException e) {
			System.out.println(e.getMessage());
		}
	}
}

package Operaciones;

import B_TDA_Pila.PilaEnlazada;
import B_TDA_Pila.Stack;
import C_TDA_Cola.ColaEnlazada;
import C_TDA_Cola.Queue;
import A_Excepciones.EmptyQueueException;
import A_Excepciones.EmptyStackException;

public class Ejercicio10 {
	public static boolean  validarCadena(String entrada) throws EmptyStackException, EmptyQueueException {
		boolean cumple = true;
		
		
		Queue<Character> col1 =  new ColaEnlazada<Character>();
		Queue<Character> col2 = new ColaEnlazada<Character>();
		Stack<Character> pilAux = new PilaEnlazada<Character>();
		Stack<Character> pil = new PilaEnlazada<Character>();
		
		for (int i = 0; i < entrada.length(); i++) {
			pil.push(entrada.charAt(i));
		}
		
		//Encolo  hasta la primer z
		while (pil.top() != 'z') {
				col1.enqueue(pil.pop());
		}
		
		pil.pop(); //Consumo z
	
		//Encolo segunda mitad
		while (pil.top() != 'x' ) {
			col2.enqueue(pil.pop());
		}
		
		if (!pil.isEmpty() && pil.top() == 'x') {pil.pop();} //consumo x
		
		while(!pil.isEmpty()) {
			if (pil.top() == 'z') {
				pil.pop();
			}else {
				pilAux.push(pil.pop());
			}
		}
		

		while(!col1.isEmpty() && cumple) {
			int resCompare = (col1.dequeue()).compareTo(pilAux.pop());
			if (resCompare != 0) {
				cumple = false;
			}
		}
			
		while(!col2.isEmpty() && cumple) {
			int resCompare = (col2.dequeue()).compareTo(pilAux.pop());
			if (resCompare != 0) {
				cumple = false;
			}
		}
		
		if (!pilAux.isEmpty() && !col1.isEmpty() && !col2.isEmpty()) {
			cumple = false;
		}else {
			cumple = true;
		}
		return cumple;
	}

	
	
	
	
	public static void main (String a[]){
		
		String entrada = "abzababxbabazba"; // Hasta aca resultado esperado TRUE
		// Hasta aca resultado esperado FALSE
		boolean resultado;
		try {
			resultado = validarCadena(entrada);
			System.out.println("La cadena "+entrada+" ¿Es valida?: "+resultado);
		} catch (EmptyStackException | EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	} 	
}
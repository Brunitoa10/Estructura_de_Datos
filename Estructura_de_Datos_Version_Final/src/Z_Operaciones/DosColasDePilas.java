package Z_Operaciones;

/*	
 	Sean Cin1 y Cin2 dos colas de pilas de caracteres cuyos elementos se encuentran ordenados de menor a mayor; 
	se	desea crear una nueva cola Cout que sea la unión de los elementos de Cin1 y Cin2. 
 	Los elementos de Cout deben	quedar ordenados de menor a mayor. 
 	Escriba un procedimiento en Java que reciba dos colas (Cin1 y Cin2) y resuelva
	el problema planteado exclusivamente en términos de las operaciones de los TDA Pila, TDA Cola y clase Character.
	Se considera que una pila P1 es menor que otra pila P2 cuando P1 tiene menos elementos que P2.
		a) Calcule el orden del tiempo de ejecución de su solución.
*/

import B_TDA_Pila.Stack;
import C_TDA_Cola.ColaEnlazada;
import C_TDA_Cola.Queue;
import A_Excepciones.EmptyQueueException;
import A_Excepciones.EmptyStackException;



public class DosColasDePilas{	
	 
	 public static Queue<Stack<Character>> ordenarPRO(Queue<Stack<Character>> cin1, Queue<Stack<Character>> cin2) throws EmptyQueueException{
		//HACER DE NUEVO
		 Queue<Stack<Character>> col = new ColaEnlazada<Stack<Character>>(); //dejo stack por convension de java
		 
		 while(!cin1.isEmpty() && !cin2.isEmpty()) {
			 try {
				int resCompare = ((cin1.front()).top()).compareTo((cin2.front().top()));
				if (resCompare == 0) {
					col.enqueue((cin1.dequeue()));
				}else {
					if (resCompare == -1) {
						col.enqueue((cin1.dequeue()));
					}else {
						col.enqueue((cin2.dequeue()));
					}
				}
			} catch (EmptyStackException | EmptyQueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		if (!cin1.isEmpty() && cin2.isEmpty()) {
			while(!cin1.isEmpty()) {
				col.enqueue((cin1.dequeue()));
			}
		}
		
		if(!cin2.isEmpty() && cin1.isEmpty()) {
			while (!cin2.isEmpty()) {
				col.enqueue((cin2.dequeue()));
			}
		}
		 return col;
	 }	 		 
}

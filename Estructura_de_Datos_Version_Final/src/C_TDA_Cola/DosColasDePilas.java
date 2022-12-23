package C_TDA_Cola;

import B_TDA_Pila.Stack;
import A_Excepciones.EmptyQueueException;
import A_Excepciones.EmptyStackException;



public class DosColasDePilas{	
	 
	 public static Queue<Stack<Character>> ordenarPRO(Queue<Stack<Character>> cin1, Queue<Stack<Character>> cin2) throws EmptyQueueException{
		//HACER DE NUEVO
		 Queue<Stack<Character>> Cout = new ColaEnlazada<Stack<Character>>(); //dejo stack por convension de java
		 
		 while(!cin1.isEmpty() && !cin2.isEmpty()) {
			 try {
				int resCompare = ((cin1.front()).top()).compareTo((cin2.front().top()));
				if (resCompare == 0) {
					Cout.enqueue((cin1.dequeue()));
				}else {
					if (resCompare == -1) {
						Cout.enqueue((cin1.dequeue()));
					}else {
						Cout.enqueue((cin2.dequeue()));
					}
				}
			} catch (EmptyStackException | EmptyQueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 }
		if (!cin1.isEmpty() && cin2.isEmpty()) {
			while(!cin1.isEmpty()) {
				Cout.enqueue((cin1.dequeue()));
			}
		}
		
		if(!cin2.isEmpty() && cin1.isEmpty()) {
			while (!cin2.isEmpty()) {
				Cout.enqueue((cin2.dequeue()));
			}
		}
		 return Cout;
	 }	 		 
}

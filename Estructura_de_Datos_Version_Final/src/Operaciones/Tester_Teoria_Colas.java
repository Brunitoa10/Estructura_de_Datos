package Operaciones;

import A_Excepciones.EmptyQueueException;
import C_TDA_Cola.ColaEnlazada;
import C_TDA_Cola.Queue;

public class Tester_Teoria_Colas {
	public static void main (String a[]) {
		try {
			Queue<Integer> q = new ColaEnlazada<Integer>();
			
			for(int i = 1; i <= 4; i++) {
				q.enqueue(i);
			}
			while (!q.isEmpty()) {
				System.out.print(q.dequeue()+" ");
			}
		}catch(EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
	}
}

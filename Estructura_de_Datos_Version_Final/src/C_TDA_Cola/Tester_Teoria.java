package C_TDA_Cola;

import Excepciones.EmptyQueueException;

public class Tester_Teoria {
	public static void main (String a[]) {
		try {
			Queue<Integer> q = new ColaEnlazada<Integer>();
			
			for(int i = 1; i <= 4; i++) {
				q.enqueue(i);
			}
			while (!q.isEmpty()) {
				System.out.println(q.dequeue());
			}
		}catch(EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
	}
}

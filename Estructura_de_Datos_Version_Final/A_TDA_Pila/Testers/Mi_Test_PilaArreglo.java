package Testers;

import Excepciones.EmptyStackException;
import Interfaces.Stack;
import Logica.PilaArreglo;

public class Mi_Test_PilaArreglo{
	public static void main (String a[]) {
		Stack<Integer> s = new PilaArreglo<Integer>();
		
		
		s.push(10);
		s.push(11);
		s.push(12);
		s.push(13);
		s.push(14);
		
		if(!s.isEmpty()) {
			try {
				System.out.println("Tope: "+s.top());
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		while(!s.isEmpty()) {
			try {
				System.out.println(s.pop());
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
}

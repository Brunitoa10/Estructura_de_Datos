
package B_TDA_Pila;

import java.util.Stack;

public class TestStackJava {

		public static void main (String a[]) {
			Stack<Integer> s = new Stack<Integer>();
			
			s.push(1);
			s.push(2);
			s.push(3);
			s.push(4);
			
			while (!s.empty()) {System.out.println("Elm: "+s.pop());}
			
			if(!s.empty()) {System.out.println("Tope: "+s.peek());}
			
			while (!s.empty()) {System.out.println("Elm: "+s.pop());}
		}
}

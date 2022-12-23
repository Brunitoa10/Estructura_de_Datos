package B_TDA_Pila;

import A_Excepciones.EmptyStackException;

public class TesterBuenaGente {
	public static void main (String a[]) {
		
		
			PilaArreglo<Integer> pA = new PilaArreglo<Integer>();
			PilaArreglo<Integer> pAAaux = new PilaArreglo<Integer>();
			PilaArreglo<Integer> pAAAaux = new PilaArreglo<Integer>();
			
			
			for(int i = 1; i <= 4; i++) {
				pA.push(i); //Arreglo
				pAAaux.push(i); //Arreglo
				pAAAaux.push(i); 
			}
			
			System.out.println("Verificando metodos PilaArreglo");
			
			 //Test isEmpty()
	        System.out.println("La pila esta vacia? "+pA.isEmpty());
	        System.out.println();

	        // Test pA.top() 
	        try {
	            System.out.print("El tope de la pila es: ");
	            System.out.println(pA.top());
	        } catch (EmptyStackException e) {
	            e.printStackTrace();
	        }
			
	        
	     // Test Muestro el contenido de la pila
			while (!pA.isEmpty()) {
				try {
					System.out.println("  |"+pA.pop()+"|");
					System.out.println("  ---");
				} catch (EmptyStackException e) {
					e.printStackTrace();
				}
			}
			
			
			System.out.println();
			System.out.println("Invirtiendo Pila");
			System.out.println();
			
	    // Test invertir contenido PILA
			pAAaux.invertir();
			 // Test pA.top() 
	        try {
	            System.out.print("El tope de la pila es: ");
	            System.out.println(pAAaux.top());
	        } catch (EmptyStackException e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println();
			
	        while (!pAAaux.isEmpty()) {
				try {
					System.out.println("  |"+pAAaux.pop()+"|");
					System.out.println("  ---");
				} catch (EmptyStackException e) {
					e.printStackTrace();
				}
			}
	        
	        // Test invertir contenido PILA
			pAAAaux.invertir();
			 // Test pA.top() 
	        try {
	            System.out.print("El tope de la pila es: ");
	            System.out.println(pAAAaux.top());
	        } catch (EmptyStackException e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println();
			
	        while (!pAAAaux.isEmpty()) {
				try {
					System.out.println("  |"+pAAAaux.pop()+"|");
					System.out.println("  ---");
				} catch (EmptyStackException e) {
					e.printStackTrace();
				}
			}
	        
	}
}

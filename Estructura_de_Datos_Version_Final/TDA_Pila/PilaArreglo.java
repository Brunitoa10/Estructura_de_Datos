package TDAPila;

import Excepciones.EmptyStackException;

public class PilaArreglo<E> implements Stack<E>
{
	//Atributos de clase
		//No tiene
	
	//Atributos de instancia
	
	protected E [] pA;
	protected int cantElem;
	
	//Constructor
	
	/**
	 * Constructor 1
	 * @return crea arreglo vacio
	 */
	
	@SuppressWarnings("unchecked")
	public PilaArreglo(int max) 
	{
		pA = (E[]) new Object[max]; 
		cantElem = -1;
	}
	
	/**
	 * Constructor 2
	 * @return Delega en constructor 1
	 * @return this invoca el constructor 1
	 */
	
	public PilaArreglo() {this(20);}
	
	//Comandos
	/**
	 * Push T(n) = O(1)
	 * @return Inserta item en el TOPE de la fila
	 * @return Si la pìla esta llena reporta ERROR
	 * @return Sino inserta 'e' en la posicion libre y incrementa longiutd
	 */
	public void push (E elem)
	{
		if( cantElem == pA.length - 1) 
		{
			expandirTamanioArreglo();
		}else {
			pA[++cantElem] = elem;
			//cantElem++;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void expandirTamanioArreglo() {
		E[] dobleEspacio = (E[]) new Object[pA.length * 2];
		
		for (int i = 0; i < pA.length; ++i) {
            dobleEspacio[i] = this.pA[i];
        }
        pA = (E[])dobleEspacio;
	}
	
	//Consultas
	public boolean isEmpty ()
	{
		return size() == 0;
	}	
	
	public E pop () throws EmptyStackException
	{
		E aux = null;
		E salida = null;
		
		if (isEmpty()) 
		{
			throw new EmptyStackException("Pila Vacia.");
		}else 
		{
			aux = pA[cantElem];
			pA[cantElem] = null;
			cantElem--;
			salida = aux;
		}
	 return salida;
	}//T(n) = O(1)
	
	public E top() throws EmptyStackException
	{
		E salida;
		if (isEmpty()) 
		{
			throw new EmptyStackException("Pila Vacia.");
		}else 
		{
			salida = pA[cantElem];
		}
		return salida;
	}
	
	public int size() {return cantElem + 1;}

	public void invertir() 
	{
		Stack<E> Pila1, Pila2;
		Pila1 = new PilaArreglo<E>();
		Pila2 = new PilaArreglo<E>();
		pasar(this,Pila1);
		pasar(Pila1,Pila2);
		pasar(Pila2,this);
	} 
	//Pasa el contenido de Pila1 a Pila2
	private void pasar (Stack<E> p1, Stack<E> p2) 
	{
		try{
			while (!p1.isEmpty()) 
			{
				p2.push(p1.pop());
			}
		}catch(EmptyStackException e) 
		{
			System.out.println(e.getMessage());;
		}
	}
	
	
}

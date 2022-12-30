package I_TDA_ArbolBinario;

public class BTNode<E> implements BTPosition<E>{
	
	//Atributos de instancia
	
	private E elem;
	private BTNode<E> rigth;
	private BTNode<E> left;
	private BTNode<E> parent;
	
	//Constructor
	public BTNode(E elem, BTNode<E> parent) {
		this.elem = elem;             //setElement(elem);
		this.parent = parent;		 //setParent(parent);
	}
	
	public BTNode(E e){
		this(e,null);
	}
	
	public E element() {
		return elem;
	}

	public void setElement(E element) {
		elem = element;
	}
	
	public BTPosition<E> getLeft() {
		return left;
	}
	
	public void setLeft(BTPosition<E> v) {
		left = (BTNode<E>) v;
	}
	
	public BTPosition<E> getRight() {
		return rigth;
	}
	
	public void setRight(BTPosition<E> v) {
		rigth = (BTNode<E>) v;
	}
	
	public BTPosition<E> getParent() {
		return parent;
	}
	
	public void setParent(BTPosition<E> v) {
		parent = (BTNode<E>) v;
	}
}

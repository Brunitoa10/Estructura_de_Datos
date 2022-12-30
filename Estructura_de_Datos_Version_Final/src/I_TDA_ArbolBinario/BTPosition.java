package I_TDA_ArbolBinario;

import D_TDA_Lista.Position;

public interface BTPosition<E> extends Position<E>{

	
	/**
	 * setElement
	 * @param E element
	 * @return Setea element
	 * */
	public void setElement(E element);

	/**
	 * getLeft
	 * @return Obtiene el elemento izquierdo
	 * */
	public BTPosition<E> getLeft();
	
	/**
	 * setLeft
	 * @param BTPosition v
	 * @return Setea el elemento izquierdo de v
	 * */
	public void setLeft(BTPosition<E> v);
	
	/**
	 * getRight
	 * @return Obtiene el elemento derecho de v
	 * */
	public BTPosition<E> getRight();
	
	/**
	 * setRight
	 * @param BTPosition v
	 * @return Setea el elemento derecho de v
	 * */
	public void setRight(BTPosition<E> v);

	/**
	 * getParent
	 * @return Obtiene el padre
	 * */
	public BTPosition<E> getParent();

	/**
	 * setParent
	 * @param BTPosition v
	 * @return Setea el padre de v
	 * */
	public void setParent(BTPosition<E> v);
}

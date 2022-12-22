package Logica;

public class DefaultComparator<E> implements java.util.Comparator<E>{
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) {
		return ((Comparable<E>)a).compareTo(b);
	}
}

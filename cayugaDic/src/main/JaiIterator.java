package main;

import java.util.*;

public class JaiIterator<E> implements Iterator<E> {
	private int limit;
	private int index = 0;
	Iterator <E> delegatee;
	
	public JaiIterator(Iterator<E> delegate, int lim){
		super();
		limit = lim;
		delegatee = delegate;
		
		ArrayList<Integer> elements = new ArrayList<>();
		elements.add(1);
		elements.iterator();
	}

	public boolean hasNext() {
		if(index < limit){
			return delegatee.hasNext();
		}
		else{
			return false;
		}
	}

	public E next() {
		if(index < limit){
			index++;
			return delegatee.next();
		}
		else{
			throw new NoSuchElementException();
		}
	}
	
}
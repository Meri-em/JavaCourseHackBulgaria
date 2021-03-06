package com.corejava.collections;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class MyBoundedQueue<E> extends ArrayDeque<E>{
	private int elementsCapacity;
	
	public MyBoundedQueue(int elementsCapacity) {
		this.elementsCapacity = elementsCapacity;
	}
	@Override
	public boolean offer(E e) {
		if (size() >= elementsCapacity) {
			super.poll();
		}
		return super.offer(e);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (E element: this) {
			sb.append(element + ", ");
		}
		return sb.substring(0, sb.length() - 2);
//		return sb.toString();
	}
}

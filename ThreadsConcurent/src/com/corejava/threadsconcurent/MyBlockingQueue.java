package com.corejava.threadsconcurent;

import java.util.ArrayDeque;

public class MyBlockingQueue<T> extends ArrayDeque<T> {
	private static final Object monitor = new Object();

	@Override
	public T poll() {
		if (isEmpty()) {
			synchronized (monitor) {
				System.out.println("The queue is empty. Waiting for an element to be added");
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		T element = super.poll();
		System.out.println(element + " is removed");
		return element;
	}

	@Override
	public boolean add(T e) {
		synchronized (monitor) {
			super.add(e);
			System.out.println(e + " is added");
			monitor.notify();
			return true;
		}
	}

	public static void main(String[] args) {
		MyBlockingQueue<Integer> bq = new MyBlockingQueue<>();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 9; i++ ) {
					bq.poll();
				}
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					bq.add(i);
				}
			}
		};

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

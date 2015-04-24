package com.corejava.threadsconcurent;

public class RaceConditionDemo {
	private static int counter = 0;
	
	public static void main(String[] args) throws InterruptedException{
		long begin = System.currentTimeMillis();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 2_000_000; i++) {
					counter++;
				}
			}
		};
		
		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 2_000_000; i++) {
					counter++;
				}
			}
		};
		
		long end = System.currentTimeMillis();
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("The counter is: " + counter);
		System.out.println("The execution time is " + (end - begin) + " milliseconds");
	}
}

package com.corejava.threadsconcurent;

public class IncrementSynchronizedMethod {
	private static long startTime = System.currentTimeMillis();
	private static int LENGTH = 2_000_000;
	private static int counter = 0;
	
	public static synchronized void increment() {
		System.out.println(Thread.currentThread().getName() + " " + counter++);
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
					for (int i = 0; i < LENGTH; i++) {
							increment();
				}
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
					for (int i = 0; i < LENGTH; i++) {
							increment();
				}
			}
		};

		t1.setName("T1");
		t2.setName("T2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println("The counter is: " + counter); //4_000_000
		System.out.println("The execution time is " + (System.currentTimeMillis() - startTime)
				+ " milliseconds"); //36_636 millisecond
	}
}

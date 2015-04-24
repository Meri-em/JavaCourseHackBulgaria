package com.corejava.threadsconcurent;

public class IncrementSynchronizedBlock {
	private static long startTime = System.currentTimeMillis();
	private static int LENGTH = 2_000_000;
	private static int counter = 0;
	public static final Object monitor = new Object();

	public static void increment() {
		System.out.println(Thread.currentThread().getName() + " " + counter++);
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < LENGTH; i++) {
					synchronized (monitor) {
						increment();
					}
				}
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < LENGTH; i++) {
					synchronized (monitor) {
						increment();
					}
				}
			}
		};

		t1.setName("T1");
		t2.setName("T2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("The counter is: " + counter); // 4_000_000
		System.out.println("The execution time is "
				+ (System.currentTimeMillis() - startTime) + " milliseconds"); // 40_663 millisecond
																			
	}
}

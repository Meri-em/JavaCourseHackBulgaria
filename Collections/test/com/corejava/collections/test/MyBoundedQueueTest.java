package com.corejava.collections.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.corejava.collections.MyBoundedQueue;

public class MyBoundedQueueTest {

	@Test
	public void test() {
		MyBoundedQueue<Integer> boundedQueue = new MyBoundedQueue<>(3);
		boundedQueue.offer(1);
		boundedQueue.offer(2);
		boundedQueue.offer(3);
		boundedQueue.offer(4);
		boundedQueue.offer(5);
		assertEquals("3, 4, 5", boundedQueue.toString());
	}

}

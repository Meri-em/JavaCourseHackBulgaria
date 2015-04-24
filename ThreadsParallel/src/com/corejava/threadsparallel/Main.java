package com.corejava.threadsparallel;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //all created threads write in syncmap
        Map<Point,Point> synmap = Collections.synchronizedMap(new HashMap<Point,Point>());
        Thread t1 = new Thread(new NearestPointRun(0, 20_000, synmap));
        Thread t2 = new Thread(new NearestPointRun(20_000, 40_000, synmap));
        Thread t3 = new Thread(new NearestPointRun(40_000, 60_000, synmap));
        Thread t4 = new Thread(new NearestPointRun(60_000, 80_000, synmap));
        Thread t5 = new Thread(new NearestPointRun(80_000, Points.POINTS_COUNT, synmap));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        //let all threads finish execution before finishing main thread
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        for(Entry<Point,Point> entry : synmap.entrySet()) {
          System.out.println(entry.getKey() + " " + entry.getValue());
      }
        long end = System.currentTimeMillis();
        System.out.println(end - start);// 5 threads 46_425 milliseconds
    }
}
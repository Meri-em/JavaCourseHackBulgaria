package com.corejava.threadsparallel;
import java.awt.Point;
import java.util.Map;


public class NearestPointRun implements Runnable{
    private int indexFrom;
    private int indexTo; 
    private Map<Point, Point> map;
    
    public NearestPointRun(int indexFrom, int indexTo, Map<Point, Point> map){
        this.indexFrom = indexFrom;
        this.indexTo = indexTo;
        this.map = map;
    }
    
    @Override
    public void run() {
        Points.doCalculations(Points.generatePoints(),indexFrom, indexTo, map);
    }

}
        
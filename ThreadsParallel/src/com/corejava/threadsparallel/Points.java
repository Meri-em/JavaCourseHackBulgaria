package com.corejava.threadsparallel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Points {
    public static final int POINTS_COUNT = 100_000;
    private static final int POINT_MIN_DISTANCE = 10_000;
    
    
    public static List<Point> generatePoints() {
        List<Point> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < POINTS_COUNT; i++) {
            list.add(new Point(random.nextInt(POINTS_COUNT), random.nextInt(POINTS_COUNT)));
        }
        return list;
    }

    public static void doCalculations(List<Point> generatedPoints, int indexFrom, int indexTo,
            Map<Point, Point> map) {
        for (int i = indexFrom; i < indexTo; i++) {
            double minDistance = POINT_MIN_DISTANCE;
            Point nearestPoint = null;
            for (Point y : generatedPoints) {
                double distance = Math.sqrt((generatedPoints.get(i).getX() - y.getX())
                        * (generatedPoints.get(i).getX() - y.getX())
                        + (generatedPoints.get(i).getY() - y.getY()) * (generatedPoints.get(i).getY() - y.getY()));
                if (distance < minDistance && distance != 0) {
                    minDistance = distance;
                    nearestPoint = y;

                }
            }
            map.put(generatedPoints.get(i), nearestPoint);
        }
        
    }
}

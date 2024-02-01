import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Brute force.
 * Write a program BruteCollinearPoints.java that examines 4 points at a
 * time and checks whether they all lie on the same line segment, returning
 * all such line segments. To check whether the 4 points p, q, r, and s are
 * collinear, check whether the three slopes between p and q, between p and
 * r, and between p and s are all equal.
 * <p>
 * The method segments() should include each line segment containing 4 points
 * exactly once. If 4 points appear on a line segment in the order p→q→r→s,
 * then you should include either the line segment p→s or s→p (but not both)
 * and you should not include subsegments such as p→r or q→r.
 * For simplicity, we will not supply any input to BruteCollinearPoints that
 * has 5 or more collinear points.
 * <p>
 * Corner cases. Throw an IllegalArgumentException if the argument to the
 * constructor is null, if any point in the array is null, or if the argument
 * to the constructor contains a repeated point.
 * <p>
 * Performance requirement. The order of growth of the running time of your
 * program should be n4 in the worst case, and it should use space proportional
 * to n plus the number of line segments returned.
 */

public class BruteCollinearPoints {
    private List<LineSegment> colinearSegments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Empty argument");
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("One of the points in the array is null");
            }
        }
        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Array contains a repeated point");
            }
        }
        int n = points.length;

        List<String> setSegments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Point p1 = points[i];
            for (int j = i + 1; j < n; j++) {
                Point p2 = points[j];
                voliateCheck(p1, p2);
                for (int m = j + 1; m < n; m++) {
                    Point p3 = points[m];
                    voliateCheck(p2, p3);
                    for (int k = m + 1; k < n; k++) {
                        Point p4 = points[k];
                        voliateCheck(p3, p4);
                        boolean isLine = isLineSegment(p1, p2, p3, p4);
                        if (isLine) {
                            boolean colFifth = false;
                            for (int t = 0; t < n; t++) {
                                Point p5 = points[t];
                                if ((p5.compareTo(p1) < 0 || p5.compareTo(p4) > 0) &&
                                        isLineSegment(p1, p2, p3, p5)) {
                                    colFifth = true;
                                }
                            }
                            if (!colFifth) {
                                String checkDuplicates = Integer.toString(i) + "-"
                                        + Integer.toString(k);
                                if (!setSegments.contains(checkDuplicates)) {
                                    LineSegment lineSeg = new LineSegment(p1, p2);
                                    colinearSegments.add(lineSeg);
                                    setSegments.add(checkDuplicates);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void voliateCheck(Point a, Point b) {
        if (a.compareTo(b) == 0) throw new IllegalArgumentException("Repeated points in the array");
    }


    private boolean isLineSegment(Point a, Point b, Point c, Point d) {
        if (a.slopeTo(b) == a.slopeTo(c)) {
            return a.slopeTo(b) == a.slopeTo(d);
        }
        return false;
    }


    // the number of line segments
    public int numberOfSegments() {
        return colinearSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[numberOfSegments()];
        segments = colinearSegments.toArray(segments);
        return segments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

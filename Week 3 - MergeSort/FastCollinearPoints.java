import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> foundSegments = new ArrayList<LineSegment>();
    
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Null Constructor");
        }
        
        int len = points.length;
        for (int i = 0; i < len; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Null Element");
        }
        
        if (len == 0) throw new IllegalArgumentException("No elements");
        
        // Check duplicates
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (pointsCopy[i].compareTo(pointsCopy[j]) == 0) throw new IllegalArgumentException("Duplicate Elements");
            }
        }
        
        for (int i = 0; i < len - 3; i++) {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            /*
             * System.out.println("This set has:");
            for (int j = 0; j < len; j++) {
                System.out.println(pointsCopy[j]);
            }
            */
            for (int p = 0, next = 1, last = 2; last < len; last++) {
                // System.out.println("P is: " + pointsCopy[p]);
                // System.out.println("Next is: " + pointsCopy[next]);
                // double slopePNext = pointsCopy[p].slopeTo(pointsCopy[next]);
                // System.out.println(slopePNext);
                // double slopePLast = pointsCopy[p].slopeTo(pointsCopy[last]);
                // System.out.println(slopePLast);
                while (last < len && pointsCopy[p].slopeTo(pointsCopy[next]) == pointsCopy[p].slopeTo(pointsCopy[last])) {
                    last++;
                    // System.out.println("Last now is: " + last);
                }
                // System.out.println("Last is: " + pointsCopy[last - 1]);
                if (last - next >= 3 && pointsCopy[p].compareTo(pointsCopy[next]) < 0) {
                    // System.out.println("Added:");
                    // System.out.println(pointsCopy[p]);
                    // System.out.println(pointsCopy[last-1]);
                    foundSegments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }
                next = last;
            }
        }
    }     // finds all line segments containing 4 or more points
    public int numberOfSegments() {
        return foundSegments.size();
    }       // the number of line segments
    public LineSegment[] segments() {
        return foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }                // the line segments
}
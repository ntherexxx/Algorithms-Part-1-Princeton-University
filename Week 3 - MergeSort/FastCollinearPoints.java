import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> foundSegments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        int len = points.length;

        if (len == 0) throw new IllegalArgumentException("No elements");
        // Check duplicates
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (points[i] == null) throw new IllegalArgumentException("Null Element");
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("Duplicate Elements");
            }
        }

        Point[] pointsCopy = points.clone();
        len = pointsCopy.length;

        for (int i = 0; i < len - 3; i++) {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());

            for (int p = 0, next = 1, last = 2; last < len; last++) {
                while (last < len && pointsCopy[p].slopeTo(pointsCopy[next]) == pointsCopy[p].slopeTo(pointsCopy[last])) {
                    last++;
                }
                if (last - next >= 3 && pointsCopy[p].compareTo(pointsCopy[last]) < 0) {
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

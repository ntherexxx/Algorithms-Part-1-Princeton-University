import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
  private final ArrayList<LineSegment> foundSegments = new ArrayList<LineSegment>();

  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Null Constructor");
    }
    int len = points.length;
    for (int i = 0; i < len; i++) {
      if (points[i] == null) throw new IllegalArgumentException("Null Element");
    }
    if (len == 0) throw new IllegalArgumentException("No elements");
    // Check duplicates
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("Duplicate Elements");
      }
    }

    Point[] pointsCopy = points.clone();

    Arrays.sort(pointsCopy);

    len = pointsCopy.length;
    // p q r s
    for (int p = 0; p < len - 3; p++) {
      for (int q = p + 1; q < len - 2; q++) {
        for (int r = q + 1; r < len - 1; r++) {
          for (int s = r + 1; s < len; s++) {
            double slopePQ = pointsCopy[p].slopeTo(pointsCopy[q]);
            double slopePR = pointsCopy[p].slopeTo(pointsCopy[r]);
            double slopePS = pointsCopy[p].slopeTo(pointsCopy[s]);
            if (Double.compare(slopePQ, slopePR) == 0 && Double.compare(slopePR, slopePS) == 0) {
              foundSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
            }
          }
        }
      }
    }
  }    // finds all line segments containing 4 points
  public int numberOfSegments() {
    return foundSegments.size();
  }       // the number of line segments
  public LineSegment[] segments() {
    return foundSegments.toArray(new LineSegment[foundSegments.size()]);
  }               // the line segments
}

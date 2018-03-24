import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
  int count = 0;
  ArrayList<LineSegment> foundSegments = new ArrayList<>();

  public BruteCollinearPoints(Point[] points) {
    int len = points.length();
    if (len == 0) throw new IllegalArgumentException("No elements");
    // Check duplicates
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        if (points[i].compareTo(points[j] == 0)) throw new IllegalArgumentException("Duplicate Elements");
      }
    }
    // p q r s
    for (int p = 0; p < len - 3; p++) {
      for (int q = p + 1; q < len - 2; q++) {
        for (int r = q + 1; r < len - 1; r++) {
          for (int s = r + 1; s < len; s++) {
            if(points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
               points[p].slopeTo(points[r]) == points[p].slopeTo(points[s])) {
                 count++;
                 foundSegments.add(new LineSegment(points[p], points[s]));
               }
          }
        }
      }
    }
  }    // finds all line segments containing 4 points
  public int numberOfSegments() {
    return count;
  }       // the number of line segments
  public LineSegment[] segments()                // the line segments
}

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private final int numTest;
  private final double[] testStat;
  private static final double FACTOR = 1.96;

  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
  {
    if (n <= 0) throw new IllegalArgumentException("n must be greated than 0.");
    if (trial <= 0) throw new IllegalArgumentException("trial must be greated than 0.");

    numTest = trials;
    testStat = new double[numTest];

    for (int i = 0; i < trials; i++)
    {
      Percolation test = new Percolation(n);
      while (!test.percolates())
      {
        int randomRow = StdRandom.uniform(1, n + 1);
        int randomCol = StdRandom.uniform(1, n + 1);
        if (!test.isOpen(randomRow, randomCol)) {
          test.open(randomRow, randomCol);
        }
      }
      int siteCount = test.numberOfOpenSites();

      testStat[i] = (double) siteCount/(n * n);
    }
  }
  public double mean() {
    return StdStats.mean(testStat);
  }                          // sample mean of percolation threshold
  public double stddev() {
    return StdStats.stddev(testStat);
  }                        // sample standard deviation of percolation threshold
  public double confidenceLo() {
    return mean() - ((FACTOR * stddev()) / Math.sqrt(numTest));
  }                  // low  endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((FACTOR * stddev()) / Math.sqrt(numTest));
  }                  // high endpoint of 95% confidence interval

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(n, trials);

    String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
    System.out.println("mean = " + ps.mean());
    System.out.println("StdDev = " + ps.stddev());
    System.out.println("95% confidence interval = " + confidence);
  }        // test client (described below)
}

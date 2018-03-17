import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
  private final int num_test;
  private final double[] test_stat;
  private static final double factor = 1.96;

  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
  {
    num_test = trials;
    test_stat = new double[num_test];

    for (int i = 0; i < trials; i++)
    {
      Percolation test = new Percolation(n);
      while(!test.percolates())
      {
        int random_row = StdRandom.uniform(1, n + 1);
        int random_col = StdRandom.uniform(1, n + 1);
        if (!test.isOpen(random_row, random_col)) {
          test.open(random_row, random_col);
        }
      }
      int siteCount = test.numberOfOpenSites();

      test_stat[i] = (double) siteCount/(n * n);
    }
  }
  public double mean() {
    return StdStats.mean(test_stat);
  }                          // sample mean of percolation threshold
  public double stddev() {
    return StdStats.stddev(test_stat);
  }                        // sample standard deviation of percolation threshold
  public double confidenceLo() {
    return mean() - ((factor * stddev()) / Math.sqrt(num_test));
  }                  // low  endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((factor * stddev()) / Math.sqrt(num_test));
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

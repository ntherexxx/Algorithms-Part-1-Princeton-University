public class PercolationStats {
  private Percolation test;
  private int num_test;
  private double[] test_stat;

  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
  {
    num_test = trials;

    for(int i = 0; i < trials; i++)
    {
      test = new Percolation(n);
      while(!test.percolates())
      {
        
      }
    }
  }
  public double mean()                          // sample mean of percolation threshold
  public double stddev()                        // sample standard deviation of percolation threshold
  public double confidenceLo()                  // low  endpoint of 95% confidence interval
  public double confidenceHi()                  // high endpoint of 95% confidence interval

  public static void main(String[] args)        // test client (described below)
}

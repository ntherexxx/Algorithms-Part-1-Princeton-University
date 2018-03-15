import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private WeightedQuickUnionUF grid;
  private boolean[][] open_site;
  private int virtual_top = 0;
  private int virtual_bot;
  private int N;

  public Percolation(int n){
    if(n <= 0) throw new IllegalArgumentException("n must be greated than 0.");

    //0 is top and 1 - 25 is grid, 26 is bot
    //n = 5, grid = 0 - 24 + 2 = 0 - 26

    //n = 5
    //size = 25
    N = n;
    size = n ^ 2;
    grid = new WeightedQuickUnionUF(size + 2);
    virtual_bot = size + 1; // 26

    //Connect to virtual top
    for(int i = 1; i <=n; i++){
      grid.union(virtual_top, i);
      System.out.println("Connecting virtual top with " + i);
    }
    for(int i = ((n - 1) * n + 1); i <= size; i++){
      grid.union(virtual_bot, i);
      System.out.println("Connecting virtual bot with " + i);
    }

    open_site = new boolean[n][n];

    System.out.println("Creating Grid Finished.");
  }
  // create n-by-n grid, with all sites blocked

  public void open(int row, int col){
    if(row < 1 || row > n || col < 1 || col > n) throw new IllegalArgumentException("open have illegal index.");

    int pos = (row - 1) * N + col;

    int up = (row - 2) * N + col;
    int down = (row) * N + col;
    int left = pos - 1;
    int right = pos + 1;

    open_site[row - 1][col - 1] = true;


    System.out.println("Opening " + row + ' ' + col);
    //Open 3 2
    //In fact open grid[2][1]
  }
  // open site (row, col) if it is not open already

  public boolean isOpen(int row, int col){
    if(row < 1 || row > n || col < 1 || col > n) return false;
    else return open_site[row - 1][col - 1];
  }
  // is site (row, col) open?


  public boolean isFull(int row, int col){
    int pos = (row - 1) * N + col;
    return grid.connected(virtual_top, pos);
    //Full: connected to top row
  }
  // is site (row, col) full?


  public int numberOfOpenSites()
  // number of open sites


  public boolean percolates()
  // does the system percolate?



  public static void main(String[] args)
  // test client (optional)


}

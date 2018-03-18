import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF grid;
  private boolean[][] openSite;
  private final int VIRTUAL_BOT;
  private final int numElement;
  private int numOfOpenSites;
  private static final int VIRTUAL_TOP = 0;

  public Percolation(int n) {
    int size = n * n;
    if (n <= 0) throw new IllegalArgumentException("n must be greated than 0.");

    // 0 is top and 1 - 25 is grid, 26 is bot
    // n = 5, grid = 0 - 24 + 2 = 0 - 26

    // n = 5
    // size = 25
    numElement = n;
    grid = new WeightedQuickUnionUF(size + 2);
    VIRTUAL_BOT = size + 1; //  26

    /*
    // Connect to virtual top
    for (int i = 1; i <=n; i++) {
      grid.union(VIRTUAL_TOP, i);
      // System.out.println("Connecting virtual top with " + i);
    }
    // System.out.println(n);
    // System.out.println(size);
    // System.out.println(((n - 1) * n + 1));
    // Connect to virtual bot
    for (int j = ((n - 1) * n + 1); j <= size; j++) {
      grid.union(VIRTUAL_BOT, j);
      // System.out.println("Connecting virtual bot with " + j);
    }
    */

    openSite = new boolean[n][n];

    // System.out.println("Creating Grid Finished.");
  }
  //  create n-by-n grid, with all sites blocked

  private int getIndex(int row, int col) {
    return (row - 1) * numElement + col;
  }

  public void open(int row, int col) {
    if (row < 1 || row > numElement || col < 1 || col > numElement)
      throw new IllegalArgumentException("open have illegal index.");

    int pos = getIndex(row, col);

    if (row == 1) {
      grid.union(VIRTUAL_TOP, pos);
    }

    if (row == numElement) {
      grid.union(VIRTUAL_BOT, pos);
    }

    int up = (row - 2) * numElement + col;
    int down = (row) * numElement + col;
    int left = pos - 1;
    int right = pos + 1;

    if (!openSite[row - 1][col - 1]) {
        numOfOpenSites++;
        openSite[row - 1][col - 1] = true;
    }
    // Connect to adjacent blocks

    if (col > 1 && isOpen(row, col - 1)) grid.union(pos, left);
    if (col < numElement && isOpen(row, col + 1)) grid.union(pos, right);
    if (row > 1 && isOpen(row - 1, col)) grid.union(pos, up);
    if (row < numElement && isOpen(row + 1, col)) grid.union(pos, down);

    // System.out.println("Opening " + row + ' ' + col);
    // Open 3 2
    // In fact open grid[2][1]
  }
  //  open site (row, col) if it is not open already

  public boolean isOpen(int row, int col) {
    if (row >= 1 && row <= numElement && col >= 1 && col <= numElement) {
      return openSite[row - 1][col - 1];
    }
    else throw new IllegalArgumentException("isOpen have illegal index.");
  }
  //  is site (row, col) open?


  public boolean isFull(int row, int col) {
    int pos = getIndex(row, col);
    if (row >= 1 && row <= numElement && col >= 1 && col <= numElement) {
      for (int i = 1; i <= numElement; i++) {
        if (grid.connected(pos, i)) return true;
      }
    }
    else throw new IllegalArgumentException("isFull have illegal index.");
    // Full: connected to top row
  }
  //  is site (row, col) full?


  public int numberOfOpenSites() {
    return numOfOpenSites;
  }
  //  number of open sites

  public boolean percolates() {
    return grid.connected(VIRTUAL_TOP, VIRTUAL_BOT);
  }
  //  does the system percolate?


  public static void main(String[] args) {
    //  test client (optional)
  }
}

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF grid;
  private boolean[][] openSite;
  private static final int VIRTUAL_TOP = 0;
  private final int virtualBot;
  private final int numElement;
  private int numOfOpenSites;

  public Percolation(int n) {
    int size = n * n;
    if (n <= 0) throw new IllegalArgumentException("n must be greated than 0.");

    // 0 is top and 1 - 25 is grid, 26 is bot
    // n = 5, grid = 0 - 24 + 2 = 0 - 26

    // n = 5
    // size = 25
    numElement = n;
    grid = new WeightedQuickUnionUF(size + 2);
    virtualBot = size + 1; //  26

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
      grid.union(virtualBot, j);
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
    else if (row == numElement) {
      grid.union(virtualBot, pos);
    }

    int up = (row - 2) * numElement + col;
    int down = (row) * numElement + col;
    int left = pos - 1;
    int right = pos + 1;

    openSite[row - 1][col - 1] = true;
    numOfOpenSites++;

    // Connect to adjacent blocks
    if (isOpen(row, col - 1)) grid.union(pos, left);
    if (isOpen(row, col + 1)) grid.union(pos, right);
    if (isOpen(row - 1, col)) grid.union(pos, up);
    if (isOpen(row + 1, col)) grid.union(pos, down);

    // System.out.println("Opening " + row + ' ' + col);
    // Open 3 2
    // In fact open grid[2][1]
  }
  //  open site (row, col) if it is not open already

  public boolean isOpen(int row, int col) {
    if (row < 0 || row > (numElement + 1) || col < 0 || col > (numElement + 1)) {
      throw new IllegalArgumentException("isOpen have illegal index.");
    }
    else if (row == 0 || row == (numElement + 1) || col == 0 || col == (numElement + 1)) {
        return false;
    }
    return openSite[row - 1][col - 1];
  }
  //  is site (row, col) open?


  public boolean isFull(int row, int col) {
    int pos = getIndex(row, col);
    if (row < 0 || row > (numElement + 1) || col < 0 || col > (numElement + 1)) {
      throw new IllegalArgumentException("isFull have illegal index.");
    }
    else return grid.connected(VIRTUAL_TOP, pos);
    // Full: connected to top row
  }
  //  is site (row, col) full?


  public int numberOfOpenSites() {
    return numOfOpenSites;
  }
  //  number of open sites

  public boolean percolates() {
    return grid.connected(VIRTUAL_TOP, virtualBot);
  }
  //  does the system percolate?


  public static void main(String[] args) {
    //  test client (optional)
  }
}

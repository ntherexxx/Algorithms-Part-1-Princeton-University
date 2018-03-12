import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private WeightedQuickUnionUF grid;

  public Percolation(int n){


    System.out.println("Creating Grid...");
  }
  // create n-by-n grid, with all sites blocked

  public void open(int row, int col){
    int row_num = row - 1;
    int col_num = col - 1;
    grid[row_num][col_num] = true;

    System.out.println("Opening " + row + ' ' + col);
    //Open 3 2
    //In fact open grid[2][1]
  }
  // open site (row, col) if it is not open already

  public boolean isOpen(int row, int col){
    return grid[row-1][col-1];
  }
  // is site (row, col) open?


  public boolean isFull(int row, int col){
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

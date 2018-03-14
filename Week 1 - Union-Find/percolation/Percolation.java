import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private WeightedQuickUnionUF grid;
  private int size;
  private boolean[] open_site;

  public Percolation(int n){
    //e.g. n = 5, size = 5 * 5 = 25, but need 2 more nodes
    //one top one bot. 0-24 normal nodes, 25 TOP 26 BOT
    size = n;

    grid = new WeightedQuickUnionUF(n + 2);
    open_site = new boolean[size * size];

    System.out.println("Creating Grid...");
    //Top/Bot
    int top = size ^ 2; //25
    int bot = size ^ 2 + 1; //26
    int first_num_bot_line = (size - 1) * size;

    for(int i = 0; i < n; i++){
      grid.union(top, i);
      grid.union(bot, first_num_bot_line + i);
    }
    System.out.println("Connecting to Virtual Top/Bot...");
  }
  // create n-by-n grid, with all sites blocked

  public void open(int row, int col){
    //Mark it as open
    int target = (row - 1) * size + (col - 1);
    open_site[target] = true;
    //Connect to adjacent open sites
    if(isOpen(row, col - 1)) grid.union(target, target - 1);
    if(isOpen(row, col + 1)) grid.union(target, target + 1);
    if(isOpen(row - 1, col)) grid.union(target, target - size);
    if(isOpen(row + 1, col)) grid.union(target, target + size);

    System.out.println("Opening " + row + ' ' + col);
    //Open 3 2
    //In fact open grid[2][1]
  }
  // open site (row, col) if it is not open already

  public boolean isOpen(int row, int col){
    int target = (row - 1) * size + (col - 1);
    return open_site[target];
  }
  // is site (row, col) open?


  public boolean isFull(int row, int col){
    //Full: connected to top row
    int target = (row - 1) * size + (col - 1);
    for(int i = 0; i < size; i++){
      if(grid.connected(i, target)) return true;
    }
    return false;
  }
  // is site (row, col) full?


  public int numberOfOpenSites(){
    int count = 0;
    for(int i = 0; i < (size ^ 2); i++){
      if(open_site[i]) count++;
    }
    return count;
  }
  // number of open sites


  public boolean percolates(){
    int top = size ^ 2;
    int bot = size ^ 2 + 1;
    return grid.connected(top, bot);
  }
  // does the system percolate?



  public static void main(String[] args){}
  // test client (optional)


}

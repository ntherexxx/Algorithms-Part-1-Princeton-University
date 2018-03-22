import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    // @SuppressWarnings("unchecked")
    public static void main(String[] args) {  
        int num = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            String tmp = StdIn.readString();
            rq.enqueue(tmp);
        }
        
        for (int i = 0; i < num; i++) {
            System.out.println(rq.dequeue());
        }
    }
}

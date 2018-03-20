import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {  
        int num = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        Iterator<String> itr = rq.iterator();
        
        for (int i = 0; i < num; i++) {
            String tmp = StdIn.readString();
            rq.enqueue(tmp);
        }
        
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabio
 * Look at page 39 of the book by Robert Sedgwik, 4th edition to see how to 
 * run this and pipeline a set of characters
 */
public class Subset {
    
    public static void main(String[] args) {
        int N;  
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();       
                
        while (!StdIn.isEmpty()) {
            String  s = StdIn.readString();  
            queue.enqueue(s);                      
        }         
        Iterator<String> it = queue.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(it.next());
        }                      
    }  
}

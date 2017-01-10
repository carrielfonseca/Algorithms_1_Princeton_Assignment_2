
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabio
 */
public class RandomizedQueue<Item> implements Iterable<Item> {    
    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot
    public RandomizedQueue()      {           // construct an empty randomized queue
       q = (Item[]) new Object[2];
       n = 0;
       first = 0;
       last = 0;       
    }
    
    public boolean isEmpty()     {            // is the queue empty?
       return n ==0;
    }
   
   public int size()      {                  // return the number of items on the queue
       return n;
   }
   
   // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = n;
    }
   
    public void enqueue(Item item)         {  // add the item
       // double size of array if necessary and recopy to front of array
        if (item == null) throw new java.lang.NullPointerException("null items not accepted");
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        //if (last == q.length) last = 0;          // no need of this part in the randomized queue
        n++;
    }   
    
   public Item dequeue()        {            // remove and return a random item
       if (isEmpty()) throw new NoSuchElementException("Queue underflow");
       int rand = StdRandom.uniform(n);
       Item itemChosen = q[rand];
       q[rand] = null;
       if (rand != (n-1)) {
          q[rand] = q[n-1];
          q[n-1] = null; // to avoid loitering
       }
       n--; 
       last--;
       // shrink size of array if necessary
        if (n > 0 && n == q.length/4) resize(q.length/2); 
       return itemChosen;
    }
   
   public Item sample()          {           // return (but do not remove) a random item
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      int rand = StdRandom.uniform(n);
      Item itemChosen = q[rand];
      return itemChosen;
   }
   
   public Iterator<Item> iterator()     {    // return an independent iterator over items in random order
       return new ArrayIterator();      
   }   
   
    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        Item[] copyOfQ = (Item[]) new Object[n];        
    
        public ArrayIterator() {
            for (int j = 0; j < n; j++) {
            copyOfQ[j] = q[j];
            }
            StdRandom.shuffle(copyOfQ);
        }
        
        public boolean hasNext()  { 
            return i < n;            
        }
        
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = copyOfQ[i];
            i++;
            return item;
        }
    }
   
   public static void main(String[] args)  { // unit testing  
       RandomizedQueue<Integer> rq = new RandomizedQueue<>();
      rq.enqueue(252);
         rq.enqueue(163);
         rq.enqueue(223);
         rq.sample()     ;// ==> 252
         rq.enqueue(876);
         rq.size()       ; //==> 4
         rq.dequeue()     ;//==> 252
         rq.enqueue(192);       
   }
}
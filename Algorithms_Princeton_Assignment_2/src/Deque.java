
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
public class Deque<Item> implements Iterable<Item> {

    private int n;          // size of the deque
    private Node first;     // top of deque  
    private Node last;      // bottom of the deque  

     // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    } 
    
    public Deque()        {                   // construct an empty deque 
        first = null;
        last  = null;
        n = 0;
    }
            
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return n;
    }
    
    public void addFirst(Item item) {  // add the item to the front
        if (item == null) throw new java.lang.NullPointerException("null items not accepted");
        Node oldfirst = first;          
        first = new Node();
        if (n != 0) {   //necessary this condition when doing operations on previous nodes
            oldfirst.next = first;            
        }
        else {
            last = first;
        }
        first.item = item;
        first.previous = oldfirst;         
        n++;
    }
    
    public void addLast(Item item)     {      // add the item to the end
        if (item == null) throw new java.lang.NullPointerException("null items not accepted");
        Node oldlast = last;          
        last = new Node();
        if (n != 0) {   //necessary this condition when doing operations on previous nodes
            oldlast.previous = last;
        }
        else {
            first = last;
        }
        last.item = item;
        last.next = oldlast;         
        n++;       
    }
    
    public Item removeFirst()  {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        Node newfirst = first.previous;
        first = newfirst;   
        if (n > 1) {
            newfirst.next = null;
        }   
        else {
            last = first;
        }
        n--;
        return item;
    }
    
    public Item removeLast()  {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        Node newlast = last.next;
       // first = null;  // should I release the memory here?
        last  = newlast;  
        if (n > 1) {
           newlast.previous = null;
        }
        else {
            first = last;
        }
        n--;
        return item;
    }
    
     /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.previous; 
            return item;
        }
    }

    
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
       
        
         deque.addFirst(0);
       
         
         
         System.out.println(deque.removeFirst());
        
        
        
        
        
        
        
        
        
        
        /*dq.addFirst("Fabio");
       dq.addFirst("Mamae");
       dq.addFirst("Papai");
       dq.addFirst("Renan");
       
       Iterator<String>  it = dq.iterator();
       
       while(it.hasNext()) {
           System.out.println(it.next());
       }
       
       Iterator<String> it2 = dq.iterator();
       
       while(it2.hasNext()) {
           System.out.println(it2
                   .next());
       }
       */
       
              
       
    }
    
    
    
}

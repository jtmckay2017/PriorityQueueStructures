//
//implement PQ using Binomial Min Heap
//https://en.wikipedia.org/wiki/Binomial_heap
//

public class BinomialHeapPQ<T, P extends Comparable<P>> implements PriorityQueue<T,P>
{
  private int currSize = 0;
  
  
  @SuppressWarnings("unchecked")
  private Node<T,P> head = null;
  
  /**
   * constructor
   */
  public BinomialHeapPQ()
  {
  }
  
  /**
   * Private constructor to use allow use of merge on insert.
   */
  private BinomialHeapPQ(Node<T,P> node){
    this.head = node;
  }
  
  /**
   * add the given value using the provided priority
   */
  public void enqueue(T value, P priority)
  {
    
    Node<T,P> temp = new Node<T,P>(value, priority);
    if(head ==null){head=temp;}else{
      head = unionBiHeap(head, temp);
    }
    currSize++;
  }
  
  /**
   * remove the value with the highest priority
   * (i.e. smallest priority value)
   */
  public T dequeue()
  {
    if(head==null){return null;}
    Node<T,P> min = findMin();
    Node<T,P> x = head;
    Node<T,P> prev_x = null;
    while(x.getPriority() != min.getPriority()){
      prev_x = x;
      x = x.sibling;
    }
    if(prev_x==null){//if smallest is first root in heap just set head to sibling of x
      head = x.sibling;
    }
    else{//if smallest is not first root in heap. remove the link to x and set prev_x to x.sibling
      prev_x.sibling = x.sibling;
    }
    x=x.child;
    Node<T,P> old = x;
    
    while(x != null){
      x.parent = null;
      x = x.sibling;
    }
    if((head==null)&&(old==null)){
      //empty
    }
    else{
      if((head==null) && (old!=null)){
        head = old.reverse(null);
      }else{
        if((head!=null)&&(old==null)){
          //do nothing
        }else{
          head = unionBiHeap(head, old.reverse(null));
        }
      }
    }
    currSize--;
    return min.getData();
  }

  
  private Node<T,P> mergeBiHeap(Node<T,P> h1, Node<T,P> h2){
    Node<T,P> temp1 = h1, temp2 = h2;
    while((temp1 != null) && (temp2 != null)){
      if(temp1.degree == temp2.degree){
        Node<T,P> tmp = temp2;
        temp2 = temp2.sibling;
        tmp.sibling = temp1.sibling;
        temp1.sibling = tmp;
        temp1 = tmp.sibling;
      } else {
        if(temp1.degree < temp2.degree){
          if((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)){
            Node<T,P> tmp = temp2;
            temp2 = temp2.sibling;
            tmp.sibling = temp1.sibling;
            temp1.sibling = tmp;
            temp1 = tmp.sibling;
          } else {
            temp1 = temp1.sibling;
          }
        } else {
          Node<T,P> tmp = temp1;
          temp1 = temp2;
          temp2 = temp2.sibling;
          temp1.sibling = tmp;
          if (tmp == head){
            head = temp1; //this is weird one
          }
        }
      }      
    }
    if(temp1 == null){
      temp1 = head;
      while(temp1.sibling != null){
        temp1 = temp1.sibling;
      }
      temp1.sibling = temp2;
    }
    return head;
    
  }
  
  /**
   * unionBiHeap | union two binomial heaps
   */
  private Node<T,P> unionBiHeap(Node<T,P> h1, Node<T,P> h2){
    Node<T,P> H = null;
    H = mergeBiHeap(h1, h2);
    if(H==null){return H;}
    Node<T,P> prev_x=null;
    Node<T,P> x = H;
    Node<T,P> next_x = x.sibling;
    while(next_x != null){
      if((x.degree != next_x.degree) || (next_x.sibling != null && next_x.sibling.degree == x.degree)){
        prev_x = x;
        x = next_x;
      } else {
        if(x.getPriority().compareTo(next_x.getPriority()) <= 0) {
          x.sibling = next_x.sibling;
          linkBiTrees(next_x, x);
        } else {
          if(prev_x == null){
            H = next_x;
          } else {
            prev_x.sibling = next_x;
          }
          linkBiTrees(x, next_x);
          x = next_x;
          
        }
      }
      next_x = x.sibling;
    }
    return H;
  }
    
  /**
   * link two binomial trees and return root
   */
  private Node<T,P> linkBiTrees(Node<T,P> y, Node<T,P> z){
    if(y.getPriority().compareTo(z.getPriority()) > 0){
      Node<T,P> temp = y;
      y = z;
      z = temp;
    }
    z.parent = y;
    z.sibling = y.child;
    y.child = z;
    y.degree += 1;
    return y;
  }
  
  /**
   * Find min
   */
  private Node<T,P> findMin(){
    Node<T,P> y = head;
    Node<T,P> x = head;
    while(x!=null){
      if(x.getPriority().compareTo(y.getPriority()) < 0){
        y = x;
      }
      x=x.sibling;
    }
    return y;
  }
  /**
   * return the value of the element with highest priority
   * (i.e. smallest priority value)
   */
  public T peek()
  {
    return findMin().getData();
  }
  
  /**
   * return the priority of the element with highest priority
   * (i.e. smallest priority value)
   */
  public P peekPriority()
  {
    return findMin().getPriority();
  }
  
  /**
   * remove everything in the priority queue - Done
   */
  public void clear()
  {
    head = null;
    currSize=0;
  }
  
  /**
   * merge two priority queues into one and return the merged priority queue
   */
  @SuppressWarnings("unchecked")
  public BinomialHeapPQ  merge(BinomialHeapPQ other)
  {
    int size = this.size()+other.size();
    Node<T,P> newHead = unionBiHeap(this.head, other.head);
    BinomialHeapPQ newHeap = new BinomialHeapPQ(newHead);
    newHeap.currSize = size;
    return newHeap;
  }
  
  /**
   * return the size of the given priority queue - Done
   */
  public int size()
  {
    return currSize;
  }
  
  /**
   * Helper method check if empty
   */
  private boolean isEmpty(){
    return currSize==0;
  }
  
  

  
  
  
  
  /**
   *MyGenericNode Class
   */
  private static class Node<T,P> {
    Node(T data, P priority){
      this.data = data;
      this.priority = priority;
    }
    
    private T data;
    private P priority;
    
    public int degree=0;;
    
    public Node<T,P> parent = null;
    public Node<T,P> sibling = null;
    public Node<T,P> child = null;
    
    
    
    public T getData(){
      return data;
    }
    
    public P getPriority(){
      return priority;
    }
    
    public Node<T,P> reverse(Node<T,P> node) 
    {
            Node<T,P> rev;
            if (sibling != null)
                rev = sibling.reverse(this);
            else
                rev = this;
            sibling = node;
            return rev;
    }
  }
  

  //==================================================================
  // do not modify anything below
  //==================================================================
  //merge two priority queues into one and return the merged priority queue
  public PriorityQueue  merge(PriorityQueue other)
  {
    return merge((BinomialHeapPQ)other);
  }
}

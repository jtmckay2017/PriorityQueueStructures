//implementing Priority Queue using a Dynamic Array that doubles the capacity everytime the array is full
public class DynamicArrayPQ<T, P extends Comparable<P>> implements PriorityQueue<T,P>
{
  /**
   * Size
   */
  private int currSize = 0;
  
  /**
   * Array   
   */
  @SuppressWarnings("unchecked")
  private Node<T,P>[] pArray = new Node[10];
  
  
  /**
   * constructor
   */
  public DynamicArrayPQ()
  {
    
  }
  
  /**
   * dd the given value using the provided priority -Done [Working]
   */
  public void enqueue(T value, P priority)
  {
    @SuppressWarnings("unchecked")
    Node<T,P> tempN = new Node(value, priority);
    if(currSize == pArray.length){
      @SuppressWarnings("unchecked")
      Node<T,P>[] temp = new Node[pArray.length*2]; //Create temp variable
      System.arraycopy(pArray, 0, temp, 0, currSize); //Copy of array into temp
      pArray = temp; //Set pArray to temp
    }
    int i=currSize;
    
    for(; i>0; i--){         //Array is not full so add new element at current size
      if(tempN.getPriority().compareTo(pArray[i-1].getPriority()) >= 0) {
        break;
      }
      pArray[i] = pArray[i-1];
    }
    pArray[i] = tempN;
    
    //System.out.println("Adding new element in Dynamic ArrayPQ with data: " + tempN.getData().toString() + " | priority: " + tempN.getPriority().toString());
    currSize++;
  }
  
  //remove the value with the highest priority -Done [Working]
  //(i.e. smallest priority value)
  public T dequeue()
  {
    if(currSize==0){return null;} //Queue is empty;
    
    //Get the deleted element
    T removedElem = peek();
    @SuppressWarnings("unchecked")
    Node<T,P>[] temp = new Node[pArray.length]; //Create temp variable
    System.arraycopy(pArray, 1, temp, 0, currSize-1); //Copy of array into temp
    pArray = temp; //Set pArray to temp
    
    //Decrement Size
    currSize--;
    return removedElem;
  }
  
  //return the value of the element with highest priority -Done [Working]
  //(i.e. smallest priority value)
  public T peek()
  {
    if(currSize==0){return null;}
    return pArray[0].getData();
  }
  
  //return the priority of the element with highest priority -Done [Working]
  //(i.e. smallest priority value)
  public P peekPriority()
  {
    if(currSize==0){return null;}
    return pArray[0].getPriority();
  }
  
  //remove everything in the priority queue -Done -Done [Working]
  public void clear()
  {
    @SuppressWarnings("unchecked")
    Node<T,P>[] temp = new Node[10];
    pArray = temp;
    currSize = 0;
  }
  
  //merge two priority queues into one and return the merged priority queue -Done [Working]
  @SuppressWarnings("unchecked")
  public DynamicArrayPQ  merge(DynamicArrayPQ other)
  {  
    DynamicArrayPQ<T,P> merged = new DynamicArrayPQ();
    int iM=other.size();
    int jM=size();
    for(int i=0; i<iM; i++){
      merged.enqueue((T)other.peek(), (P)other.peekPriority());
      other.dequeue();
    }
    for(int i=0; i<jM; i++){
      merged.enqueue((T)this.peek(), (P)this.peekPriority());
      this.dequeue();
    }
    return merged;
  }
  
  //return the size of the given priority queue -Done [Working]
  public int size()
  {
    return currSize;
  }
  
  //Method to pring out and test  contents
  private void toS(){
    for(int i=0; i<currSize; i++){
      System.out.println(pArray[i].getData() +" | P: " + pArray[i].getPriority());
    }
  }
  
  //MyGenericNode Class
  private static class Node<T,P> {
    Node(T data, P priority){
      this.data = data;
      this.priority = priority;
    }
    
    private T data;
    private P priority;
    
    public T getData(){
      return data;
    }
    
    public P getPriority(){
      return priority;
    }
  }

  
  
  
  //==================================================================
  // do not modify anything below
  //==================================================================
  //merge two priority queues into one and return the merged priority queue
  public PriorityQueue  merge(PriorityQueue other)
  {
    return merge((DynamicArrayPQ)other);
  }
}

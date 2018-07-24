//implement K-ary min heap, for K>=2
public class KaryHeapPQ<T, P extends Comparable<P>> implements PriorityQueue<T,P>
{
  /**
   * Number of elements in array.
   */
  private int currSize = 0;
  
  /**
   * Array for storing elements. 
   */
  @SuppressWarnings("unchecked")
  private Node<T,P>[] pArray = new Node[10];
  
  /**
   * K size of "tree"
   */
  private int k;
  
  
  /**
   * Constructor
   * @param K number of branches.
   */
  public KaryHeapPQ(int K)
  {
    this.k = K;
  }
  
  /**
   * Percolate down used when dequeueing.
   * In this method I find the indexes of the children of "root"
   * then find the highest priority child.  Afterwards I compare it to
   * the current root, if the child is higher swap elements and repeat
   * down the tree
   */
  private void percolateDown(int root,int size){
    //if(currSize==0 || currSize==1){return;}
    int[] children = new int[k+1];
    int index = root; //start of percolate down
    while(true){
      for(int i=1; i<=k; i++){
        if(k*index+i < currSize){  //Store the indexes of child to
          children[i] = k*index+i;    //check with root data
        }else{children[i]=-1;}
      }
      
      //Create new node
      @SuppressWarnings("unchecked")
      Node<T,P> minChild = new Node(null, null);
      int minChildIndex = -1;
      
      //go through children and find min node
      for(int i=1; i<=k; i++)
      {
        if(children[i]!=-1){
          if(minChild.getPriority()==null){//if run through add one
            minChild = pArray[children[1]];
          }
          if(pArray[children[i]].getPriority().compareTo(minChild.getPriority())<=0){
            minChildIndex= children[i];
            minChild= pArray[children[i]];
          }
        }
      }
      if(minChildIndex==-1){//if leaf node break
        break;
      }
      
      if(pArray[index].getPriority().compareTo(pArray[minChildIndex].getPriority()) > 0){//if the priority is lower swap
        Node<T,P> tempN = pArray[index];
        pArray[index] = pArray[minChildIndex];
        pArray[minChildIndex] = tempN;   
      }
      index = minChildIndex;
    }
  }
  /**
   * Add the given value using the provided priority
   */
  public void enqueue(T value, P priority)
  { 
    //Create new node
    @SuppressWarnings("unchecked")
    Node<T,P> tempN = new Node(value, priority);
    
    if(currSize == pArray.length){
      @SuppressWarnings("unchecked")
      Node<T,P>[] temp = new Node[pArray.length*2]; //Create temp variable
      System.arraycopy(pArray, 0, temp, 0, currSize); //Copy of array into temp
      pArray = temp; //Set pArray to temp
    }
    
    //Insert new node at end
    pArray[currSize] = tempN;
    
    //Add one to current size
    
    //Store some indices
    int i = currSize;
    int parent = (currSize-1)/k;
    //percolate up
    while(parent>-1){
      if(tempN.getPriority().compareTo(pArray[parent].getPriority()) < 0 ){
        //Swap
        pArray[i] = pArray[parent];
        pArray[parent] = tempN;
        i = parent;
        parent = (i-1)/k;  
      }else{break;}
    }
    currSize++;
  }
  
  /**
   * remove the value with the highest priority
   *(i.e. smallest priority value)
   */
  public T dequeue()
  {
    if(currSize==0){return null;}
    T min = pArray[0].getData(); 
    pArray[0] = pArray[currSize-1];
    currSize--;
    percolateDown(0, currSize);
    
    
    return min;
  }
  
  /**
   * return the value of the element with highest priority
   * (i.e. smallest priority value)
   */
  public T peek()
  {
    if(currSize==0){return null;}
    return pArray[0].getData();
  }
  
  /**
   * return the priority of the element with highest priority
   * (i.e. smallest priority value)
   */
  public P peekPriority()
  {
    if(currSize==0){return null;}
    return pArray[0].getPriority();
  }
  
  /**
   * remove everything in the priority queue
   */
  public void clear()
  {
    @SuppressWarnings("unchecked")
    Node<T,P>[] temp = new Node[10];
    pArray = temp;
    currSize = 0;
  }
  
  /**
   * merge two priority queues into one and return the merged priority queue
   */
  @SuppressWarnings("unchecked")
  public KaryHeapPQ  merge(KaryHeapPQ other)
  {
    KaryHeapPQ<T,P> merged = new KaryHeapPQ();
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
  
  /**
   * return the size of the given priority queue
   */
  public int size()
  {
    return currSize;
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
    
    public T getData(){
      return data;
    }
    
    public P getPriority(){
      return priority;
    }
  }
  /**
   * Method to pring out and test  contents
   */
  private void toS(){
    for(int i=0; i<currSize; i++){
      System.out.println(pArray[i].getData() +" | P: " + pArray[i].getPriority());
    }
  }
  

  //==================================================================
  // do not modify anything below
  //==================================================================
  public KaryHeapPQ()
  {
    this(7);//back to 7
  }
  
  //merge two priority queues into one and return the merged priority queue
  public PriorityQueue  merge(PriorityQueue other)
  {
    return merge((KaryHeapPQ)other);
  }
}

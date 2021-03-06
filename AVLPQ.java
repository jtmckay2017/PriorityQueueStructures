//implement PQ using AVL Tree (AVL)
public class AVLPQ<T, P extends Comparable<P>> implements PriorityQueue<T,P>
{
  
  /**
   * current size of AVL Tree
   */
  private int currSize = 0;
  
  /**
   * root of avl tree
   */
  private Node<T,P> root = null;
  
  /**
   * constructor
   */
  public AVLPQ()
  {
  }
  
  /**
   * ******************************************
   * ***************Rotations******************
   * ******************************************
   */
  
  /**
   * height function
   */
  private int height(Node<T,P> node){
    if(node==null){return -1;}
    return 1+max(height(node.left), height(node.right));
  }
  
  /**
   * private max function to help find height
   */
  private int max(int a, int b){
    if(a>b){return a;}
    else{return b;}
  }
  
  /**
   * Balance value
   */
  private int balance(Node<T,P> node){
    if(node==null){return 0;}
    return height(node.left)-height(node.right);
  }
  
  /**
   * Left Rotation
   */
  private Node<T,P> rotateLeft(Node<T,P> currNode){
    Node<T,P> newRoot = currNode.right;
    Node<T,P> leftKidOfRight = newRoot.left;
    
    newRoot.left = currNode;
    currNode.right = leftKidOfRight;
    
    
    return newRoot;
  }
  
  /**
   * Right Rotation
   */
  private Node<T,P> rotateRight(Node<T,P> currNode){
    Node<T,P> newRoot = currNode.left;
    Node<T,P> rightKidOfLeft = newRoot.right;
    
    newRoot.right = currNode;
    currNode.left = rightKidOfLeft;
    
    return newRoot;
  }
  
  /**
   * *******************************************
   * *******************************************
   * *******************************************
   */
  /**
   * add the given value using the provided priority Done - Need to rebalance tree
   */
  public void enqueue(T value, P priority)
  {
    
    @SuppressWarnings("unchecked")
    Node<T,P> tempN = new Node(value, priority);
    root = enqueue(root, tempN);
    currSize++;
  }
  
  /**
   * recursive helper function inserting new node Done - Need to rebalance tree
   */
  private Node<T,P> enqueue(Node<T,P> root, Node<T,P> node){
    if(root==null){
      root = node;
      return root;
    }
    if(node.getPriority().compareTo(root.getPriority()) < 0){
      root.left = enqueue(root.left, node);
    } else if(node.getPriority().compareTo(root.getPriority()) >/*no duplicated keys right now*/ 0){
      root.right = enqueue(root.right, node);
    }
    
    int balance = balance(root);
    
    //Single right rotation
    if(balance > 1 && node.getPriority().compareTo(root.left.getPriority()) < 0){
      return rotateRight(root);
    }
    //Single left rotation
    if(balance < -1 && node.getPriority().compareTo(root.right.getPriority()) > 0){
      return rotateLeft(root);
    }
    //Double leftright rotation
    if(balance > 1 && node.getPriority().compareTo(root.left.getPriority()) > 0){
      root.left = rotateLeft(root.left);
      return rotateRight(root);
    }
    //Double rightleft rotation
    if(balance < -1 && node.getPriority().compareTo(root.right.getPriority()) < 0){
      root.right = rotateRight(root.right);
      return rotateLeft(root);
    }
    
    return root;
  }
  
  /**
   * remove the value with the highest priority Done
   * (i.e. smallest priority value)
   */
  public T dequeue()
  {
    if(currSize==0){return null;}
    T dataReturn = peek();    
    root = dequeueRecursive(root);
    currSize--;
    return dataReturn;
  }
  
  /**
   * helper method for dequeueing 
   */
  private Node<T,P> dequeueRecursive(Node<T,P> node){
    if(node.left==null){
      node=node.right;
    }else{
    node.left = dequeueRecursive(node.left);
    }
    int balance = balance(node);
    
    //Single right rotation
    if(balance > 1 && node.getPriority().compareTo(root.left.getPriority()) < 0){
      return rotateRight(root);
    }
    //Single left rotation
    if(balance < -1 && node.getPriority().compareTo(root.right.getPriority()) > 0){
      return rotateLeft(root);
    }
    //Double leftright rotation
    if(balance > 1 && node.getPriority().compareTo(root.left.getPriority()) < 0){
      root.left = rotateLeft(root.left);
      return rotateRight(root);
    }
    //Double rightleft rotation
    if(balance < -1 && node.getPriority().compareTo(root.right.getPriority()) > 0){
      root.right = rotateRight(root.right);
      return rotateLeft(root);
    }
    return node;
  }
  
  /**
   * return the value of the element with highest priority - Done
   * (i.e. smallest priority value)
   */
  public T peek()
  {
    if(currSize==0){return null;}
    @SuppressWarnings("unchecked")
    Node<T,P> tempN = peekRecursive(root);
    return tempN.getData();
  }
  
  /**
   * return the priority of the element with highest priority - Done
   * (i.e. smallest priority value)
   */
  public P peekPriority()
  {
    if(currSize==0){return null;}
    @SuppressWarnings("unchecked")
    Node<T,P> tempN = peekRecursive(root);
    return tempN.getPriority(); 
  }
  
  /**
   * helper method for peeking and finding smallest priority - Done
   */
  private Node<T,P> peekRecursive(Node<T,P> node){
    if(node.left==null){
      return node;
    }
    return peekRecursive(node.left);
  }
  
  /**
   * remove everything in the priority queue - Done
   */
  public void clear()
  {
    currSize=0;
    root = null;
  }
  
  /**
   * Merge method, dequeue from each data structure and enqueue into new one Done
   */
  @SuppressWarnings("unchecked")
  protected AVLPQ merge(AVLPQ other)
  {
    AVLPQ<T,P> merged = new AVLPQ();
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
   * return the size of the given priority queue - Done
   */
  public int size()
  {
    //your code here
    return currSize;
  }
  
  /**
   * in order traversal for testing results
   */
  private void toS(Node<T,P> node) {
    if(node==null){
      return;
    }
    toS(node.left);
    System.out.println(String.format("V: %s | P: %s | Height: %s", node.getData(), node.getPriority(), height(node)));
    toS(node.right);
  }
  //MyGenericNode Class
  private static class Node<T,P> {
    Node(T data, P priority){
      this.data = data;
      this.priority = priority;
    }
    
    private T data;
    private P priority;
    
    public Node<T,P> left = null;
    public Node<T,P> right = null;
    
    
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
    return merge((AVLPQ)other);
  }
}

//implementing Priority Queue using a Dynamic Array that doubles the capacity everytime the array is full
public class DynamicArrayPQ<T, P extends Comparable<P>> implements PriorityQueue<T,P>
{

    //constructor
    public DynamicArrayPQ()
    {

    }

    //add the given value using the provided priority
    public void enqueue(T value, P priority)
    {
    }

    //remove the value with the highest priority
    //(i.e. smallest priority value)
    public T dequeue()
    {
        return null;
    }

    //return the value of the element with highest priority
    //(i.e. smallest priority value)
    public T peek()
    {
        return null;
    }

    //return the priority of the element with highest priority
    //(i.e. smallest priority value)
    public P peekPriority()
    {
        return null;
    }

    //remove everything in the priority queue
    public void clear()
    {
    }

    //merge two priority queues into one and return the merged priority queue
    public DynamicArrayPQ  merge(DynamicArrayPQ other)
    {
        return null;
    }

    //return the size of the given priority queue
    public int size()
    {
        return 0;
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

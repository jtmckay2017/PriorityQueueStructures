//
// !!! Do NOT Change anything in this file
//

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.util.Random;


public class cs310pa3
{
    //all possible types of PQs
    private static String [] pqtypes={"array","avl","kary","binomial"};
    private static Class [] pqclasses={DynamicArrayPQ.class, AVLPQ.class, KaryHeapPQ.class, BinomialHeapPQ.class};
    private static String [] descriptions={"Dynamic Array","AVL tree","K-ary min Heap","Binomial min heap"};

    static private void printUsage()
    {
      System.err.println("Usage: java cs310pa3 pq-type file");
      System.err.print("\tpq-type: ");
      for(String type: pqtypes) System.err.print(type+" ");
      System.err.println();
    }

    @SuppressWarnings("unchecked")
    static public void main(String [] args)
    {
      //print usage
      if(args.length!=2)
      {
          printUsage();
          return;
      }

      String pqtype=args[0];
      String datafile=args[1];

      //build PQ
      PriorityQueue<String, Double> pq1=null,pq2=null;
      try{
        for(int i=0;i<pqtypes.length;i++)
        {
          if(pqtype.compareTo(pqtypes[i])==0)
          {
            pq1=(PriorityQueue<String, Double>)pqclasses[i].newInstance();
            pq2=(PriorityQueue<String, Double>)pqclasses[i].newInstance();
            System.out.println("[00] create two Priority queues of type: "+descriptions[i]);
            break;
          }
        }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      if(pq1==null || pq2==null){
        System.err.println("Error: unknown pq type: "+args[0]);
        printUsage();
        return;
      }

      //read data from file
      Vector<String> words=new Vector<>();
      try
      {
        Scanner scanner = new Scanner(new File(datafile));

        //read query
        int query_count=0;
        while(scanner.hasNextLine())
        {
          String word = scanner.nextLine();
          words.add(word);
        }
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      System.out.println("[01] read "+words.size()+" words from "+datafile);

      //init
      Random random = new Random(System.currentTimeMillis());
      int n=20;//(int)Math.floor(words.size()*(random.nextDouble()/10));
      int m=20;//(int)Math.floor(words.size()*(random.nextDouble()/10));

      System.out.println("[02] n="+n+" m="+m);

      //add n random records to pq1
      System.out.println("[03] adding "+n+" words to PQ#1");
      for(int i=0;i<n;i++){
        String word=words.elementAt((int)Math.floor(random.nextDouble()*words.size()));
        Double prioirty=random.nextDouble();
        pq1.enqueue(word,prioirty);
      }

      //remove n/2 from pq1
      System.out.println("[04] removing "+n/2+" words from PQ#1");
      for(int i=0;i<n/2;i++){ pq1.dequeue(); }

      //add m random records to pq2
      System.out.println("[05] adding "+m+" words to PQ#2");
      for(int i=0;i<m;i++){
        String word=words.elementAt((int)Math.floor(random.nextDouble()*words.size()));
        Double prioirty=random.nextDouble();
        pq2.enqueue(word,prioirty);
      }
      //remove m/2 from pq2
      System.out.println("[06] removing "+m/2+" words from PQ#2");
      for(int i=0;i<m/2;i++){ pq2.dequeue(); }

      //merge pq1 and pq2 into pq3
      System.out.println("[07] mering PQ#1 and PQ#2 into PQ#3");
      PriorityQueue<String, Double> pq3=pq1.merge(pq2);

      System.out.println("[08] clearing PQ#1 and PQ#2");
      pq1.clear();
      pq2.clear();

      //sort the results in pq3
      System.out.print("[09] sorting PQ#3: ");
      int pq3size=pq3.size();
      for(int i=0;i<pq3size;i++)
      {
        String value=pq3.peek();
        Double priority=pq3.peekPriority();
        System.out.print("("+value+","+priority+") ");
        pq3.dequeue();
      }
      System.out.println("[10] done.");

    }//end main

}//end cs310pa3

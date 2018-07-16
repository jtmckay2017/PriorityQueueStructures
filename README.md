
# Priority Queues (PQ)

- CS 310 Programming Assignment 3 Due: **July 22th** 11:59pm, 2018

## Assignment Objective
Implement Priority Queues using (1) Dynamic Array, (2) AVL tree, (3) K-ary Min Heap and (4) Binomial Min Heap and compare their performances.

## Table of Contents
1. [Tasks](#tasks)
2. [Examples](#examples)
3. [Rules](#rules)
4. [Submission Instructions](#submission-instructions)
5. [Grading Rubric](#grading-rubric)
6. [Useful Links](#external-links)

## Tasks

- Implement PQ using Dynamic Array (15 pts)
- Implement PQ using AVL tree  (25 pts)
- Implement PQ using K-ary Min Heap  (25 pts)
- Implement PQ using Binomial Min Heap (30 pts)
- Complete README.txt and report performance comparisons (5%)

All four of these PQs must implement PriorityQueue interface defined in PriorityQueue.java that has the following methods.

```java
    //add the given value using the provided priority
    //20%
    public void enqueue(DataType value, PriorityType priority);

    //remove the value with the highest priority (i.e. smallest priority value)
    //20%
    public DataType dequeue();

    //return the value of the element with highest priority (i.e. smallest priority value)
    //10%
    public DataType peek();

    //return the priority of the element with highest priority
    //(i.e. smallest priority value)
    //10%
    public PriorityType peekPriority();

    //remove everything in the priority queue
    //10%
    public void clear();

    //merge two priority queues into one and return the merged priority queue
    //20%
    public PriorityQueue  merge(PriorityQueue other);

    //return the size of the given priority queue
    //10%
    public int size();
```

## Examples

Using cs310pa3.java to test your code. The usage is:

```
> java cs310pa3 
Usage: java cs310pa3 pq-type file
	pq-type: array avl kary binomial 
```

We will use the following command to test your code:

```
> java cs310pa3 array data/words.txt 
[00] create two Priority queues of type: Dynamic Array
[01] read 466544 words from data/words.txt
[02] n=43420 m=21903
[03] adding 43420 words to PQ#1
[04] removing 21710 words from PQ#1
[05] adding 21903 words to PQ#2
[06] removing 10951 words from PQ#2
[07] mering PQ#1 and PQ#2 into PQ#3
[08] clearing PQ#1 and PQ#2
[09] sorting PQ#3:
     (...)
```

The program in cs310pa3.java first creates two PQs and insert **random** words loaded from data/words.txt with **random** prioirties. Then delete half of the elements from both PQS before merge them. Finally, the merged PQ is sorted using heap sort. 

## Rules

### You must

1. Have a style (indentation, good variable names, etc.)
2. Comment your code well in JavaDoc style (no need to overdo it, just do it well)
3. Have code that compiles with the command: javac *.java in your user directory

### You may 

1. Import the following libraries
```java
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
```
2. Add private methods and private data

### You cannot 
1. Make your program part of a package.
2. Import any additional libraries/packages
3. Copy code from your text book _Data Structures and Problem Solving Using Java_, 4th Edition by _Mark A. Weiss_
4. Add public methods or public data
5. Change any given methods and data prototype

## Submission Instructions
- Use the cloud or some other server to backup your code!
- Remove all test files, jar files, class files, etc.
- You should just submit your java files and your readme.txt
- Zip your user folder (not just the files) and name the zip “username-p3.zip” (no other type of archive) where “username” is your username.
- Submit to blackboard.

## Grading Rubric
[back to top](#table-of-contents)

### No Credit
- Non submitted assignments
- Late assignments after 48 hours (**late tokens will be automatically applied**)
- Non compiling assignments
- Non-independent work
- "Hard coded" solutions
- Code that would win an obfuscated code competition with the rest of CS310 students.

### How will my assignment be graded?
- Grading will be divided into two portions:
  1. Manual/Automatic Testing (100%): To assess the correctness of programs.
  2. Manual Inspection (10% off the top points): [A checklist](#manual-code-inspection-rubric-10-off-the-top-points) of features your programs should exhibit. These comprise things that cannot be easily checked via unit tests such as good variable name selection, proper decomposition of a problem into multiple functions or cooperating objects, overall design elegance, and proper asymptotic complexity. These features will be checked by graders and assigned credit based on level of compliance. See the remainder of this document for more information.
- You CANNOT get points (even style/manual-inspection points) for code that doesn't compile or for submitting just the files given to you with the assignment. You CAN get manual inspection points for code that (a) compiles and (b) is an "honest attempt" at the assignment, but does not pass any unit tests.

#### Manual/Automatic Testing (100%)
- Your output images will be compared with our output

#### Manual Code Inspection Rubric (10% "off the top" points)
These are all "off the top" points (i.e. items that will lose you points rather than earn you points):

Inspection Point | Points | High (all points) | Med (1/2 points) | Low (no points)
:---: | :---: | :--- | :--- | :--- 
Submission Format (Folder Structure) |  2 |  Code is in a folder which in turn is in a zip file. Folder is correctly named. | Code is not directly in user folder, but in a sub-folder. Folder name is correct or close to correct. | Code is directly in the zip file (no folder) and/or folder name is incorrect.
Code Formatting | 2 | Code has a set indentation and formatting style which is kept consistent throughout and code looks "well laid out".| Code has a mostly consistent indentation and formatting style, but one or more parts do not match.|Code indentation and formatting style changes throughout the code and/or the code looks "messy".
JavaDocs | 3 | The entire code base is well documented with meaningful comments in JavaDoc format. Each class, method, and field has a comment describing its purpose. Occasional in-method comments used for clarity. | The code base has some comments, but is lacking comments on some classes/methods/fields or the comments given are mostly "translating" the code. | The only documentation is what was in the template and/or documentation is missing from the code (e.g. taken out).
Coding conventions | 3 | Code has good, meaningful variable, method, and class names. All (or almost all) added fields and methods are properly encapsulated. For variables, only class constants are public. | Names are mostly meaningful, but a few are unclear or ambiguous (to a human reader) [and/or] Not all fields and methods are properly encapsulated. |  Names often have single letter identifiers and/or incorrect/meaningless identifiers. [Note: i/j/k acceptable for indexes.] [and/or] Many or all fields and methods are public or package default.

### External Links
- N/A


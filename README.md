
# Priority Queues

- CS 310 Programming Assignment 2 Due: **July 22th** 11:59pm, 2018

## Assignment Objective
Implement Priority Queues using Dynamic Array, AVL tree, K-ary Min Heap and Binomial Min Heap

## Table of Contents
1. [Tasks](#tasks)
2. [Rules](#rules)
3. [Submission Instructions](#submission-instructions)
4. [Grading Rubric](#grading-rubric)
5. [Useful Links](#external-links)

## Tasks

- Implement PQ using Dynamic Array (15 pts)
- Implement PQ using AVL tree  (25 pts)
- Implement PQ using K-ary Min Heap  (25 pts)
- Implement PQ using Binomial Min Heap (30 pts)
- README.txt (5%)

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
- Zip your user folder (not just the files) and name the zip “username-p1.zip” (no other type of archive) where “username” is your username.
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
- Your output images will be compared with our output via the following command
```
diff your-output.svg my-ouput.svg
```

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

# Session 9 — Classwork: Introduction to Data Structures & Searching Algorithms

## Overview
This directory contains pure Java solutions for Session 9 practice problems covering elementary data structures, 1D and 2D arrays, hash sets, two-pointer techniques, and binary search.

---

## Coding Problems Summary

| Problem | Class Name | Technique | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :---: | :---: |
| 1. Library Catalog Lookup | `LibraryCatalogLookup` | Binary Search | $O(\log n)$ | $O(1)$ |
| 2. Warehouse Grid Summary | `WarehouseGridSummary` | 2D Array Traversal | $O(m \times n)$ | $O(1)$ |
| 3. Pair With Target Sum | `PairWithTargetSum` | Hash Set Complement Lookup | $O(n)$ | $O(n)$ |
| 4. Pair With Target Sum (Trade-offs) | `PairWithTargetSumUnsorted` | Hash Set vs Two Pointers | $O(n)$ / $O(n \log n)$ | $O(n)$ / $O(1)$ |
| 5. Maximize Area Between Boundaries | `MaximizeAreaBetweenBoundaries` | Inward Two Pointers | $O(n)$ | $O(1)$ |

---

## Part B — Quiz Answers & Explanations

1. **Q1:** What is the time complexity for accessing an element at a known index in a one-dimensional (1D) array?  
   **Answer:** **B. $O(1)$**  
   *Explanation:* Arrays allocate contiguous blocks in memory, allowing instant random access via address arithmetic (`base_address + index * element_size`).

2. **Q2:** The operation of adding a new element to a data structure is known as what?  
   **Answer:** **C. Insertion**  
   *Explanation:* Adding elements is defined as insertion in data structure terminology.

3. **Q3:** When choosing an appropriate data structure for a given problem, what is the most crucial step?  
   **Answer:** **C. Matching the required operations and constraints to the strengths of different data structures**  
   *Explanation:* Choosing a structure depends entirely on balancing frequency of reads, writes, searches, and memory limits.

4. **Q4:** A software system stores frequently accessed calculations in a cache (extra memory) to avoid recomputing them, thereby speeding up future queries. This scenario best illustrates what concept?  
   **Answer:** **C. Time vs space trade-offs**  
   *Explanation:* Using auxiliary space (cache) to reduce execution time exemplifies the classic time-space trade-off.

5. **Q5:** A function has two distinct loops. The first loop iterates $m$ times over one input, and the second loop (not nested) iterates $n$ times over a different input. What is the overall time complexity?  
   **Answer:** **B. $O(m + n)$**  
   *Explanation:* Sequential non-nested loops have additive complexity, leading to $O(m + n)$.

6. **Q6:** In which type of data structure are elements arranged sequentially, where each element (except the first/last) has exactly one predecessor and one successor?  
   **Answer:** **C. Linear data structure**  
   *Explanation:* Arrays, linked lists, stacks, and queues maintain a single linear sequence of elements.

7. **Q7:** An algorithm performs a number of operations proportional to $2n + 5$. According to the rules of Big-O notation, what is its simplified time complexity?  
   **Answer:** **C. $O(n)$**  
   *Explanation:* Big-O notation drops lower-order terms (+5) and constant multipliers (2).

8. **Q8:** Which of the following is an example of a non-primitive data structure?  
   **Answer:** **D. Linked list**  
   *Explanation:* `int`, `char`, and `boolean` are primitive types; linked lists are user-defined/composite structures.

9. **Q9:** For a two-dimensional (2D) array with $m$ rows and $n$ columns, what is the time complexity of visiting every element exactly once?  
   **Answer:** **B. $O(m \times n)$**  
   *Explanation:* Visiting each of the $m \times n$ cells takes constant work per cell, totalling $O(m \times n)$.

10. **Q10:** What is the fundamental purpose of a data structure?  
    **Answer:** **C. To organize, store, and access data efficiently for specific operations**  
    *Explanation:* Data structures provide organized layout and access paradigms tailored for performance.

---

## Part C — Concept Questions

### Question 1: Unsorted vs Sorted 1D Array Search Complexity
* **Explanation:** In an unsorted array, no ordering assumptions exist; finding a target requires a linear scan ($O(n)$) in the worst and average cases because every element must be inspected. In contrast, a sorted array enables Binary Search ($O(\log n)$), which repeatedly compares the middle element and discards half of the search space in each step, exponentially reducing search time.

### Question 2: Why Constant Multipliers Are Dropped in Big-O
* **Explanation:** Big-O notation characterizes the asymptotic growth rate of an algorithm as the input size $n$ approaches infinity. Constant factors depend on hardware speed, compiler optimizations, and architecture, but do not alter the shape of the growth curve. Doubling the operations from $n$ to $2n$ still scales linearly with $n$, so both belong to the equivalence class $O(n)$.

### Question 3: Traversal Differences: Linear vs Non-Linear Structures
* **Explanation:** In linear structures (arrays, linked lists), elements have a 1-to-1 relationship with unique predecessor and successor neighbors, allowing a single natural sequential traversal path. In non-linear structures (trees, graphs), elements have 1-to-many or many-to-many hierarchical/network relationships, requiring specialized traversal strategies such as depth-first (in-order, pre-order, post-order) or breadth-first (level-order) searches using auxiliary stacks or queues.

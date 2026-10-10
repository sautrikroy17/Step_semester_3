# Session 10 — Homework: Category B Linked Lists Coding Assignment

## Overview
This directory contains pure Java implementations for Session 10 Category B coding assignments focusing on custom linked list data structures, in-place pointer manipulation, doubly linked lists, and circular lists without using `java.util.LinkedList`.

---

## Assignment Problems Summary

| Problem | Class Name | Technique | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :---: | :---: |
| 1. Priority Token Insertion | `PriorityTokenInsertion` | Singly Linked List (Front, End, Index Insertion) | $O(1)$ front / $O(n)$ index & end | $O(1)$ auxiliary |
| 2. Cancelled Orders Cleanup | `CancelledOrdersCleanup` | Singly Linked List Value Removal (`removeAll`) | $O(n)$ | $O(1)$ auxiliary |
| 3. Reverse the Queue | `ReverseTheQueue` | In-Place Three-Pointer Reversal | $O(n)$ | $O(1)$ auxiliary |
| 4. Train Coaches Both Ways | `TrainCoachesBothWays` | Doubly Linked List (Head & Tail References) | $O(n)$ search / $O(1)$ unlink | $O(1)$ auxiliary |
| 5. Round-Robin Game Turns | `RoundRobinGameTurns` | Circular Singly Linked List (Tail Reference) | $O(1)$ append / $O(k)$ turns | $O(1)$ auxiliary |

---

## Detailed Problem Analysis & Complexity

### Problem 1: Priority Token Insertion
* **Task:** Implement a custom singly linked list with `Node(int data, Node next)` supporting priority addition at head (`addFirst`), normal addition at tail (`addLast`), and rescheduled insertion at any 0-based position (`insertAt(int index, int token)`).
* **Constraints:**
  * $0 \le index \le current\ size$
  * $1 \le number\ of\ operations \le 50$
* **Complexity:**
  * `addFirst(token)`: $O(1)$ time, $O(1)$ auxiliary space. Direct link update at `head`.
  * `addLast(token)`: $O(n)$ time (or $O(1)$ with cached tail pointer), traversing to the last node.
  * `insertAt(index, token)`: $O(n)$ time, walking to $(index - 1)$ before splicing the new node.
  * Space Complexity: $O(1)$ auxiliary memory per operation.

### Problem 2: Cancelled Orders Cleanup
* **Task:** Given order codes in a singly linked list, purge every occurrence of a canceled order code in a single traversal and return the updated head (`Node removeAll(Node head, int code)`).
* **Constraints:**
  * $0 \le length\ of\ list \le 1000$
* **Complexity:**
  * Time Complexity: $O(n)$ linear scan, inspecting each node once.
  * Additional Space Complexity: $O(1)$ auxiliary space. Relinks existing node pointers in-place without allocating auxiliary memory.

### Problem 3: Reverse the Queue
* **Task:** Reverse a singly linked ticket queue in-place so the last element becomes the new head (`Node reverse(Node head)`). No new node instances may be allocated.
* **Constraints:**
  * $0 \le length\ of\ list \le 1000$
  * No new nodes may be created.
* **Complexity:**
  * Time Complexity: $O(n)$ single-pass pointer traversal.
  * Additional Space Complexity: $O(1)$ auxiliary memory using three pointer references (`prev`, `curr`, `next`).

### Problem 4: Train Coaches Both Ways
* **Task:** Model train coaches using a doubly linked list maintained with both `head` and `tail` pointers. Support detaching any coach by name (`remove(String name)`), forward inspection (`printForward`), and reverse inspection (`printBackward`).
* **Constraints:**
  * $1 \le number\ of\ coaches \le 50$
* **Complexity:**
  * Search Time: $O(n)$ to locate the coach by name.
  * Unlink Time: $O(1)$ once located by setting `node.prev.next = node.next` and `node.next.prev = node.prev`, with constant-time head/tail boundary updates.
  * Space Complexity: $O(1)$ auxiliary memory.

### Problem 5: Round-Robin Game Turns
* **Task:** Implement player turns in a continuous circle using a circular singly linked list maintained via a `tail` pointer where `tail.next` references the `head`.
* **Constraints:**
  * $1 \le number\ of\ players \le 20$
  * $1 \le turns \le 100$
* **Complexity:**
  * Append Time (`addLast`): $O(1)$ time by rewiring `newNode.next = tail.next` and updating `tail`.
  * Turn Execution (`printTurns`): $O(k)$ where $k$ is the number of turns, wrapping infinitely around the circular reference chain.
  * Space Complexity: $O(1)$ auxiliary memory.

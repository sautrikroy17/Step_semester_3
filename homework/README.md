# Session 9 — Homework: Category B Data Structures Coding Assignment

## Overview
This directory contains pure Java implementations for Session 9 Category B coding assignments focusing on 2D matrix aggregations, two-pointer linear merges, frequency mapping with tie-breaking, sliding window threshold alerts, and binary search insertion indexing.

---

## Assignment Problems Summary

| Problem | Class Name | Technique | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :---: | :---: |
| 1. Class Topper Finder | `ClassTopperFinder` | 2D Row-wise Accumulation | $O(m \times n)$ | $O(1)$ |
| 2. Merging Two Token Queues | `MergingTwoTokenQueues` | Two-Pointer Linear Merge | $O(m + n)$ | $O(m + n)$ |
| 3. Most Popular Canteen Order | `MostPopularCanteenOrder` | Frequency Map + Order Scan | $O(n)$ | $O(u)$ |
| 4. Hot Weather Alert Windows | `HotWeatherAlertWindows` | Fixed-Size Sliding Window | $O(n)$ | $O(1)$ |
| 5. Ticket Price Slot Finder | `TicketPriceSlotFinder` | Binary Search / Lower Bound | $O(\log n)$ | $O(1)$ |

---

## Detailed Problem Analysis & Complexity

### Problem 1: Class Topper Finder
* **Task:** Given an $m \times n$ matrix of student marks, compute the row with the maximum sum of marks. If multiple students achieve the highest total, return the smallest row index.
* **Complexity:**
  * Time: $O(m \times n)$, where $m$ is the number of students and $n$ is the number of subjects.
  * Space: $O(1)$ auxiliary memory beyond the input grid.

### Problem 2: Merging Two Token Queues
* **Task:** Combine two already sorted queues of integers into a single sorted array preserving duplicates.
* **Approach Comparison:**
  * Joining both lists and re-sorting requires $O((m + n) \log(m + n))$ time.
  * The optimal two-pointer merge approach traverses both lists concurrently in $O(m + n)$ time and $O(m + n)$ space.

### Problem 3: Most Popular Canteen Order
* **Task:** Find the most frequently ordered item in a sequence of orders. If there is a tie, return the item that appeared first in chronological order.
* **Approach Comparison:**
  * A naive scan counting occurrences of each item takes $O(n^2)$ time.
  * Using a hash map achieves $O(n)$ frequency counting, followed by a linear scan of original orders to resolve ties in $O(n)$ time and $O(u)$ auxiliary space (where $u$ is the number of unique items).

### Problem 4: Hot Weather Alert Windows
* **Task:** Count the number of contiguous windows of size $k$ whose average temperature meets or exceeds `threshold`.
* **Approach Comparison:**
  * Recalculating each window sum from scratch takes $O(k \cdot (n - k + 1)) \approx O(n \cdot k)$ time.
  * The sliding-window approach adds the incoming element and subtracts the outgoing element in $O(1)$ per step, yielding $O(n)$ overall time and $O(1)$ extra space. Integer arithmetic `windowSum >= (long) k * threshold` prevents floating-point inaccuracies.

### Problem 5: Ticket Price Slot Finder
* **Task:** Find the index of an existing ticket price, or the insertion slot index that maintains sorted order.
* **Approach Comparison:**
  * Linear scan takes $O(n)$ time.
  * Binary search (`lower_bound`) halves the search boundary each iteration, achieving $O(\log n)$ time and $O(1)$ extra space.

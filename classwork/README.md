# Session 10 — Classwork: Object-Oriented Modeling, 2D Arrays & Data Structures

## Overview
This directory contains pure Java solutions for Session 10 practice problems covering object-oriented domain modeling, polymorphism, inheritance, custom hash contracts, 2D grid analysis, and polymorphic payroll management.

---

## Coding Problems Summary

| Problem | Class Name | Technique | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :---: | :---: |
| 1. Bank Account Withdrawal System | `BankAccountWithdrawalSystem` | Abstract Class & Polymorphism | $O(1)$ per operation | $O(n)$ total accounts |
| 2. Campus Vehicle Pass System | `CampusVehiclePassSystem` | Class Hierarchy & Interface Contract | $O(1)$ per operation | $O(n)$ total passes |
| 3. Student Club Registration Management | `StudentClubRegistrationManagement` | Custom `equals()` & `hashCode()` Hash Set | $O(1)$ amortized lookup | $O(n)$ members |
| 4. Class Marks Grid Analysis | `ClassMarksGridAnalysis` | 2D Array Row/Column Aggregation | $O(m \times n)$ | $O(m \times n)$ |
| 5. Payroll Register System | `PayrollRegisterSystem` | Polymorphic Hierarchy & Overriding | $O(n)$ | $O(n)$ |

---

## Part B — Quiz Answers & Explanations

1. **Q1:** If `Student` is a derived type of `Person`, which members are directly accessible within the `Student` class?  
   **Answer:** **B. `# age` and `+ getName()`**  
   *Explanation:* `private` members (`- name`) are inaccessible outside the declaring class. `protected` members (`# age`) and `public` members (`+ getName()`) are directly accessible within subclasses.

2. **Q2:** Which diagram illustrates an invalid class inheritance structure, assuming a language that supports single class inheritance?  
   **Answer:** **C. Diagram 3, where a class attempts to extend two different classes.**  
   *Explanation:* Java and single-inheritance OOP models prohibit multiple class inheritance (`Class S` cannot directly extend both `Class Q` and `Class R`).

3. **Q3:** In what order will the constructors be executed when instantiating `Manager` where `Manager extends Employee` and `Employee extends Person`?  
   **Answer:** **B. Person -> Employee -> Manager**  
   *Explanation:* Constructor calls propagate up to the root base class first via `super()` before child class constructor bodies execute.

4. **Q4:** Which statement about accessing `Base` members or overriding from within `Derived` is correct?  
   **Answer:** **C. Derived can directly access protectedField.**  
   *Explanation:* A subclass can access inherited `protected` fields. It cannot access `privateField`, cannot override `finalMethod()`, and cannot reduce the visibility of `publicMethod()`.

5. **Q5:** If a `Vehicle` reference points to a `Bus` object and `fare()` is called through this reference, which version of `fare()` runs?  
   **Answer:** **B. The fare() method defined in Bus.**  
   *Explanation:* Java uses dynamic method dispatch (virtual invocation) resolved at runtime based on the actual object type (`Bus`).

6. **Q6:** Which statement correctly identifies the distinction between overloading and overriding?  
   **Answer:** **B. print(text: String) and print(data: int) in Printer are examples of overloading, and SpecialPrinter's print(text: String) is an example of overriding.**  
   *Explanation:* Overloading occurs within the same scope with different parameters, resolved at compile-time. Overriding redefines an inherited method with identical signature, resolved at runtime.

7. **Q7:** If a `Shape` reference `s` is assigned a `Circle` object (upcasting), which members can be accessed directly using the `s` reference?  
   **Answer:** **A. Only members defined in the Shape class.**  
   *Explanation:* The compiler permits access only to members declared in the reference type (`Shape`), unless explicitly downcasted.

8. **Q8:** For an array `Shape[] shapes` containing a `Circle` and a `Square`, what is the expected behavior when a loop calls `area()` on each element?  
   **Answer:** **C. The area() method specific to Circle will be called for shapes[0], and the area() method specific to Square will be called for shapes[1].**  
   *Explanation:* Polymorphic dispatch dynamically binds the method execution to the concrete instance type at runtime.

9. **Q9:** Given `Account a = new Current();`, which casting attempt will succeed at runtime?  
   **Answer:** **B. s = (Savings) a; will fail, c = (Current) a; will succeed.**  
   *Explanation:* The underlying instance is `Current`. Downcasting to `Current` succeeds, while downcasting to unrelated sibling `Savings` throws `ClassCastException`.

10. **Q10:** What is the benefit of processing an array of `Plan` subtypes in a loop calling `bill()` on each element?  
    **Answer:** **B. It allows uniform processing of different derived objects through a base-type reference, without needing type-specific conditional logic.**  
    *Explanation:* Polymorphism adheres to the Open-Closed Principle, allowing new plan types to be added without modifying loop execution logic.

11. **Q11:** Which operation represents essential exposed behavior of `Printer`, and which represent hidden internal details?  
    **Answer:** **A. printDocument() is essential behavior; internalBuffer, loadBuffer(), and sendToPrintHead() are hidden details.**  
    *Explanation:* Public methods form the client API; private fields and helper routines encapsulate implementation mechanics.

12. **Q12:** Where `Vehicle` is abstract and `Car` is a concrete subclass, which statement is true?  
    **Answer:** **B. A Vehicle reference can refer to a Car object.**  
    *Explanation:* Abstract classes cannot be instantiated directly, but can serve as polymorphic references to concrete subclass instances.

13. **Q13:** If concrete subclass `SavingsAccount` does not implement abstract method `calculateFee()`, what is the consequence?  
    **Answer:** **B. SavingsAccount must also be declared as an abstract class.**  
    *Explanation:* Any concrete subclass must provide implementations for all inherited abstract methods, or else declare itself abstract.

14. **Q14:** Which scenario in `Report` and subclass `MonthlyReport` leads to a compilation error?  
    **Answer:** **B. MonthlyReport attempting to be a concrete class without implementing the abstract method generateBody().**  
    *Explanation:* Failing to implement inherited abstract methods in a concrete class triggers a compilation error.

15. **Q15:** What does an interface fundamentally specify and what does it typically not provide?  
    **Answer:** **A. It specifies operations a type promises to provide, but not per-object state or direct implementation.**  
    *Explanation:* Interfaces define contracts of behavior without defining per-object instance state.

16. **Q16:** Which statement accurately describes the implicit visibility and modifiers of members in an interface?  
    **Answer:** **B. logMessage() and getLogLevel() are implicitly public abstract.**  
    *Explanation:* Methods declared in an interface without a body are implicitly `public abstract`. Interface fields are implicitly `public static final`.

17. **Q17:** Which operations must `DataProcessor` implement when realizing `Serializable` and `Resettable`?  
    **Answer:** **C. Both serialize() from Serializable and reset() from Resettable.**  
    *Explanation:* A concrete class implementing multiple interfaces must fulfill all abstract contracts declared across those interfaces.

18. **Q18:** Which construct is most appropriate for defining a common protocol for unrelated classes without default implementation or per-object state?  
    **Answer:** **C. Box C (Interface)**  
    *Explanation:* Interfaces define cross-cutting protocols across unrelated class hierarchies without coupling them to an inheritance chain.

19. **Q19:** Classify the relationships between `Dog` and `Animal`, and `Eagle` and `Flyable`.  
    **Answer:** **B. Dog IS-A Animal (generalization); Eagle CAN-DO Flyable (realization).**  
    *Explanation:* Class inheritance models an IS-A relationship; interface implementation models a CAN-DO capability.

20. **Q20:** Which operations are commonly inherited by default from the root `Object` class and often redefined?  
    **Answer:** **A. equals(), hashCode(), toString()**  
    *Explanation:* `Object` defines `equals()`, `hashCode()`, and `toString()`, which standard Java classes override for value-based equality and string formatting.

---

## Part C — Concept Questions

### Question 1: Method Overloading vs. Method Overriding
* **Explanation:** Method overloading is compile-time (static) polymorphism where multiple methods within the same class have identical names but distinct parameter signatures (count, types, or order). The compiler binds the call based on argument types at compile-time. Method overriding is run-time (dynamic) polymorphism where a subclass provides a specific implementation of a method already declared in its superclass with the exact same signature and return type. The JVM uses dynamic method dispatch to bind the invocation to the actual runtime instance.

### Question 2: Constructor Invocation Sequence
* **Explanation:** When a derived class object is created, constructors execute in top-down order starting from the root class (`java.lang.Object`) down through intermediate superclasses to the derived class. A derived class constructor passes arguments to its base constructor using the `super(...)` call, which must be the very first statement inside the derived constructor.

### Question 3: Concrete Classes vs. Abstract Types vs. Interfaces
* **Explanation:**
  - **Concrete Class:** Can be instantiated directly, can hold mutable instance state, and provides full implementations for all methods. Used for complete, self-contained domain entities.
  - **Abstract Class:** Cannot be instantiated, can hold instance fields and constructors, and can mix concrete and abstract methods. Used to provide partial common implementation across tightly coupled subtypes.
  - **Interface:** Cannot be instantiated, contains no instance state (only constants), and specifies contracts. Used to establish capabilities across unrelated types.

### Question 4: Multiple Interfaces with Identical Method Signatures
* **Explanation:** When a class implements two interfaces that both declare an abstract method with the identical signature (e.g., `void execute()`), the implementing class writes a single method implementation. This single implementation simultaneously satisfies both interface contracts, proving that interfaces specify behavioural requirements rather than distinct member identities.

### Question 5: Crucial Need to Override Both equals() and hashCode()
* **Explanation:** By default, `equals()` and `hashCode()` in `java.lang.Object` rely on memory address identity. When defining value/content equality, overriding `equals()` without overriding `hashCode()` violates the Java contract: objects that are equal must produce identical integer hash codes. If not overridden, two equal objects can produce different hash codes and be placed in different hash buckets in hash-based collections (`HashSet`, `HashMap`), resulting in duplicates and broken lookups.

### Question 6: Shallow Copy vs. Deep Copy
* **Explanation:** A shallow copy duplicates the immediate primitive fields and reference values of an object; both the original and copy point to the same underlying referenced objects in heap memory. Modifying a mutable nested object through the shallow copy mutates the original object. A deep copy recursively duplicates the object along with all nested mutable dependencies, ensuring complete memory isolation.

### Question 7: Inner Type vs. Static Nested Type
* **Explanation:** A non-static inner class holds an implicit reference to an enclosing outer class instance and can directly access outer instance variables (including private members). A `static` nested class does not hold an outer instance reference and can only access static members of the outer class. Use inner classes for tightly coupled helper components (like collection iterators); use static nested classes for independent data holders or utilities (like tree/graph `Node` classes).

### Question 8: Time Complexity of Array Operations
* **Explanation:**
  - **Access by index ($O(1)$):** Arrays are stored in contiguous memory blocks. The exact memory address is computed directly in constant time using `address = base_address + index * element_size`.
  - **Insertion/Deletion in the middle ($O(n)$):** Because elements must remain contiguous, inserting or removing an element requires shifting up to $n$ subsequent elements to create or close the slot.

### Question 9: Uniform Processing via Base Reference and Dynamic Dispatch
* **Explanation:** By storing diverse derived objects in an array of base-type references (e.g., `StaffMember[]`), client code can iterate through the collection and invoke common methods (e.g., `calculatePay()`) uniformly without knowing the concrete subtype. The JVM resolves the invocation at runtime using the object's virtual method table (vtable), directing execution to the appropriate overridden method without requiring `instanceof` checks.

### Question 10: IS-A vs CAN-DO System Design Decision
* **Explanation:** Use inheritance ('IS-A') when a type is inherently a specialized kind of another type, sharing identity, fundamental state, and lifecycle (e.g., `SavingsAccount` IS-A `BankAccount`). Use an interface ('CAN-DO') when defining an orthogonal ability or contract that can be shared across disparate, unrelated types (e.g., `Chargeable` can be implemented by both `ECar` and `Smartphone`).

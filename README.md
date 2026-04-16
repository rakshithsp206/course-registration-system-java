# 🎓 University Course Registration System (MIS)

A terminal-based **University Management Information System** developed using Java, implementing core **Object-Oriented Programming concepts**, JDBC, and design patterns.

---

## 🚀 Features

### 👤 User Roles

* **Student**

  * View courses
  * Register / Drop courses
  * View schedule
  * Track academic progress (SGPA & CGPA)
  * Submit complaints
  * Provide course feedback

* **Professor**

  * Manage assigned courses
  * View enrolled students
  * Access student feedback

* **Administrator**

  * Manage course catalog (Add/Delete)
  * Manage student records and grades
  * Assign professors to courses
  * Handle complaints

* **Teaching Assistant (TA)** *(Extension)*

  * Inherits from Student
  * Can assist professors in managing grades

---

## 🧠 Advanced Concepts Implemented

### 🔹 Object-Oriented Programming

* Abstraction, Encapsulation, Inheritance, Polymorphism
* Abstract `User` base class
* Role-based system design

### 🔹 Design Patterns

* **Singleton Pattern**

  * Used for managing a single database connection (`DBConnection`)
* **Factory Pattern**

  * Used to create user objects dynamically based on role
* **Strategy Pattern**

  * Used for GPA calculation (SGPA & CGPA)

### 🔹 Generics

* Generic `Feedback<T>` class to handle:

  * Numeric ratings
  * Textual feedback

### 🔹 Exception Handling

Custom exceptions implemented:

* `CourseFullException`
* `InvalidLoginException`
* `DropDeadlinePassedException`

---

## 🗄️ Database Integration (JDBC)

* Connected to MySQL using JDBC
* Centralized DB connection using Singleton
* Queries handled directly inside model classes (No DAO layer used)

> ⚠️ Note: Database operations are implemented within model classes instead of a separate DAO layer for simplicity.

---

## 🧱 Project Structure

```text
├── models/
│   ├── User.java
│   ├── Student.java
│   ├── Professor.java
│   ├── Administrator.java
│   ├── TeachingAssistant.java
│
├──services/
|   ├── Start.java
|   ├── CalculateGPA.java
|
├── db/
│   └── DBConnection.java
│
├── exceptions/
│   ├── CourseFullException.java
│   ├── InvalidLoginException.java
│   ├── DropDeadlinePassedException.java
│
├── factory/
│   └── UserFactory.java
│
├── main/
│   └── Main.java
```

---

## ⚙️ Application Flow

1. Start application
2. Select role (Student / Professor / Admin)
3. Login / Signup
4. Access role-specific functionalities
5. Perform operations via menu-driven interface
6. Logout / Exit safely

---

## 📊 GPA Calculation

* **SGPA**: Based on course grades within a semester
* **CGPA**: Weighted average across semesters
* Implemented using **Strategy Pattern** for flexibility

---

## 💬 Feedback System

* Generic feedback system using `Feedback<T>`
* Supports:

  * Numeric ratings (e.g., 1–5)
  * Text comments
* Professors can view feedback for their courses

---

## ⚠️ Assumptions

* Fixed credit limit per semester (e.g., 20 credits)
* Courses have predefined credits (2 or 4)
* Admin assigns grades and manages system data
* CLI-based interface (no GUI)

---

## ▶️ How to Run

1. Clone the repository
2. Configure database in `DBConnection.java`
3. Compile all Java files
4. Run `Main.java`

---

## 🧪 Demonstration

* Multiple users supported:

  * Students
  * Professors
  * Admin
* Course registration and GPA calculation
* Feedback system and complaint handling
* Exception handling scenarios demonstrated

---

## 📌 Key Highlights

✔ Clean OOP-based architecture
✔ Use of multiple design patterns
✔ Generic programming implementation
✔ Real-world simulation of university system
✔ Modular and extensible design

---

## 🏁 Future Improvements

* Introduce DAO layer for better separation of concerns
* Add GUI (JavaFX / Swing)
* Implement connection pooling
* Add authentication security (hashing)

---

## 👨‍💻 Author

Rakshith S

---

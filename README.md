# HotelReservationSystem
<div align="center">

  <img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=4,11,20,30,43&height=200&section=header&text=Hotel%20Reservation%20System&fontSize=38&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=A%20Robust%20Java%2C%20JDBC%20%26%20MySQL%20CRUD%20Application&descSize=16&descAlignY=65" width="100%"/>
<p align="center">
  <img src="https://img.shields.io/badge/Author-Subrat%20Nayak-blue?style=for-the-badge&logo=github" alt="Author">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=sqlite&logoColor=white" alt="JDBC">
  <img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=intellij-idea&logoColor=white" alt="IntelliJ">
</p>
 

</div>

---

## 🌟 About The Project

The **Hotel Reservation System** is a robust backend console application designed and built by **Subrat Nayak**. It leverages core Java principles, Object-Oriented Programming (OOP), and direct database manipulation through **JDBC** connected to a **MySQL** database.

This project automates full hotel management workflows through an intuitive, interactive console interface built inside **IntelliJ IDEA**.

---

## 💡 Problem Statement & Solution

* **The Problem:** Traditional hotel booking approaches rely on manual tracking, leading to duplicate room allocations, data inconsistency, unhandled application crashes, and slow guest lookup procedures.
* **The Solution:** This application addresses these issues by enforcing real-time transactional accuracy. Powered by strict SQL relational mapping and custom Java Exception Handling, it guarantees zero double-booking, safe database queries, and seamless data recovery across all guest transactions.

---

## ⚙️ Key Features & Deep Java Concepts

### 🔄 Full CRUD Implementation
* **Create:** Book new hotel rooms dynamically and generate unique reservation records inside MySQL.
* **Read:** Instantly retrieve guest details, room statuses, and real-time availability reports.
* **Update:** Modify existing reservation information, update guest credentials, or reassign room numbers.
* **Delete:** Process room checkout operations and purge active bookings safely from the database.

### 🛡️ Deep Exception Handling & Security
* **Robust `SQLException` Catching:** Prevents application crashes during unexpected database connection drops or execution failures.
* **Resource Safety:** Utilizes proper database connection management (`Connection`, `Statement`, `ResultSet`) with try-catch-finally strategies to prevent memory leaks.
* **Input Validation:** Prevents invalid console menu inputs using scanner checks and thread timing pauses.

---

## 🛠️ Tech Stack & Environment

| Layer | Technology Used |
| :--- | :--- |
| **Language** | Java (Core, OOP, Exception Handling, I/O) |
| **Database API** | JDBC (Java Database Connectivity) |
| **Database Engine** | MySQL Relational Database Server |
| **IDE** | IntelliJ IDEA |
| **Version Control** | Git & GitHub |

---

## 📂 Project Architecture

```text
HotelReservationSystem/
│
├── src/
│   └── main/
│       └── java/
│           ├── HotelReservationSystem.java   # Main driver code, CRUD & Menu loops
│           └── DatabaseConnection.java       # JDBC setup & MySQL connection driver
│
├── .gitignore
└── README.md

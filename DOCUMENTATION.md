# Stack & Queue Visualizer: Project Documentation

## 1. Project Overview
This project is an educational, full-stack web application designed to visualize the fundamental operations of **Stack (LIFO)** and **Queue (FIFO)** data structures. It transitions abstract algorithmic concepts into a tangible, interactive graphical interface.

## 2. Technical Architecture
The application uses a **Decoupled Architecture**:
*   **Backend:** Java 17 with Spring Boot (REST API).
*   **Frontend:** HTML5, CSS3, and Vanilla JavaScript.
*   **Communication:** JSON over HTTP (Fetch API).

---

## 3. File System Structure & Responsibilities

### Root Directory
*   `pom.xml`: Maven configuration file containing project dependencies (Spring Boot Web, Test).
*   `DOCUMENTATION.md`: This document explaining the system.

### Backend: `src/main/java/com/visualizer/`
*   `Application.java`: The entry point that starts the Spring Boot server.
*   **`model/`**: Contains the core "Engine" of the data structures.
    *   `StackData.java`: Implements a manual integer array with a `top` pointer. Handles `push`, `pop`, `peek`, and boundary validation (Overflow/Underflow).
    *   `QueueData.java`: Implements a **Circular Queue** using a manual array with `front` and `rear` pointers to ensure efficient memory use.
*   **`controller/`**: Handles the communication between the web and the logic.
    *   `StackController.java`: Exposes endpoints like `/api/stack/push` and `/api/stack/state`.
    *   `QueueController.java`: Exposes endpoints like `/api/queue/enqueue` and `/api/queue/state`.
    *   `GlobalExceptionHandler.java`: Catches logic errors (like a Full Queue) and sends a clean error message back to the UI.

### Frontend: `src/main/resources/static/`
*   `index.html`: The user interface. Contains the tabbed layout and the input forms.
*   `style.css`: The "Paint" of the project. Defines the vertical stack layout, the horizontal queue layout, and the dynamic animations for elements.
*   `app.js`: The "Brain" of the frontend. It fetches data from the Java backend, loops through the returned arrays, and creates the HTML elements (cells) you see on screen.

---

## 4. Key Logic Explanations

### Stack (Last-In-First-Out)
*   **Visualization:** Elements are stacked vertically from bottom to top.
*   **The Pointer:** The `Top` pointer always points to the highest index currently occupied. When you `Pop`, it removes the item at `Top` and moves the pointer down.

### Queue (First-In-First-Out)
*   **Visualization:** Elements are arranged horizontally.
*   **The Circular Logic:** Instead of shifting every element when one is removed (which is slow), we move the `Front` pointer forward. When the `Rear` pointer reaches the end of the array, it "wraps around" to the beginning if there is space.

## 5. Functional Requirements Met
1.  **Manual Array Sizing:** Users must define the size before starting.
2.  **Persistent State:** The backend remembers the array state as long as the server is running.
3.  **Real-time Validation:** Users receive immediate alerts for "Overflow" (Full) or "Underflow" (Empty) conditions.

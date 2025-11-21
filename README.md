# ToDoListApp

A simple command-line to-do list application written in Java. It allows users to add, view, delete, and manage tasks, which are saved to a local file so that progress persists between sessions.

## Features

*   **Add Tasks:** Create new tasks with a description.
*   **View Tasks:** See a list of all current tasks with their completion status (`[X]` for complete, `[ ]` for incomplete).
*   **Mark as Complete:** Mark any existing task as complete.
*   **Delete Tasks:** Remove tasks from the list.
*   **Persistence:** All tasks are automatically saved to `tasks.txt` and loaded when the application starts.

## How to Run

1.  **Prerequisites:** Ensure you have a Java Development Kit (JDK) version 8 or higher installed on your system.
2.  **Navigate:** Open your terminal or command prompt and navigate to the `src` directory inside the project folder.
3.  **Compile:** Compile the Java source files by running the following command:
    ```sh
    javac *.java
    ```
4.  **Run:** Run the application with the following command:
    ```sh
    java ToDoListApp
    ```

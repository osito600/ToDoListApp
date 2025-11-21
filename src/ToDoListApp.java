import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SAVE_FILE_NAME = "tasks.txt"; // File to store tasks

    public static void main(String[] args) {
        loadTasksFromFile(); // Load tasks at the beginning
        int choice;

        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    markTaskAsDone();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== To-Do List Menu ===");
        System.out.println("1. View Tasks");
        System.out.println("2. Add Task");
        System.out.println("3. Delete Task");
        System.out.println("4. Mark Task as Complete");
        System.out.println("5. Exit");
        System.out.print("Choose an option (1-5): ");
    }

    private static void viewTasks() {
        System.out.println("\n--- Your Tasks ---");
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter the task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added successfully.");
        saveTasksToFile(); // Save after adding
    }

    private static void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }

        viewTasks();
        System.out.print("Enter the task number to delete: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Successfully deleted: \"" + removedTask.getDescription() + "\"");
            saveTasksToFile(); // Save after deleting
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void markTaskAsDone() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to mark as done.");
            return;
        }

        viewTasks();
        System.out.print("Enter the task number to mark as complete: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            System.out.println("Task marked as complete!");
            saveTasksToFile(); // Save after marking as done
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE_NAME))) {
            for (Task task : tasks) {
                // Save in a simple CSV format: isDone,description
                writer.println(task.isDone() + "," + task.getDescription());
            }
        } catch (IOException e) {
            System.out.println("Error: Could not save tasks to file.");
            e.printStackTrace();
        }
    }

    private static void loadTasksFromFile() {
        File file = new File(SAVE_FILE_NAME);
        if (!file.exists()) {
            return; // No file to load, just start a new list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    boolean isDone = Boolean.parseBoolean(parts[0]);
                    String description = parts[1];
                    Task task = new Task(description);
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not load tasks from file.");
            e.printStackTrace();
        }
    }
}

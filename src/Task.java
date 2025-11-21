public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for creating a new Task.
     * By default, a new task is not marked as done.
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is marked as complete.
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Provides a user-friendly string representation of the task,
     * showing its completion status and description.
     * Example: "[X] Buy milk" or "[ ] Call mom"
     * @return The formatted string for display.
     */
    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }
}

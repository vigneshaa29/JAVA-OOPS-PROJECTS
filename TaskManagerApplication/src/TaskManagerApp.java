import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Task class to represent individual tasks
class Task {
    private int id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;

    public Task(int id, String title, String description, Priority priority, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Method to initialize task attributes
    public void init(int id, String title, String description, Priority priority, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    // Method to convert task details to a string representation
    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description +
                ", priority=" + priority + ", status=" + status + "]";
    }

}

// TaskManager class to manage tasks
class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Method to get all tasks
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Method to initialize the task manager with some tasks
    public void init() {
        // Sample tasks for initialization
        Task task1 = new Task(1, "Task 1", "Description 1", Priority.HIGH, Status.PENDING);
        Task task2 = new Task(2, "Task 2", "Description 2", Priority.MEDIUM, Status.IN_PROGRESS);
        Task task3 = new Task(3, "Task 3", "Description 3", Priority.LOW, Status.COMPLETED);

        // Add tasks to the list
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    // Method to add a new task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to edit an existing task
    public void editTask(int id, String title, String description, Priority priority, Status status) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(title);
                task.setDescription(description);
                task.setPriority(priority);
                task.setStatus(status);
                break;
            }
        }
    }

    // Method to delete a task by ID
    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    // Method to get a task by ID
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    // Method to view all tasks
    public void viewAllTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Method to filter tasks by priority
    public void filterTasksByPriority(Priority priority) {
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                System.out.println(task);
            }
        }
    }

}

public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        taskManager.init();

        int choice;
        do {
            System.out.println("\nTask Manager Application");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Filter Tasks by Priority");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Task Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Task Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Task Priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter Task Status (Pending/In Progress/Completed): ");
                    String status = scanner.nextLine();
                    // Generate unique ID for the task (e.g., incrementing from the last ID)
                    int id = taskManager.getAllTasks().size() + 1;
                    taskManager.addTask(new Task(id, title, description, Priority.valueOf(priority.toUpperCase()),
                            Status.valueOf(status.toUpperCase())));
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Task ID to Edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Task editTask = taskManager.getTaskById(editId);
                    if (editTask != null) {
                        System.out.print("Enter New Title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter New Description: ");
                        String newDescription = scanner.nextLine();
                        System.out.print("Enter New Priority: ");
                        String newPriority = scanner.nextLine();
                        System.out.print("Enter New Status: ");
                        String newStatus = scanner.nextLine();
                        taskManager.editTask(editId, newTitle, newDescription,
                                Priority.valueOf(newPriority.toUpperCase()), Status.valueOf(newStatus.toUpperCase()));
                        System.out.println("Task edited successfully!");
                    } else {
                        System.out.println("Task with ID " + editId + " not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Task ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    taskManager.deleteTask(deleteId);
                    System.out.println("Task deleted successfully!");
                    break;
                case 4:
                    System.out.println("\nAll Tasks:");
                    taskManager.viewAllTasks();
                    break;
                case 5:
                    System.out.print("Enter Priority to Filter Tasks: ");
                    String filterPriority = scanner.nextLine();
                    System.out.println("\nTasks with Priority " + filterPriority + ":");
                    taskManager.filterTasksByPriority(Priority.valueOf(filterPriority.toUpperCase()));
                    break;
                case 6:
                    System.out.println("Exiting Task Manager Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}

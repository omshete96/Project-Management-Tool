import java.util.ArrayList;
import java.util.Scanner;

public class ProjectManagementTool {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Project> projects = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nProject Management Tool");
            System.out.println("1. Add Project");
            System.out.println("2. Add Task to Project");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. View Project Details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProject();
                    break;
                case 2:
                    addTaskToProject();
                    break;
                case 3:
                    markTaskAsComplete();
                    break;
                case 4:
                    viewProjectDetails();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProject() {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        projects.add(new Project(name));
        System.out.println("Project added successfully.");
    }

    private static void addTaskToProject() {
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        Project project = findProject(projectName);

        if (project != null) {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine();
            project.addTask(new Task(description));
            System.out.println("Task added successfully.");
        } else {
            System.out.println("Project not found.");
        }
    }

    private static void markTaskAsComplete() {
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        Project project = findProject(projectName);

        if (project != null) {
            System.out.print("Enter task description: ");
            String taskDescription = scanner.nextLine();
            Task task = project.findTask(taskDescription);

            if (task != null) {
                task.markAsComplete();
                System.out.println("Task marked as complete.");
            } else {
                System.out.println("Task not found.");
            }
        } else {
            System.out.println("Project not found.");
        }
    }

    private static void viewProjectDetails() {
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        Project project = findProject(projectName);

        if (project != null) {
            System.out.println("Project: " + project.getName());
            System.out.println("Tasks:");
            for (Task task : project.getTasks()) {
                System.out.println("- " + task);
            }
        } else {
            System.out.println("Project not found.");
        }
    }

    private static Project findProject(String name) {
        for (Project project : projects) {
            if (project.getName().equalsIgnoreCase(name)) {
                return project;
            }
        }
        return null;
    }
}

class Project {
    private final String name;
    private final ArrayList<Task> tasks;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task findTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                return task;
            }
        }
        return null;
    }
}

class Task {
    private final String description;
    private boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String getDescription() {
        return description;
    }

    public void markAsComplete() {
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return description + " (Complete: " + isComplete + ")";
    }
}

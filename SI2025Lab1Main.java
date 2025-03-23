import java.util.*;

enum Priority {
    LOW, MEDIUM, HIGH
}

class Task {
    private String name;
    private boolean isCompleted;
    private Priority priority;
    private String category;

    public Task(String name, Priority priority, String category) {
        this.name = name;
        this.priority = priority;
        this.category = category;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " [" + category + "] - Priority: " + priority + (isCompleted ? " [Completed]" : " [Pending]");
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String name, Priority priority, String category) {
        tasks.add(new Task(name, priority, category));
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // 1. Remove a task by name
    

    public void removeTask(String name) {
        tasks.removeIf(task -> task.getName().equals(name));
    }

    // 2. Find all completed tasks


    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    // 3. List tasks sorted by name


    public void sortTasksByName() {
        tasks.sort(Comparator.comparing(Task::getName));
    }

    // 4. Sort tasks by priority
    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
    }

    // 5. Filter tasks by category
    public List<Task> filterByCategory(String category) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    // 6. Find the highest-priority unfinished task
    public Task getMostUrgentTask() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .max(Comparator.comparing(Task::getPriority))
                .orElse(null);
    }

    // 7. Count tasks per category
    public Map<String, Integer> countTasksPerCategory() {
        Map<String, Integer> categoryCount = new HashMap<>();
        for (Task task : tasks) {
            categoryCount.put(task.getCategory(), categoryCount.getOrDefault(task.getCategory(), 0) + 1);
        }
        return categoryCount;
    }

    // 8. Mark a task as completed by name
    public void markTaskCompleted(String name) {
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                task.complete();
                break;
            }
        }
    }

    // 9. Mark all tasks in a category as completed
    public void markCategoryCompleted(String category) {
        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category)) {
                task.complete();
            }
        }
    }
}

public class SI2025Lab1Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("Write report", Priority.HIGH, "Work");
        manager.addTask("Submit assignment", Priority.MEDIUM, "School");
        manager.addTask("Buy groceries", Priority.LOW, "Personal");


        System.out.println("All tasks:");
        manager.printTasks();
        manager.markTaskCompleted("Write report");
        System.out.println("\nCompleted tasks:");

        manager.printTasks();
        manager.removeTask("Buy groceries");

        System.out.println("\nTasks after removal:");
        manager.printTasks();

        manager.sortTasksByName();
        System.out.println("\nTasks sorted by name:");
        manager.printTasks();

        manager.sortTasksByPriority();
        System.out.println("\nTasks sorted by priority:");
        manager.printTasks();

        List<Task> workTasks = manager.filterByCategory("Work");
        System.out.println("\nWork tasks:");
        for (Task task : workTasks) {
            System.out.println(task);
        }

        Task urgentTask = manager.getMostUrgentTask();
        System.out.println("\nMost urgent task: " + urgentTask);

        Map<String, Integer> categoryCounts = manager.countTasksPerCategory();
        System.out.println("\nTask counts per category:");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        manager.markCategoryCompleted("School");
        System.out.println("\nTasks after marking 'School' category as completed:");
        manager.printTasks();
    }
}

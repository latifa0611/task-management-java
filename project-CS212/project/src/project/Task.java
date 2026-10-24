package project;

public class Task {

    private String title;
    private int priority;     //  1=High, 2=Medium, 3= low
    private boolean isCompleted;
    private String dueDate;

    public Task(String title, int priority, boolean isCompleted, String dueDate) {
        this.title = title;
        this.priority = priority;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("Task: %s,\t\tPriority: %d, \tIs Completed: %b \tDue: %s",
                title, priority, isCompleted, dueDate);
    }
}

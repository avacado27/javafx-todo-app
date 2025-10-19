public class Task {
    private String title;
    private boolean completed;
    private int priority;

    public Task(String title) {
        this.title = title;
        this.completed = false;
        this.priority = 2;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        String priorityLabel;
        switch(priority) {
            case 1 -> priorityLabel = "High";
            case 2 -> priorityLabel = "Medium";
            case 3 -> priorityLabel = "Low";
            default -> priorityLabel = "Unset";
        }
        return (completed ? "[✔] " : "[ ] ") + title + " (Priority: " + priorityLabel + ")\n";
    }
}

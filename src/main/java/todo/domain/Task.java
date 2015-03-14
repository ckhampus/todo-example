package todo.domain;

import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private String id;

    private String description;

    private Boolean completed;

    protected Task() {
    }

    public Task(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public static Task create(String description, Boolean completed) {
        Task task = new Task(description);
        task.setCompleted(completed);
        return task;
    }
}

package todo.domain;

import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private String id;

    private String description;

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
}

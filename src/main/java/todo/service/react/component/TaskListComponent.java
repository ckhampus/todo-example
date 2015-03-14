package todo.service.react.component;

import todo.domain.Task;

import java.util.List;

public class TaskListComponent extends Component {
    public TaskListComponent(List<Task> tasks) {
        super("TaskList", tasks, "tasks");
    }
}

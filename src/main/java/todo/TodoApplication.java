package todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import todo.domain.Task;
import todo.domain.TaskRepository;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {
    @Autowired
    private TaskRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        repository.deleteAll();

        repository.save(Task.create("Clean the floor", true));
        repository.save(Task.create("Do the dishes", false));
        repository.save(Task.create("Make some tea", true));

        repository.findAll().forEach(System.out::println);
    }
}

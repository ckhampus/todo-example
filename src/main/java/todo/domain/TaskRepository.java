package todo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface TaskRepository extends MongoRepository<Task, String> {

}

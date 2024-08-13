package standartpark.to_do_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import standartpark.to_do_list.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

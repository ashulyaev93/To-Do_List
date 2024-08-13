package standartpark.to_do_list.services;

import standartpark.to_do_list.entities.Task;
import standartpark.to_do_list.entities.enums.Status;
import standartpark.to_do_list.exceptions.TaskNotFoundException;

import java.util.List;

public interface TaskService {

    public Task createTask(Task task);
    public List<Task> getAllTasks();
    public Task updateTaskStatus(Long id, Status status);
    public void deleteTask(Long id);
}

package standartpark.to_do_list.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import standartpark.to_do_list.entities.Task;
import standartpark.to_do_list.entities.enums.Status;
import standartpark.to_do_list.exceptions.TaskNotFoundException;
import standartpark.to_do_list.repositories.TaskRepository;
import standartpark.to_do_list.services.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskDAO;

    @Autowired
    public TaskServiceImpl(TaskRepository taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public Task createTask(Task task) {
        return taskDAO.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }

    @Override
    public Task updateTaskStatus(Long id, Status status) {
        Task task = taskDAO.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(status);
        return taskDAO.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskDAO.deleteById(id);
    }
}

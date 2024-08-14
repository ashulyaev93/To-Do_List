package standartpark.to_do_list.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import standartpark.to_do_list.entities.Task;
import standartpark.to_do_list.entities.enums.Status;
import standartpark.to_do_list.exceptions.TaskNotFoundException;
import standartpark.to_do_list.repositories.TaskRepository;
import standartpark.to_do_list.services.TaskService;

import java.util.List;

@Service
@Transactional
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
    public Task updateTaskStatus(Long id, Status newStatus) {
        Task task = taskDAO.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        if (!task.getStatus().equals(newStatus)) {
            task.setStatus(newStatus);
            return taskDAO.save(task);
        }
        return task;
    }

    @Override
    public void deleteTask(Long id) {
        taskDAO.deleteById(id);
    }
}

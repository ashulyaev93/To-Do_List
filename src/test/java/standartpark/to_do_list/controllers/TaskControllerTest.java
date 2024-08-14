package standartpark.to_do_list.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import standartpark.to_do_list.entities.Task;
import standartpark.to_do_list.entities.enums.Status;
import standartpark.to_do_list.services.impl.TaskServiceImpl;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskServiceImpl taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Title For Task");
        task.setDescription("Test Description Test Description Test Description");
        task.setStatus(Status.WAITING);

        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                        .contentType("application/json")
                        .content("{\"title\":\"Test Title For Task\", \"description\":\"Test Description Test Description Test Description\", \"status\":\"WAITING\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Title For Task"))
                .andExpect(jsonPath("$.description").value("Test Description Test Description Test Description"))
                .andExpect(jsonPath("$.status").value("WAITING"));

        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    void testGetAllTasks() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Title");
        task.setDescription("Test Description");
        task.setStatus(Status.WAITING);

        when(taskService.getAllTasks()).thenReturn(List.of(task));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Title"))
                .andExpect(jsonPath("$[0].description").value("Test Description"))
                .andExpect(jsonPath("$[0].status").value("WAITING"));

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testUpdateTaskStatus() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Title");
        task.setDescription("Test Description");
        task.setStatus(Status.COMPLETED);

        when(taskService.updateTaskStatus(anyLong(), any(Status.class))).thenReturn(task);

        mockMvc.perform(put("/api/tasks/1/status")
                        .param("status", "COMPLETED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("COMPLETED"));

        verify(taskService, times(1)).updateTaskStatus(anyLong(), any(Status.class));
    }

    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(anyLong());

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());

        verify(taskService, times(1)).deleteTask(anyLong());
    }
}

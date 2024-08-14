package standartpark.to_do_list;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateAndGetTask() throws Exception {
        String taskJson = "{\"title\":\"Test Title Test Title\", \"description\":\"Test Description Test Description Test Description\", \"status\":\"WAITING\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Title Test Title"))
                .andExpect(jsonPath("$[0].description").value("Test Description Test Description Test Description"))
                .andExpect(jsonPath("$[0].status").value("WAITING"));
    }

    @Test
    void testUpdateTaskStatus() throws Exception {
        String taskJson = "{\"title\":\"Test Title Test Title\", \"description\":\"Test Description Test Description Test Description\", \"status\":\"COMPLETED\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/1/status")
                        .param("status", "COMPLETED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    void testDeleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/1"))
                .andExpect(status().isNoContent());
    }
}

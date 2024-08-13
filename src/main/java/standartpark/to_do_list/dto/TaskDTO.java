package standartpark.to_do_list.dto;

import lombok.Builder;
import lombok.Data;
import standartpark.to_do_list.entities.enums.Status;

@Data
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Status status;
}

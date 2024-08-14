package standartpark.to_do_list.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import standartpark.to_do_list.dto.TaskDTO;
import standartpark.to_do_list.entities.Task;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Named("taskToTaskDTO")
    @Mappings({
            @Mapping(source="task.id", target="id"),
            @Mapping(source="task.title", target="title"),
            @Mapping(source="task.description", target="description"),
            @Mapping(source="task.status", target="status")
    })
    TaskDTO taskToTaskDTO(Task task);
    @Mappings({
            @Mapping(source="taskDTO.id", target="id"),
            @Mapping(source="taskDTO.title", target="title"),
            @Mapping(source="taskDTO.description", target="description"),
            @Mapping(source = "taskDTO.status", target = "status"),
    })
    Task taskDTOtoTask(TaskDTO taskDTO);
}

package standartpark.to_do_list.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import standartpark.to_do_list.entities.enums.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 10, max = 128, message = "Title must be between 10 and 128 characters")
    @Column(name = "title", length = 128, unique = true)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 20, max = 1000, message = "Description must be between 20 and 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @NotNull(message = "Status cannot be null")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 128)
    private Status status;
}

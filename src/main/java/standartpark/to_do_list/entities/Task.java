package standartpark.to_do_list.entities;

import jakarta.persistence.*;
import lombok.*;
import standartpark.to_do_list.entities.enums.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "title", length = 128, unique = true)
    private String title;
    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 128, updatable = false)
    private Status status;
}

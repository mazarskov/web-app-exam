package ee.eek.stim.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table(name = "games")
public class Game {

    @Id
    Long id;

    private String title;

    private String description;
}

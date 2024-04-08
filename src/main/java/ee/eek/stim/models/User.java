package ee.eek.stim.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table(name = "users")
public class User {

    @Id
    Long id;
    
    private String name;
    
    private String password;
    
    private Integer basket;
}

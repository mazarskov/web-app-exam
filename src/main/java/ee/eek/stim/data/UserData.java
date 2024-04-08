package ee.eek.stim.data;

import lombok.Data;

@Data
public class UserData {
    private Long id;
    private String name;
    private String password;
    private Integer basket;
}

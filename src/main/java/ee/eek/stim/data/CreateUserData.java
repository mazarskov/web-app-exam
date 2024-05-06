package ee.eek.stim.data;

import lombok.Data;

@Data
public class CreateUserData {
    private String name;
    private String password;
    private int basket;
}

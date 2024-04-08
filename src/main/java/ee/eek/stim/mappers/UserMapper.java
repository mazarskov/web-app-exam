package ee.eek.stim.mappers;

import ee.eek.stim.data.CreateUserData;
import ee.eek.stim.data.UserData;
import ee.eek.stim.models.User;

public class UserMapper {
    public static UserData toDto(User request) {
        UserData userData = new UserData();
        userData.setId(request.getId());
        userData.setName(request.getName());
        userData.setPassword(request.getPassword());
        userData.setBasket(request.getBasket());
        return userData;
    }
        public static User toEntity(CreateUserData request) {
        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        return user;
    }
}

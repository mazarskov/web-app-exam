package ee.eek.stim.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ee.eek.stim.data.CreateUserData;
import ee.eek.stim.data.UserData;
import ee.eek.stim.models.User;
import ee.eek.stim.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    UserData userData = new UserData();

    @GetMapping("/api/users")
    public List<UserData> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/api/users/{user_id}")
    public User getMethodName(@PathVariable Long user_id) {
        return userService.getById(user_id);
    }
    

    @PostMapping("/api/users/adduser")
    public UserData postMethodName(@RequestBody CreateUserData user) {
        return userService.create(user);
    }

    @GetMapping("/api/users/{user_id}/addtobasket/{game_id}")
    public User addToBasket(@PathVariable Long user_id, Integer game_id) {
        return userService.addGameToBasket(game_id, user_id);
    }
    
    
}

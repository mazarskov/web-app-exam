package ee.eek.stim.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ee.eek.stim.data.CreateUserData;
import ee.eek.stim.data.UserData;
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

    @PostMapping("/api/users/adduser")
    public UserData postMethodName(@RequestBody CreateUserData user) {
        return userService.create(user);
    }

    @GetMapping("/api/users/{user_id}/addtobasket/{game_id}")
    public String addToBasket(@PathVariable Long user_id, Long game_id) {
        return "Not ready";
    }
    
    
}

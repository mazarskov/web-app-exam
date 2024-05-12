package ee.eek.stim.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






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

    @DeleteMapping("/api/users/delete/{user_id}")
    public User deleteUser(@PathVariable Long user_id) {
        return userService.deleteUser(userService.getById(user_id).getId());
        
    }


    @PutMapping("api/users/{user_id}/add/game")
    public ResponseEntity<UserData> putMethodName(@PathVariable Long user_id, @RequestParam Integer game) {
        return userService.addToBasket(user_id, game);
    }
    
    @PutMapping("api/users/{user_id}/clearbasket")
    public ResponseEntity<UserData> clearBasket(@PathVariable Long user_id) {
        return userService.addToBasket(user_id, 0);
    }

    @GetMapping("/api/users/usernames")
    public List<String> getMethodName() {
        return userService.listUsernames();
    }

    @GetMapping("/api/users/user")
    public User getUser(@RequestParam String username, @RequestParam String password) {
        return userService.findUser(username, password);
    }
}

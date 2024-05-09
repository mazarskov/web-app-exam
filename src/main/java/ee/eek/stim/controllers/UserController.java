package ee.eek.stim.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ee.eek.stim.data.CreateUserData;
import ee.eek.stim.data.UserData;
import ee.eek.stim.mappers.UserMapper;
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
        User updatedUser = userService.updateUserBasket(user_id, game);
        UserData updatedUserData = UserMapper.toDto(updatedUser);
        return ResponseEntity.ok(updatedUserData);
    }
    
    @PutMapping("api/users/{user_id}/clearbasket")
    public ResponseEntity<UserData> clearBasket(@PathVariable Long user_id) {
        User updatedUser = userService.updateUserBasket(user_id, 0);
        UserData updatedUserData = UserMapper.toDto(updatedUser);
        return ResponseEntity.ok(updatedUserData);
    }
}

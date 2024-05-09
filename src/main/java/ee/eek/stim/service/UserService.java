package ee.eek.stim.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ee.eek.stim.data.CreateUserData;
import ee.eek.stim.data.UserData;
import ee.eek.stim.mappers.UserMapper;
import ee.eek.stim.models.User;
import ee.eek.stim.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;


    public UserData create(CreateUserData createUserData) {
        User user = UserMapper.toEntity(createUserData);
        User saved = usersRepository.save(user);
        return UserMapper.toDto(saved);
    }
    public List<UserData> getAll() {
        return usersRepository.findAll()
            .stream()
            .map(UserMapper::toDto)
            .toList();
    }
    public User getById(Long id) {
        return usersRepository.findAllById(id);
    }
    public User addGameToBasket(Integer game_id, Long user_id) {
        return usersRepository.addGameToBasket(game_id, user_id);
    }
    public User deleteUser(Long user_id) {
        User user = getById(user_id);
        usersRepository.delete(user);
        return user;
    }

    public User updateUserBasket(Long user_id, Integer basket) {
        User user = getById(user_id);
        if (basket <= 0) {
            user.setBasket(0);
            User updatedUser = usersRepository.save(user);
            return updatedUser;
        } 
        if (String.valueOf(user.getBasket()).length() == 4) {
            User updatedUser = usersRepository.save(user);
            return updatedUser;
        } else {
            String oldBasket = String.valueOf(user.getBasket());
            String newBasket = String.valueOf(basket);
            basket = Integer.valueOf(oldBasket + newBasket);
            user.setBasket(basket);
            User updatedUser = usersRepository.save(user);
            return updatedUser;
        }
    }

    public boolean validateUser(Long id) {
        try {
            User user = getById(id);
            if (user.getId() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}

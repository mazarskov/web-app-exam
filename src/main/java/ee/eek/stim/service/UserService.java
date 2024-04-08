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

}

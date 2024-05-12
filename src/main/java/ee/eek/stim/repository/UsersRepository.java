package ee.eek.stim.repository;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ee.eek.stim.models.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    @Query("SELECT * FROM users;")
    List<User> findAll();

    @Query("""
            select * from users where id = :id;
            """)
    User findAllById(Long id);

    @Query("""
            UPDATE users SET basket = :game_id WHERE id = :user_id RETURNING *;
            """)
    User addGameToBasket(Integer game_id, Long user_id);

    @Query("SELECT name FROM users;")
    List<String> listNames();

    @Query("SELECT * FROM users WHERE name = :username AND password = :password;")
    User findUser(String username, String password);
}
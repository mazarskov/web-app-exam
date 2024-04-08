package ee.eek.stim.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ee.eek.stim.models.Game;

@Repository
public interface GamesRepository extends CrudRepository<Game, Long> {
    @Query("SELECT * FROM games;")
    List<Game> findAll();

    @Query("""
            select * from games where id = :id;
            """)
    Game findAllById(Long id);
}

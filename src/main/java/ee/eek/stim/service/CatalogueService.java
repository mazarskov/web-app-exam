package ee.eek.stim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ee.eek.stim.data.CreateGameData;
import ee.eek.stim.data.GameData;
import ee.eek.stim.mappers.GameMapper;
import ee.eek.stim.models.Game;
import ee.eek.stim.repository.GamesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogueService {
    private final GamesRepository gamesRepository;
    public List<GameData> getAll() {
        return gamesRepository.findAll()
            .stream()
            .map(GameMapper::toDto)
            .toList();
    }
    public GameData create(CreateGameData createGameData) {
        Game game = GameMapper.toEntity(createGameData);
        Game saved = gamesRepository.save(game);
        return GameMapper.toDto(saved);
    }
    public Game getById(Long id) {
        return gamesRepository.findAllById(id);
    }
    public String deleteGame(Long id) {
        Game game = gamesRepository.findAllById(id);
        gamesRepository.delete(game);
        return "Succesfully deleted";
    }
}

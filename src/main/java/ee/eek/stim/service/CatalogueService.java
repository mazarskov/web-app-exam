package ee.eek.stim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ee.eek.stim.data.CreateGameData;
import ee.eek.stim.data.GameData;
import ee.eek.stim.mappers.GameMapper;
import ee.eek.stim.models.Game;
import ee.eek.stim.repository.GamesRepository;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

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
    public Game deleteGame(Long id) {
        Game game = gamesRepository.findAllById(id);
        gamesRepository.delete(game);
        return game;
    }
    public List<Game> listFromBasket(Integer basket) {
        List<Game> listOfGames = new ArrayList<>();
        String stringBasket = String.valueOf(basket);
        for (int i = 0; i < stringBasket.length(); i++) {
            Integer singleItem = Character.getNumericValue(stringBasket.charAt(i));
            Long gameId = Long.valueOf(singleItem);
            Game game = gamesRepository.findAllById(gameId);
            listOfGames.add(game);
        }
        return listOfGames;
    }
    //1 - to change title, 2 - to change description
    public Game updateGameData(Long id, String newData, Integer selection) {
        Game game = getById(id);
        if (selection == 1) {
            game.setTitle(newData);
            Game updatedGame = gamesRepository.save(game);
            return updatedGame;
        } else if (selection == 2) {
            game.setDescription(newData);
            Game updatedGame = gamesRepository.save(game);
            return updatedGame;
        } else {
            return game;
        }
    }
}

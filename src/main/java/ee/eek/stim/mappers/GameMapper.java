package ee.eek.stim.mappers;

import ee.eek.stim.data.CreateGameData;
import ee.eek.stim.data.GameData;
import ee.eek.stim.models.Game;

public class GameMapper {
    public static GameData toDto(Game request) {
        GameData gameData = new GameData();
        gameData.setId(request.getId());
        gameData.setTitle(request.getTitle());
        gameData.setDescription(request.getDescription());
        return gameData;
    }

    public static Game toEntity(CreateGameData request) {
        Game game = new Game();
        game.setTitle(request.getTitle());
        game.setDescription(request.getDescription());
        return game;
    }

}

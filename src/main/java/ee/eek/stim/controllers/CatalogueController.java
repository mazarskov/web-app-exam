package ee.eek.stim.controllers;

import org.springframework.web.bind.annotation.RestController;

import ee.eek.stim.data.CreateGameData;
import ee.eek.stim.data.GameData;
import ee.eek.stim.models.Game;
import ee.eek.stim.service.CatalogueService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequiredArgsConstructor
public class CatalogueController {
    
    private final CatalogueService catalogueService;

    GameData gameData = new GameData();
    
    @GetMapping("/api")
    public String apiUsageExplanation() {
        return """
            Bla bla bla this is how you use this API bla bla bla <br>
            Endpoints: <br>
            /api - this page <br>
            /api/catalogue - get a list of all games, return type List <br>
            /api/catalogue/{game_id} - get info about a certain game, return type Game <br>
            /api/catalogue/addgame - add game to db, return type String <br>
            /api/catalogue/deletegame/{game_id} - delete game from db, return type String <br>
            /api/users - get a list of all users, return type List <br>
            /api/users/adduser - add user to db, return type String <br>
                """;
    }
    
    @GetMapping("/api/catalogue")
    public List<GameData> getGames() {
        return catalogueService.getAll();
    }

    @GetMapping("/api/catalogue/{game_id}")
    public Game getGameInfo(@PathVariable Long game_id) {
        return catalogueService.getById(game_id);
    }

    @PostMapping("/api/catalogue/addgame")
    public String postMethodName(@RequestBody CreateGameData game) {        
        return "Game created with id: " + catalogueService.create(game).getId();
    }
    @GetMapping("/api/catalogue/deletegame/{game_id}")
    public String deleteGameById(@PathVariable Long game_id) {
        return catalogueService.deleteGame(game_id);
    }
    


    

}

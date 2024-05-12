package ee.eek.stim.controllers;

import org.springframework.web.bind.annotation.RestController;

import ee.eek.stim.data.CreateGameData;
import ee.eek.stim.data.GameData;
import ee.eek.stim.models.Game;
import ee.eek.stim.service.CatalogueService;
import ee.eek.stim.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequiredArgsConstructor
public class CatalogueController {
    
    private final CatalogueService catalogueService;
    private final UserService userService;

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
    public List<GameData> getGames(@RequestParam Long token) {
        boolean isValid = userService.validateUser(token);
        if (isValid) {
            return catalogueService.getAll();
        } else {
            return null;
        }
    }

    @GetMapping("/api/catalogue/{game_id}")
    public Game getGameInfo(@PathVariable Long game_id, @RequestParam Long token) {
        if (userService.validateUser(token)) {
            return catalogueService.getById(game_id);
        } else {
            return null;
        }
    }

    @PostMapping("/api/catalogue/addgame")
    public GameData postMethodName(@RequestBody CreateGameData game, @RequestParam Long token) {        
        if (userService.validateUser(token)) {
            return catalogueService.create(game);
        } else {
            return null;
        }
    }
    @DeleteMapping("/api/catalogue/deletegame/{game_id}")
    public Game deleteGameById(@PathVariable Long game_id, @RequestParam Long token) {
        if (userService.validateUser(token)) {
            return catalogueService.deleteGame(game_id);
        } else {
            return null;
        }
    }
    @GetMapping("/api/catalogue/list")
    public List<Game> listBasket(@RequestParam Long token, @RequestParam Integer basket) {
        if (userService.validateUser(token)) {
            return catalogueService.listFromBasket(basket);
        } else {
            return null;
        }
    }
    @PutMapping("/api/catalogue/game")
    public Game changeGameData(@RequestParam Long id, @RequestParam String newData, @RequestParam Integer selection) {
        //TODO: process PUT request
        return catalogueService.updateGameData(id, newData, selection);
    }
}

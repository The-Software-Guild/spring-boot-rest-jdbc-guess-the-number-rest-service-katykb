package guessNumberGame.data;


import guessNumberGame.data.GameDao;
import guessNumberGame.data.RoundDao;
import junit.framework.TestCase;
import guessNumberGame.Service.GameService;
import guessNumberGame.TestApplicationConfiguration;
import guessNumberGame.models.Game;
import guessNumberGame.models.Round;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDatabaseDaoTest extends TestCase {
    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;
    public RoundDatabaseDaoTest ()
    {
        
    }
    
    @Before
    public void setUp() {
        List<Round> rounds = roundDao.getAll();
        for(Round round : rounds) {
            roundDao.deleteById(round.getRound_id());
        }

        List<Game> games = gameDao.getAll();
        for(Game game : games) {
            gameDao.deleteById(game.getGameId());
        }
    }

    @Test
    public void testAdd() {
        GameService gameService = new GameService();
        Game game = gameService.newGame();
        gameDao.add(game);

        Round round = new Round();
        round.setGame_id(game.getGameId());
        gameService.setTimeStamp(round);
        round.setGuess("1234");
        round.setGuessResult("e:2:p:1");
        roundDao.add(round);
        Round fromDao = roundDao.findById(round.getRound_id());

        assertEquals(round.getRound_id(), fromDao.getGame_id());
    }

    @Test
    public void testGetAll() {
        GameService gameService = new GameService();
        Game game = gameService.newGame();
        gameDao.add(game);

        Game game2 = gameService.newGame();
        gameDao.add(game2);

        Round round = new Round();
        round.setGuess("1111");
        round.setGame_id(game.getGameId());

        Round round2 = new Round();
        round2.setGuess("2222");
        round2.setGame_id(game2.getGameId());

        roundDao.add(round);
        roundDao.add(round2);

        List<Round> rounds = roundDao.getAll();
       assertEquals(2, rounds.size());
    }

    @Test
    public void testGetAllOfGame() {
         //implement
    }

}

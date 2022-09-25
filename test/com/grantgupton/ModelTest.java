package test.com.grantgupton;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.grantgupton.Model;
import com.grantgupton.Model.States;

public class ModelTest {
    public Model game;

    @BeforeEach
    void setUp() {
        game = new Model();
    }

    @Test
    void testGetWinner() {
        assertEquals(States.EMPTY, game.getWinner());

        game.makeMove(States.BLUE_PLAYER, 0);
        game.makeMove(States.BLUE_PLAYER, 0);
        game.makeMove(States.BLUE_PLAYER, 0);
        game.makeMove(States.BLUE_PLAYER, 0);

        assertEquals(States.BLUE_PLAYER, game.getWinner());

        game.resetGame();
    }
}

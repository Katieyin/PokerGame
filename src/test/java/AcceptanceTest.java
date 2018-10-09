import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AcceptanceTest {
    static List<Player> winners;

    @BeforeClass
    public static void getGameResults() {
        winners = new ArrayList<Player>();
        try {
            File file = new File("src/main/java/inputCards.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Game game = new Game(line);
                Player winner = game.playGame();
                winners.add(winner);
            }
            fileReader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    @Test
//    For either player,  detect any royal flush and hold
    public void testRoyalFlush() {
        assertEquals("Royal Flush", winners.get(0).getResult().getCombination());
    }

    @Test
//    For either player,  detect any straight flush and hold
    public void testStraightFlush() {
        assertEquals("Straight Flush", winners.get(1).getResult().getCombination());
    }

    @Test
//    For either player,  detect any 4 of a kind and hold
    public void testFourOfAKind() {
        assertEquals("4 Of A Kind", winners.get(2).getResult().getCombination());
    }

    @Test
//    For either player,  detect any full house and hold
    public void testFullHouse() {
        assertEquals("Full House", winners.get(3).getResult().getCombination());
    }

    @Test
//    For either player,  detect any flush and hold
    public void testFlush() {
        assertEquals("Flush", winners.get(4).getResult().getCombination());
    }

    @Test
//    For either player,  detect any straight  and hold
    public void testStraight() {
        assertEquals("Straight", winners.get(5).getResult().getCombination());
    }
}

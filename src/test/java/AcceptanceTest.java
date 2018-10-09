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
}

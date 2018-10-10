import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class BonusAcceptanceTest {

    @Test
    public void testComplex1Away() {
//        H2 H3 H4 H5 H6  vs  H5 D6 H6 H8 H9  exchange card: H7
        try {
            File file = new File("src/main/java/bonusInputCard.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            Game game = new Game(line);
            Player winner = game.playGame();
            assertEquals("One Card Away From Straight Flush", winner.getExchangeMatch());
            assertEquals("Straight Flush", winner.getResult().getCombination());
            assertEquals("AIP", winner.getName());
            fileReader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }

    }
}

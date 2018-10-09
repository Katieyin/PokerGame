import org.junit.After;
import org.junit.Test;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TwoOrMoreExchange {

    private int size;
    private boolean isMatched;

    private int getExchangeSize(Player player) {
        int score = player.evaluate();
        List<Integer> index = player.canExchange(score);
        return index.size();
    }

    @After
    public void checkExchangeSize() {
        assertTrue(this.isMatched);
    }

    @Test
    public void testOnePair() {
        Player testPlayer = new Player("S3 C10 H3 S7 D9");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isOnePair(testPlayer.getCards()).isMatched();
        assertEquals(3, this.size);
    }

    @Test
    public void testThreeWithSameSuit() {
        Player testPlayer = new Player("S3 C10 H3 S7 S9");
        this.size = this.getExchangeSize(testPlayer);
        List<Integer> index = testPlayer.getStrategy().isThreeOfSameSuit(testPlayer.getCards());
        this.isMatched = index.size() == 2;
        assertEquals(2, this.size);
    }

    @Test
    public void testThreeInSquence() {
        Player testPlayer = new Player("S3 C6 H8 S7 SJ");
        this.size = this.getExchangeSize(testPlayer);
        List<Integer> index = testPlayer.getStrategy().hasThreeCardInSequence(testPlayer.getCards());
        this.isMatched = index.size() == 2;
        assertEquals(2, this.size);
    }
}

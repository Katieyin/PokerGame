import org.junit.After;
import org.junit.Test;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class NoExchange {
    private int size;
    private boolean isMatched;

    private int getExchangeSize(Player player) {
        int score = player.evaluate();
        List<Integer> index = player.canExchange(score);
        return index.size();
    }

    @After
    public void checkExchangeSize() {
        assertEquals(0, this.size);
        assertTrue(this.isMatched);
    }

    @Test
    public void testRoyalFlush() {
        Player testPlayer = new Player("H1 HJ HK HQ H10");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isRoyalFlush(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testStraightFlush() {
        Player testPlayer = new Player("H4 H5 H6 H8 H7");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isStraightFlush(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testFourOfAKind() {
        Player testPlayer = new Player("H4 H5 S4 C4 D4");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isFourOfAKind(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testFullHouse() {
        Player testPlayer = new Player("S5 H5 S4 C4 D4");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isFullHouse(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testFlush() {
        Player testPlayer = new Player("S5 S10 S1 S7 S6");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isFlush(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testStraight() {
        Player testPlayer = new Player("S5 C4 H8 S7 D6");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isStraight(testPlayer.getCards()).isMatched();
    }


}

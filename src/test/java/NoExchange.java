import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class NoExchange {
    private boolean isMatched;

    @After
    public void checkExchangeSize() {
        assertTrue(this.isMatched);
    }

    @Test
    public void testStraight() {
        Player testPlayer = new Player("S5 C4 H8 S7 D6");
        this.isMatched = testPlayer.getStrategy().isStraight(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testFourOfAKind() {
        Player testPlayer = new Player("H4 H5 S4 C4 D4");
        this.isMatched = testPlayer.getStrategy().isFourOfAKind(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testFlush() {
        Player testPlayer = new Player("S5 S10 S1 S7 S6");
        this.isMatched = testPlayer.getStrategy().isFlush(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testFullHouse() {
        Player testPlayer = new Player("S5 H5 S4 C4 D4");
        this.isMatched = testPlayer.getStrategy().isFullHouse(testPlayer.getCards()).isMatched();
    }
}

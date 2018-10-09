import org.junit.After;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class OneExchange {
    private boolean isMatched;

    @After
    public void checkExchangeSize() {
        assertTrue(this.isMatched);
    }

    @Test
    public void testThreeOfAKind() {
        Player testPlayer = new Player("S3 C4 H3 S7 D3");
        this.isMatched = testPlayer.getStrategy().isThreeOfAKind(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testTwoPairs() {
        Player testPlayer = new Player("S3 C7 H3 S7 D9");
        this.isMatched = testPlayer.getStrategy().isTwoPairs(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testOneCardAwayFromRoyalFlush() {
        Player testPlayer = new Player("S10 C7 SJ SA SQ");
        List<Integer> index = testPlayer.getStrategy().isOneCardFromRoyalFlush(testPlayer.getCards());
        this.isMatched = index.size() == 1;
    }

    @Test
    public void testOneCardAwayFromFlush() {
        Player testPlayer = new Player("S10 C2 S3 SJ S7");
        List<Integer> index = testPlayer.getStrategy().isOneCardFromFlush(testPlayer.getCards());
        this.isMatched = index.size() == 1;
    }
}

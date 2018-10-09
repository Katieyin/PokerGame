import org.junit.After;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class OneExchange {
    private boolean isMatched;

    @After
    public void checkExchangeSize() {
        assertTrue(this.isMatched);
    }

    @Test
    public void testTwoPairs() {
        Player testPlayer = new Player("S3 C7 H3 S7 D9");
        this.isMatched = testPlayer.getStrategy().isTwoPairs(testPlayer.getCards()).isMatched();
    }
}

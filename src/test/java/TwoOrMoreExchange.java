import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TwoOrMoreExchange {
    private boolean isMatched;

    @Test
    public void testOnePair() {
        Player testPlayer = new Player("S3 C10 H3 S7 D9");
        this.isMatched = testPlayer.getStrategy().isOnePair(testPlayer.getCards()).isMatched();
        assertTrue(this.isMatched);
    }
}

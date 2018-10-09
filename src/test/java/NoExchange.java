import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class NoExchange {
    private boolean isMatched;

    @Test
    public void testStraight() {
        Player testPlayer = new Player("S5 C4 H8 S7 D6");
        this.isMatched = testPlayer.getStrategy().isStraight(testPlayer.getCards()).isMatched();
        assertTrue(this.isMatched);
    }
}

import org.junit.After;
import org.junit.Test;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class OneExchange {
    private int size;
    private boolean isMatched;

    private int getExchangeSize(Player player) {
        int score = player.evaluate();
        List<Integer> index = player.canExchange(score);
        return index.size();
    }

    @After
    public void checkExchangeSize() {
        assertEquals(1, this.size);
        assertTrue(this.isMatched);
    }

    @Test
    public void testThreeOfAKind() {
        Player testPlayer = new Player("S3 C4 H3 S7 D3");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isThreeOfAKind(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testTwoPairs() {
        Player testPlayer = new Player("S3 C7 H3 S7 D9");
        this.size = this.getExchangeSize(testPlayer);
        this.isMatched = testPlayer.getStrategy().isTwoPairs(testPlayer.getCards()).isMatched();
    }

    @Test
    public void testOneCardAwayFromRoyalFlush() {
        Player testPlayer = new Player("S10 C7 SJ SA SQ");
        this.size = this.getExchangeSize(testPlayer);
        List<Integer> index = testPlayer.getStrategy().isOneCardFromRoyalFlush(testPlayer.getCards());
        this.isMatched = index.size() == 1;
    }

    @Test
    public void testOneCardAwayFromStraightFlush() {
        Player testPlayer = new Player("S10 C2 S9 S6 S7");
        this.size = this.getExchangeSize(testPlayer);
        List<Integer> index = testPlayer.getStrategy().isOneCardFromStraightFlush(testPlayer.getCards());
        this.isMatched = index.size() == 1;
    }

    @Test
    public void testOneCardAwayFromFlush() {
        Player testPlayer = new Player("S10 C2 S3 SJ S7");
        this.size = this.getExchangeSize(testPlayer);
        List<Integer> index = testPlayer.getStrategy().isOneCardFromFlush(testPlayer.getCards());
        this.isMatched = index.size() == 1;
    }

    @Test
    public void testOneCardAwayFromStraight() {
        Player testPlayer = new Player("H10 C2 S9 D6 S7");
        this.size = this.getExchangeSize(testPlayer);
        List<Integer> index = testPlayer.getStrategy().isOneCardFromStraight(testPlayer.getCards());
        this.isMatched = index.size() == 1;
    }

    @Test
    public void testThreeWithSameRank() {
        Player testPlayer = new Player("S3 C3 H3 S7 S9");
        this.size = this.getExchangeSize(testPlayer);
        List<Integer> index = testPlayer.getStrategy().isThreeOfSameRank(testPlayer.getCards());
        this.isMatched = index.size() == 2;
    }
}

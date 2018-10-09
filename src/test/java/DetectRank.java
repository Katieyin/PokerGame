import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class DetectRank {
    @Test
    public void testSuitsRankedCorrectly() {
        Card card1 = new Card("H", 4);
        Card card2 = new Card("H", 5);
        Card card3 = new Card("S", 4);
        int lessThan = card1.compareTo(card2);
        int greaterThan = card2.compareTo(card3);
        int greaterThan2 = card3.compareTo(card1);
        assertTrue( lessThan < 0);
        assertTrue( greaterThan > 0);
        assertTrue( greaterThan2 > 0);
    }
}

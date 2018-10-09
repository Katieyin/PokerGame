import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class DetectRank {
    @Test
    public void testSuitsRankedCorrectly() {
        Card card1 = new Card("H", 4);
        Card card2 = new Card("D", 4);
        Card card3 = new Card("S", 4);
        Card card4 = new Card("C", 4);
        int greaterThan1 = card1.compareTo(card2);
        int greaterThan2 = card2.compareTo(card4);
        int lessThan1 = card1.compareTo(card3);
        int lessThan2 = card2.compareTo(card3);
        assertTrue( lessThan1 < 0);
        assertTrue( lessThan2 < 0);
        assertTrue( greaterThan1 > 0);
        assertTrue( greaterThan2 > 0);
    }

    @Test
    public void testCardsRankedCorrectly() {
        Card card1 = new Card("H", 4);
        Card card2 = new Card("H", 5);
        Card card3 = new Card("H", 3);
        int lessThan = card1.compareTo(card2);
        int greaterThan = card2.compareTo(card3);
        int lessThan2 = card3.compareTo(card1);
        assertTrue( lessThan < 0);
        assertTrue( greaterThan > 0);
        assertTrue( lessThan2 < 0);

    }

    @Test
    public void testPlayerSortCardCorrectly(){
        Player testPlayer = new Player("H2 H5 H6 H3 H4");
        testPlayer.sortCards();
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("H", 3));
        cards.add(new Card("H", 4));
        cards.add(new Card("H", 5));
        cards.add(new Card("H", 6));
        for(int i = 0; i < cards.size(); i++){
            assertEquals(cards.get(i).getRank(), testPlayer.getCards().get(i).getRank());
        }
    }

    @Test
    public void testHandsWithoutExhcange() {
        Player testPlayer = new Player("H1 HJ HK HQ H10");
        boolean isMatched = testPlayer.getStrategy().isRoyalFlush(testPlayer.getCards()).isMatched();
        assertTrue(isMatched);
    }

    @Test
    public void testHandsWithExhcange() {
        Game game = new Game("HA HJ H10 HK HQ SA SJ S10 SK C9 SQ");
        Player winner = game.playGame();
        assertEquals("One Card Away From Royal Flush", winners.get(0).getExchangeMatch());
        assertEquals("Royal Flush", winners.get(0).getResult().getCombination());
        assertEquals("AIP", winners.get(0).getName());
    }
}

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AcceptanceTest {
    static List<Player> winners;

    @BeforeClass
    public static void getGameResults() {
        winners = new ArrayList<Player>();
        try {
            File file = new File("src/main/java/inputCards.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Game game = new Game(line);
                Player winner = game.playGame();
                winners.add(winner);
            }
            fileReader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    @Test
//    For either player,  detect any royal flush and hold
    public void testRoyalFlush() {
        assertEquals("Royal Flush", winners.get(0).getResult().getCombination());
    }

    @Test
//    For either player,  detect any straight flush and hold
    public void testStraightFlush() {
        assertEquals("Straight Flush", winners.get(1).getResult().getCombination());
    }

    @Test
//    For either player,  detect any 4 of a kind and hold
    public void testFourOfAKind() {
        assertEquals("4 Of A Kind", winners.get(2).getResult().getCombination());
    }

    @Test
//    For either player,  detect any full house and hold
    public void testFullHouse() {
        assertEquals("Full House", winners.get(3).getResult().getCombination());
    }

    @Test
//    For either player,  detect any flush and hold
    public void testFlush() {
        assertEquals("Flush", winners.get(4).getResult().getCombination());
    }

    @Test
//    For either player,  detect any straight  and hold
    public void testStraight() {
        assertEquals("Straight", winners.get(5).getResult().getCombination());
    }

    @Test
//    For either player,  detect  you are 1 card away from a royal flush and exchange that card
    public void testOneCardAwayFromRoyalFlush() {
        assertEquals("One Card Away From Royal Flush", winners.get(0).getExchangeMatch());
        assertEquals("Royal Flush", winners.get(0).getResult().getCombination());
        assertEquals("AIP", winners.get(0).getName());
    }

    @Test
//    For either player,  detect you are 1 card away from a straight flush and exchange that card
    public void testOneCardAwayFromStraightFlush() {
        assertEquals("One Card Away From Straight Flush", winners.get(1).getExchangeMatch());
        assertEquals("Straight Flush", winners.get(1).getResult().getCombination());
        assertEquals("AIP", winners.get(1).getName());
    }

    @Test
//    For either player,  detect you are 1 card away from a full house and exchange a card
    public void testOneCardAwayFromFullHouse() {
        assertEquals("One Card Away From Full House", winners.get(3).getExchangeMatch());
        assertEquals("Full House", winners.get(3).getResult().getCombination());
        assertEquals("AIP", winners.get(3).getName());
    }

    @Test
//    For either player,  detect you are 1 card away from a  flush and exchange that card
    public void testOneCardAwayFromFlush() {
        assertEquals("One Card Away From Flush", winners.get(4).getExchangeMatch());
        assertEquals("Flush", winners.get(4).getResult().getCombination());
        assertEquals("AIP", winners.get(4).getName());
    }

    @Test
//    For either player,  detect you are 1 card away from a straight and exchange that card
    public void testOneCardAwayFromStraight() {
        assertEquals("One Card Away From Straight", winners.get(5).getExchangeMatch());
        assertEquals("Straight", winners.get(5).getResult().getCombination());
        assertEquals("AIP", winners.get(5).getName());
    }

    @Test
//    For either player,   detect 3 cards of the same suit & exchange other two
    public void testThreeOfSameSuit() {
        assertEquals("Three Of The Same Suit", winners.get(6).getExchangeMatch());
        assertEquals("Flush", winners.get(6).getResult().getCombination());
    }

    @Test
//    For either player,  detect 3 cards in sequence & exchange other two
    public void testThreeInSequence() {
        assertEquals("Three Cards In Sequence", winners.get(7).getExchangeMatch());
        assertEquals("Straight", winners.get(7).getResult().getCombination());
    }

    @Test
//    For either player,  detect a single pair and exchange the other 3 cards
    public void testHasOnePair() {
        assertEquals("Has One Pair", winners.get(8).getExchangeMatch());
        assertEquals("Full House", winners.get(8).getResult().getCombination());
    }

    @Test
//    If none of the above, then keep highest 2 and exchange 3 others
    public void testNoStrategy() {
        assertEquals("No Strategy", winners.get(9).getExchangeMatch());
    }


//  ------------------WINNING-------------------------

    @Test
//    Royal flush beats any other hand (Straight Flush vs Royal Flush)
    public void testRoyalFlushWins() {
        assertEquals("Royal Flush", winners.get(10).getResult().getCombination());
        assertEquals("AIP", winners.get(10).getName());
    }

    @Test
//    Straight flush beats any hand but royal flush (Four of a Kind vs Straight Flush)
    public void testStraightFlushWins() {
        assertEquals("Straight Flush", winners.get(11).getResult().getCombination());
        assertEquals("AIP", winners.get(11).getName());
    }

    @Test
//    4-of-a-kind beats any hand but royal flush and Straight flush (Full House vs Four of a Kind)
    public void testFourOfAKindWins() {
        assertEquals("4 Of A Kind", winners.get(12).getResult().getCombination());
        assertEquals("AIP", winners.get(12).getName());
    }

    @Test
//    Full house beats any hand but royal flush, straight flush and 4-of-a-kind (Flush vs Full House)
    public void testFullHouseWins() {
        assertEquals("Full House", winners.get(13).getResult().getCombination());
        assertEquals("AIP", winners.get(13).getName());
    }

    @Test
//    Flush beats straight, 3-of-a-kind, two pairs, one pair, and high card (Straight vs Flush)
    public void testFlushWins() {
        assertEquals("Flush", winners.get(14).getResult().getCombination());
        assertEquals("AIP", winners.get(14).getName());
    }

    @Test
//    Straight beats 3-of-a-kind, two pairs, one pair, and high card (Three of a kind vs Straight)
    public void testStraightWins() {
        assertEquals("Straight", winners.get(15).getResult().getCombination());
        assertEquals("AIP", winners.get(15).getName());
    }

    @Test
//    3-of-a-kind, beats two pairs, one pair, and high card (Two Pairs vs Three of a kind)
    public void testThreeOfAKindWins() {
        assertEquals("3 Of A Kind", winners.get(16).getResult().getCombination());
        assertEquals("AIP", winners.get(16).getName());
    }

    @Test
//    Two pairs beats one pair and high cardr (One Pair vs Two Pairs)
    public void testTwoPairsWins() {
        assertEquals("Two Pairs", winners.get(17).getResult().getCombination());
        assertEquals("AIP", winners.get(17).getName());
    }

    @Test
//    One pair beats high card (Highest card vs One Pair)
    public void testOnePairWins() {
        assertEquals("One Pair", winners.get(18).getResult().getCombination());
        assertEquals("AIP", winners.get(18).getName());
    }
}

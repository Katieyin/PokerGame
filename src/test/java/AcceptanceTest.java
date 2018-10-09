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

    @Test
//    straight flush with the same highest card, then highest suit wins
    public void testHigherStraightFlushWins() {
        assertEquals("Straight Flush", winners.get(19).getResult().getCombination());
        assertEquals("AIP", winners.get(19).getName());
    }

    @Test
//    three-of-a-kind, the highest card of the 2 triplets wins
    public void testHigherThreeOfAKindWins() {
        assertEquals("3 Of A Kind", winners.get(20).getResult().getCombination());
        assertEquals("AIP", winners.get(20).getName());
    }

    @Test
//    two straights with the same rank for their highest card, then highest suit of that highest card win
    public void testHigherSuitStraightWins() {
        assertEquals("Straight", winners.get(21).getResult().getCombination());
        assertEquals("AIP", winners.get(21).getName());
    }

    @Test
//    two pairs with the same highest pair for each participant, then highest suit of this highest pair wins
    public void testHigherTwoPairsWins() {
        assertEquals("Two Pairs", winners.get(22).getResult().getCombination());
        assertEquals("AIP", winners.get(22).getName());
    }

    @Test
//    two pairs, with distinct highest pairs for the 2 participants, then highest pair wins
    public void testHigherRankTwoPairsWins() {
        assertEquals("Two Pairs", winners.get(23).getResult().getCombination());
        assertEquals("AIP", winners.get(23).getName());
    }

    @Test
//    one pair, with both of these pairs of equal rank, then highest suit of these pairs wins,
//    one pair, with distinct-ranked pairs for the 2 participants,
//    then pair with highest card wins
    public void testHigherOnePairWins() {
        assertEquals("One Pair", winners.get(24).getResult().getCombination());
        assertEquals("AIP", winners.get(24).getName());
    }

    @Test
//    high card hand with same rank for the highest card of each participant, then highest suit of the highest card win
    public void testHigherSuitCardWins() {
        assertEquals("Highest Card", winners.get(25).getResult().getCombination());
        assertEquals("AIP", winners.get(25).getName());
    }

    @Test
//    high card hand with distinct highest card for each participant, then highest ranked card wins
    public void testHigherRankCardWins() {
        assertEquals("Highest Card", winners.get(26).getResult().getCombination());
        assertEquals("AIP", winners.get(26).getName());
    }

    @Test
    public void testHigher5FlushWins() {
        assertEquals("Flush", winners.get(27).getResult().getCombination());
        assertEquals("AIP", winners.get(27).getName());
    }

    @Test
    public void testHigher4FlushWins() {
        assertEquals("Flush", winners.get(28).getResult().getCombination());
        assertEquals("AIP", winners.get(28).getName());
    }

    @Test
    public void testHigher3FlushWins() {
        assertEquals("Flush", winners.get(29).getResult().getCombination());
        assertEquals("AIP", winners.get(29).getName());
    }

    @Test
    public void testHigher2FlushWins() {
        assertEquals("Flush", winners.get(30).getResult().getCombination());
        assertEquals("AIP", winners.get(30).getName());
    }

    @Test
    public void testHigher1FlushWins() {
        assertEquals("Flush", winners.get(31).getResult().getCombination());
        assertEquals("AIP", winners.get(31).getName());
    }

//  ------------------BONUS-------------------------
    @Test
    public void testComplex1Away() {
//        H2 H3 H4 H5 H6  vs  H5 D6 H6 H8 H9  exchange card: H7
        assertEquals("One Card Away From Straight Flush", winners.get(32).getExchangeMatch());
        assertEquals("Straight Flush", winners.get(32).getResult().getCombination());
        assertEquals("AIP", winners.get(32).getName());
    }
}

import java.util.*;

public class Strategy {
    private Result result;

    public void sortCards(List<Card> cards) {
        Collections.sort(cards);
    }

    public Card getHighestCard(List<Card> cards) {
        this.sortCards(cards);
        return cards.get(cards.size() - 1);
    }

    public Result isStraight(List<Card> cards) {
        this.sortCards(cards);
        Card highestCard = this.getHighestCard(cards);
        boolean isMatched = true;
        if (cards == null || cards.size() != 5) {
            isMatched = false;
        } else {
            if (cards.get(4).getRank() == 1) {
                if (cards.get(0).getRank() == 10) {
                    for (int i = 1; i < cards.size() - 1; i++) {
                        if (cards.get(i).getRank() - cards.get(i - 1).getRank() != 1) {
                            isMatched = false;
                        }
                    }
                } else if (cards.get(0).getRank() == 2) {
                    for (int i = 1; i < cards.size() - 1; i++) {
                        if (cards.get(i).getRank() - cards.get(i - 1).getRank() != 1) {
                            isMatched = false;
                        }
                    }
                } else {
                    isMatched = false;
                }
            } else {
                int prev = -1;
                for (Card card : cards) {
                    if (prev == -1 || (prev + 1) == card.getRank()) {
                        prev = card.getRank();
                    } else {
                        isMatched = false;
                    }
                }
            }
        }
        Result result = new Result(highestCard, isMatched, "Straight");
        return result;
    }


}

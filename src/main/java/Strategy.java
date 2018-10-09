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

    public Result isNumberOfAKind(List<Card> cards, int number) {
        this.sortCards(cards);
        int pairCount = 0;
        Card highestCard = cards.get(0);
        Result result = new Result(highestCard, false, "Highest Card");
        if (cards == null || cards.size() != 5) {
            return result;
        } else {
            for (int i = 0; i < cards.size(); i++) {
                pairCount = 0;
                for (int j = 0; j < cards.size(); j++) {
                    if (cards.get(j).getRank() == cards.get(i).getRank()) {
                        pairCount++;
                    }
                }
                if (pairCount == number) {
                    result.setMatched(true);
                    if (highestCard.getRank() == 1 && highestCard.getRank() != cards.get(i).getRank()) {
                        highestCard = cards.get(i);
                    }
                    if (cards.get(i).isGreaterThan(highestCard)) {
                        highestCard = cards.get(i);
                    }
                    result.setHighestCard(highestCard);
                    if (number == 2) {
                        result.setCombination("One Pair");
                    } else {
                        result.setCombination(number + " Of A Kind");
                    }
                }
            }
        }
        return result;
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

    public Result isOnePair(List<Card> cards) {
        this.sortCards(cards);
        return isNumberOfAKind(cards, 2);
    }

    public Result isTwoPairs(List<Card> cards) {
        this.sortCards(cards);
        int pair = 0;
        int pairCounter = 0;
        Card highestCard = cards.get(0);
        boolean isMatched = true;
        if (isFourOfAKind(cards).isMatched()) {
            isMatched = false;
        } else {
            for (int i = 0; i < cards.size(); i++) {
                for (int j = 0; j < cards.size(); j++) {
                    if (cards.get(i).getRank() == cards.get(j).getRank()) {
                        pairCounter++;
                    }
                }
                if (pairCounter > 1) {
                    pair++;
                    if (cards.get(i).isGreaterThan(highestCard)) {
                        highestCard = cards.get(i);
                    }
                }
                pairCounter = 0;
            }
            isMatched = pair == 4;
        }
        Result result = new Result(highestCard, isMatched, "Two Pairs");
        return result;
    }

    public Result isFourOfAKind(List<Card> cards) {
        this.sortCards(cards);
        return isNumberOfAKind(cards, 4);
    }

    public Result isThreeOfAKind(List<Card> cards) {
        this.sortCards(cards);
        return isNumberOfAKind(cards, 3);
    }

    public Result isFlush(List<Card> cards) {
        this.sortCards(cards);
        Card highestCard = this.getHighestCard(cards);
        boolean isMatched = true;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getSuit().compareTo(cards.get(i).getSuit()) != 0) {
                isMatched = false;
            }
        }
        Result result = new Result(highestCard, isMatched, "Flush");
        return result;
    }

    public Result isFullHouse(List<Card> cards) {
        this.sortCards(cards);
        boolean isMatched;
        if (isNumberOfAKind(cards, 3).isMatched() && isNumberOfAKind(cards, 2).isMatched() &&
                isNumberOfAKind(cards, 2).getHighestCard() != isNumberOfAKind(cards, 3).getHighestCard()) {
            isMatched = true;
        } else {
            isMatched = false;
        }
        Result result = new Result(isNumberOfAKind(cards, 3).getHighestCard(), isMatched, "Full House");
        return result;
    }

    public Result isStraightFlush(List<Card> cards) {
        this.sortCards(cards);
        Card highestCard = this.getHighestCard(cards);
        boolean isMatched;
        if (isStraight(cards).isMatched() && isFlush(cards).isMatched()) {
            isMatched = true;
        } else {
            isMatched = false;
        }
        Result result = new Result(highestCard, isMatched, "Straight Flush");
        return result;
    }

    public Result isRoyalFlush(List<Card> cards) {
        this.sortCards(cards);
        Card highestCard = this.getHighestCard(cards);
        boolean isMatched = false;
        int count = 0;
        List<Integer> royalFlush = new ArrayList<Integer>(Arrays.asList(10, 11, 12, 13, 1));
        if (isStraightFlush(cards).isMatched()) {
            for (Card card : cards) {
                if (royalFlush.indexOf(card.getRank()) >= 0) {
                    count++;
                }
            }
            if (count == 5) {
                isMatched = true;
            }
        }
        Result result = new Result(highestCard, isMatched, "Royal Flush");
        return result;

    }

}

import java.util.*;

public class Strategy {

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

    public List<Integer> findMatch(List<Integer> sequence, List<Card> cards) {
        this.sortCards(cards);
        List<Integer> index = new ArrayList<Integer>();
        for (int i = 4; i >= 0; i--) {
            if (sequence.indexOf(cards.get(i).getRank()) >= 0) {
                sequence.remove(sequence.indexOf(cards.get(i).getRank()));
            } else {
                index.add(i);
            }
        }
        return index;
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

    public List<Integer> isOneCardFromRoyalFlush(List<Card> cards) {
        this.sortCards(cards);
        List<Integer> royalFlush = new ArrayList<Integer>(Arrays.asList(10, 11, 12, 13, 1));
        List<Integer> index = new ArrayList<Integer>();
        int count = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (royalFlush.indexOf(cards.get(i).getRank()) >= 0) {
                count++;
            } else {
                index.add(i);
            }
        }
        if (count == 4 && (this.isFlush(cards.subList(0, 4)).isMatched() || this.isFlush(cards.subList(1, 5)).isMatched())) {
            return index;
        } else {
            index.clear();
            return index;
        }
    }

    public List<Integer> isOneCardFromFlush(List<Card> cards) {
        this.sortCards(cards);
        List<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < cards.size(); i++) {
            int count = 0;
            index.clear();
            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(i).getSuit().equals(cards.get(j).getSuit())) {
                    count++;
                } else {
                    index.add(j);
                }
            }
            if (count == 4) {
                return index;
            }
        }
        index.clear();
        return index;
    }

    public List<Integer> isOneCardFromStraight(List<Card> cards) {
        this.sortCards(cards);
        int rank1 = cards.get(0).getRank();
        int rank2 = cards.get(1).getRank();
        List<Integer> straight1 = new ArrayList<Integer>(Arrays.asList(rank1, rank1 + 1, rank1 + 2, rank1 + 3, rank1 + 4));
        List<Integer> straight2 = new ArrayList<Integer>(Arrays.asList(rank2, rank2 + 1, rank2 + 2, rank2 + 3, rank2 + 4));
        List<Integer> specialStraight = new ArrayList<Integer>(Arrays.asList(10, 11, 12, 13, 1));

        List<Integer> index = new ArrayList<Integer>();

        List<Integer> a = findMatch(straight1, cards);
        List<Integer> b = findMatch(straight2, cards);
        List<Integer> c = findMatch(specialStraight, cards);
        if (specialStraight.size() == 1) {
            return c;
        } else if (straight2.size() == 1) {
            return b;
        } else if (straight1.size() == 1) {
            return a;
        } else {
            return index;
        }
    }

    public List<Integer> isOneCardFromStraightFlush(List<Card> cards) {
        this.sortCards(cards);
        List<Integer> straightIndex = new ArrayList<Integer>();
        List<Integer> flushIndex = new ArrayList<Integer>();
        straightIndex = isOneCardFromStraight(cards);
        flushIndex = isOneCardFromFlush(cards);
        if (straightIndex.size() == 1 && flushIndex.size() == 1 && straightIndex.get(0).equals(flushIndex.get(0))) {
            return straightIndex;
        } else {
            straightIndex.clear();
            return straightIndex;
        }
    }

    public List<Integer> isOneCardFromFullHouse(List<Card> cards) {
        this.sortCards(cards);
        List<Integer> index = new ArrayList<Integer>();
        if (this.isThreeOfSameRank(cards).size() == 2) {
            index.add(this.isThreeOfSameRank(cards).get(0));
        } else if (this.hasTwoPairs(cards) >= 0) {
            index.add(this.hasTwoPairs(cards));
        } else {
            index.clear();
        }
        return index;
    }

    public List<Integer> isThreeOfSameRank(List<Card> cards) {
        this.sortCards(cards);
        List<Integer> index = new ArrayList<Integer>();
        if (this.isThreeOfAKind(cards).isMatched()) {
            for (int i = 0; i < cards.size(); i++) {
                if ((cards.get(i).getRank() != this.isThreeOfAKind(cards).getHighestCard().getRank())) {
                    index.add(i);
                }
            }
        }
        if (index.size() == 2) {
            return index;
        } else {
            index.clear();
            return index;
        }
    }

    public int hasTwoPairs(List<Card> cards) {
        this.sortCards(cards);
        int index = -1;
        if (this.isTwoPairs(cards).isMatched()) {
            for (int i = 0; i < cards.size(); i++) {
                int count = 0;
                for (int j = 0; j < cards.size(); j++) {
                    if (cards.get(j).getRank() == cards.get(i).getRank()) {
                        count++;
                    }
                }
                if (count == 1) {
                    return i;
                }
            }
        }
        return index;
    }

    public List<Integer> isThreeOfSameSuit(List<Card> cards) {
        this.sortCards(cards);
        List<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < cards.size(); i++) {
            int count = 0;
            index.clear();
            for (int j = 0; j < cards.size(); j++) {
                if (cards.get(i).getSuit().equals(cards.get(j).getSuit())) {
                    count++;
                } else {
                    index.add(j);
                }
            }
            if (count == 3) {
                return index;
            } else {
                index.clear();
            }
        }
        return index;
    }

}

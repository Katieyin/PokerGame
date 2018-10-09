import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private List<Card> cards;
    private String name;
    private Result result;
    private Strategy strategy;
    private String exchangeMatch;

    public Player(String name, Strategy strategy) {
        this.cards = new ArrayList<Card>();
        this.name = name;
        this.strategy = strategy;
    }

    public Player(String inputCards) {
        this.strategy = new Strategy();
        this.name = "Test Player";
        this.cards = new ArrayList<Card>();
        String[] cards = inputCards.split("\\s+");
        for (int i = 0; i < 5; i++) {
            String[] inputCard = cards[i].split("");
            Card card = new Card(inputCard);
            this.cards.add(card);
        }
    }

    public List<Card> getCards() { return cards; }

    public void setCards(List<Card> cards) { this.cards = cards; }

    public void sortCards() {
        Collections.sort(this.cards);
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Result getResult() { return result; }

    public void setResult(Result result) { this.result = result; }

    public Strategy getStrategy() { return strategy; }

    public void setStrategy(Strategy strategy) { this.strategy = strategy; }

    public String getExchangeMatch() { return exchangeMatch; }

    public void setExchangeMatch(String exchangeMatch) { this.exchangeMatch = exchangeMatch; }

    public void printCards() {
        System.out.print(this.getName() + " has: ");
        for (Card card : this.cards) {
            card.printCard();
            System.out.print(" ");
        }
        System.out.println(" ");
    }

    public int evaluate() {
        int score = 0;
        if (this.strategy.isRoyalFlush(cards).isMatched()) {
            this.setResult(this.strategy.isRoyalFlush(cards));
            score = 10;
        } else if (this.strategy.isStraightFlush(cards).isMatched()) {
            this.setResult(this.strategy.isStraightFlush(cards));
            score = 9;
        } else if (this.strategy.isFourOfAKind(cards).isMatched()) {
            this.setResult(this.strategy.isFourOfAKind(cards));
            score = 8;
        } else if (this.strategy.isFullHouse(cards).isMatched()) {
            this.setResult(this.strategy.isFullHouse(cards));
            score = 7;
        } else if (this.strategy.isFlush(cards).isMatched()) {
            this.setResult(this.strategy.isFlush(cards));
            score = 6;
        } else if (this.strategy.isStraight(cards).isMatched()) {
            this.setResult(this.strategy.isStraight(cards));
            score = 5;
        } else if (this.strategy.isThreeOfAKind(cards).isMatched()) {
            this.setResult(this.strategy.isThreeOfAKind(cards));
            score = 4;
        } else if (this.strategy.isTwoPairs(cards).isMatched()) {
            this.setResult(this.strategy.isTwoPairs(cards));
            score = 3;
        } else if (this.strategy.isOnePair(cards).isMatched()) {
            this.setResult(this.strategy.isOnePair(cards));
            score = 2;
        } else {
            Result result = new Result(this.strategy.getHighestCard(cards), false, "Highest Card");
            this.setResult(result);
            score = 1;
        }
//        System.out.println(score);
        this.result.printCombination();
        return score;
    }

    public List<Integer> canExchange(int score) {
        List<Integer> index = new ArrayList<Integer>();
        this.sortCards();
        if (score >= 5) {
            return index;
        }
        System.out.print("Detected AIP is: ");
        if (this.strategy.isOneCardFromRoyalFlush(cards).size() == 1) {
            this.setExchangeMatch("One Card Away From Royal Flush");
            System.out.println(this.getExchangeMatch());
            return this.strategy.isOneCardFromRoyalFlush(cards);
        } else if (this.strategy.isOneCardFromStraightFlush(cards).size() == 1) {
            this.setExchangeMatch("One Card Away From Straight Flush");
            System.out.println(this.getExchangeMatch());
            return this.strategy.isOneCardFromStraightFlush(cards);
        } else if (this.strategy.isOneCardFromFullHouse(cards).size() == 1) {
            this.setExchangeMatch("One Card Away From Full House");
            System.out.println(this.getExchangeMatch());
            return this.strategy.isOneCardFromFullHouse(cards);
        } else if (this.strategy.isOneCardFromFlush(cards).size() == 1) {
            this.setExchangeMatch("One Card Away From Flush");
            System.out.println(this.getExchangeMatch());
            return this.strategy.isOneCardFromFlush(cards);
        } else if (this.strategy.isOneCardFromStraight(cards).size() == 1) {
            this.setExchangeMatch("One Card Away From Straight");
            System.out.println(this.getExchangeMatch());
            return this.strategy.isOneCardFromStraight(cards);
        } else if (this.strategy.isThreeOfSameSuit(cards).size() == 2) {
            this.setExchangeMatch("Three Of The Same Suit");
            System.out.println(this.getExchangeMatch());
            return this.strategy.isThreeOfSameSuit(cards);
        } else if (this.strategy.hasThreeCardInSequence(cards).size() == 2) {
            this.setExchangeMatch("Three Cards In Sequence");
            System.out.println(this.getExchangeMatch());
            return this.strategy.hasThreeCardInSequence(cards);
        } else if (this.strategy.hasOnePair(cards).size() == 3) {
            this.setExchangeMatch("Has One Pair");
            System.out.println(this.getExchangeMatch());
            return this.strategy.hasOnePair(cards);
        }
        this.setExchangeMatch("No Strategy");
        System.out.println(this.getExchangeMatch());
        index.add(0);
        index.add(1);
        index.add(2);
        return index;
    }
}

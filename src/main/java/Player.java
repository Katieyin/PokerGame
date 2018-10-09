import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private List<Card> cards;
    private String name;
    private Result result;
    private Strategy strategy;

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
}

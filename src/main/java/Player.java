import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private List<Card> cards;

    public Player() {
        this.cards = new ArrayList<Card>();
    }

    public Player(String inputCards) {
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


}

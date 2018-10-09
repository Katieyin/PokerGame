import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private List<Card> exchangeCards;

    public Game(String inputCards) {
        this.players = new ArrayList<Player>();
        this.exchangeCards = new ArrayList<Card>();
        String[] cards = inputCards.split("\\s+");
        Strategy strategy = new Strategy();
        Player player1 = new Player("Tom", strategy);
        Player player2 = new Player("AIP", strategy);
        this.players.add(player1);
        this.players.add(player2);

        for (int i = 0; i < 5; i++) {
            String[] inputCard = cards[i].split("");
            Card card = new Card(inputCard);
            player1.addCard(card);
        }
        for (int i = 5; i < 10; i++) {
            String[] inputCard = cards[i].split("");
            Card card = new Card(inputCard);
            player2.addCard(card);
        }
        if (cards.length > 10) {
            for (int i = 10; i < cards.length; i++) {
                String[] inputCard = cards[i].split("");
                Card card = new Card(inputCard);
                this.exchangeCards.add(card);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getExchangeCards() {
        return exchangeCards;
    }

    public void setExchangeCards(List<Card> exchangeCards) {
        this.exchangeCards = exchangeCards;
    }


}

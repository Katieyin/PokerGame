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

    public Player findWinner(int score1, int score2) {
        Player winner;
        if (score1 > score2) {
            winner = this.players.get(0);
        } else if (score1 == score2) {
            winner = this.players.get(0).getResult().getHighestCard().isGreaterThan(this.players.get(1).getResult().getHighestCard())
                    ? this.players.get(0) : this.players.get(1);
        } else {
            winner = this.players.get(1);
        }
        System.out.println("Winner is: " + winner.getName());
        return winner;
    }

    public void changeCards(Player player, List<Integer> index) {
        for (int i = 0; i < index.size(); i++) {
            player.discardCard(index.get(i));
            player.exchangeCard(this.exchangeCards.get(i), index.get(i));
        }
        player.sortCards();
    }

    public Player playGame() {
        Player winner;
        System.out.println("------Starting A New Game-----");

        this.players.get(0).printCards();
        int score1 = this.players.get(0).evaluate();
        this.players.get(1).printCards();
        int score2 = this.players.get(1).evaluate();

        winner = this.findWinner(score1, score2);

        List<Integer> index = this.players.get(1).canExchange(score2);
        if (index.size() > 0) {
            System.out.println(this.players.get(1).getName() + " is exchanging cards...");
            this.changeCards(this.players.get(1), index);
            System.out.print("After exchanging cards, ");
            this.players.get(1).printCards();
            score2 = this.players.get(1).evaluate();
            winner = this.findWinner(score1, score2);
        }
        System.out.println("------Good Bye-----");
        System.out.println(" ");
        return winner;

    }
}

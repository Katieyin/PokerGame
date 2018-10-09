public class Card implements Comparable<Card> {
    private String suit;
    private int rank;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int compareTo(Card card) {
        if (this.getRank() == 1 && card.getRank() != 1)
            return 1;
        else if (this.getRank() != 1 && card.getRank() == 1)
            return -1;
        else if (this.getRank() > card.getRank())
            return 1;
        else if (this.getRank() < card.getRank())
            return -1;
        else
            return this.getSuit().compareTo(card.getSuit());
    }
}
public class Card implements Comparable<Card> {
    private String suit;
    private int rank;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(String[] card) {
        this.suit = card[0];
        if (card.length == 3) {
            this.rank = 10;
        } else if (card[1].equals("J")) {
            this.rank = 11;
        } else if (card[1].equals("Q")) {
            this.rank = 12;
        } else if (card[1].equals("K")) {
            this.rank = 13;
        } else if (card[1].equals("A")) {
            this.rank = 1;
        } else {
            this.rank = Integer.parseInt(card[1]);
        }
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


    public boolean isGreaterThan(Card card) {
        return this.compareTo(card) > 0;
    }

    public void printCard() {
        switch (this.rank) {
            case 1:
            case 14:
                System.out.print(this.suit + "A");
                break;
            case 11:
                System.out.print(this.suit + "J");
                break;
            case 12:
                System.out.print(this.suit + "Q");
                break;
            case 13:
                System.out.print(this.suit + "K");
                break;
            default:
                System.out.print(this.suit + this.rank);
                break;

        }

    }
}
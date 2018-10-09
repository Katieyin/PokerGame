public class Result {
    private Card highestCard;
    private boolean isMatched;
    private String combination;

    public Result(Card highest, boolean isMatched, String combination) {
        this.highestCard = highest;
        this.isMatched = isMatched;
        this.combination = combination;
    }

    public Card getHighestCard() {
        return highestCard;
    }

    public void setHighestCard(Card highest) {
        this.highestCard = highest;
    }


    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) { this.combination = combination; }

    public void printCombination() {
        System.out.println("Combination: " + this.combination);
    }



}

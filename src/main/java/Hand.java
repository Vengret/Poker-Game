import java.util.List;

public class Hand {
    List<Card> handCards;
    Rank rank;

    public Hand(List<Card> playersCards){
        this.handCards = playersCards;
        this.rank = new Rank(this.handCards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "handCards=" + handCards +
                ", \nrank=" + rank +
                '}';
    }
}

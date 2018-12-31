import java.util.ArrayList;
import java.util.List;

public class Dealer {
    Deck deck = new Deck();

    public Hand dealHand(int handSize){
        Hand playersHand = null;
        List<Card> playersCards = new ArrayList();

        for(int i = 0; i < handSize; ++i){
            Card newCard = this.deck.dealCard();
            playersCards.add(newCard);
        }

        playersHand = new Hand(playersCards);

        return playersHand;
    }

}

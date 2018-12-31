import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> deck;

    public Deck(){
        this.deck = generateDeck();
    }

    private List<Card> generateDeck(){
        List<Card> cardList = new ArrayList();
        generateSuite(Suite.S, cardList);
        generateSuite(Suite.C, cardList);
        generateSuite(Suite.D, cardList);
        generateSuite(Suite.H, cardList);

        Collections.shuffle(cardList);

        return cardList;
    }

    private List<Card> generateSuite(Suite suite, List<Card> cardList){
        for(int i = 2; i <= 14; ++i){
            Card card = new Card(i, suite);
            cardList.add(card);
        }
        return cardList;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }

    public Card dealCard(){
        Card cardDealt = this.deck.get(0);
        this.deck.remove(0);
        return cardDealt;
    }
}

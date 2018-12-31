import java.util.Comparator;

public class Card{
    private int value;
    private String face;
    private Suite suite;

    // Empty constructor
    public Card(){}

    // Default constructor
    public Card(int value, Suite suite) {
        this.value = value;
        this.suite = suite;

        // Applying face to value to make the face cards more user friendly
        switch (value) {
            case 11:
                this.face = "J";
                break;
            case 12:
                this.face = "Q";
                break;
            case 13:
                this.face = "K";
                break;
            case 14:
                this.face = "A";
                break;
            default:
                this.face = Integer.toString(value);

        }

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    @Override
    public String toString() {
        return "Card{" +
                "face=" + face +
                ", suite=" + suite +
                '}'
                + "\n";
    }

    public static Comparator<Card> sortSuite = new Comparator<Card>(){
        public int compare(Card c1, Card c2){
            Suite c1Suite = c1.getSuite();
            Suite c2Suite = c2.getSuite();

            return c1Suite.compareTo(c2Suite);
        }
    };

    public static Comparator<Card> sortValue = new Comparator<Card>(){
        public int compare(Card c1, Card c2){
            int c1Value = c1.getValue();
            int c2Value = c2.getValue();

            return c2Value-c1Value;
        }
    };

}
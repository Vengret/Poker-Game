public class Main {

    private static boolean declareWinner(Hand hand1, Hand hand2) {
        boolean isWinner = false;

        for (int i = 0; i < hand1.rank.getTiebreaker().size(); ++i) {
            if (hand1.rank.getTiebreaker().get(i) > hand2.rank.getTiebreaker().get(i)) {
                System.out.println("Player 1 is the winner!");
                isWinner = true;
                return isWinner;
            } else if (hand2.rank.getTiebreaker().get(i) > hand1.rank.getTiebreaker().get(i)) {
                System.out.println("Player 2 is the winner!");
                isWinner = true;
                return isWinner;
            }
        }

        return isWinner;
    }

    public static void main(String[] args) {

        Dealer dealer = new Dealer();

        boolean isWinner = false;

        Hand hand1 = dealer.dealHand(5);
        Hand hand2 = dealer.dealHand(5);

        System.out.println(hand1.rank);
        System.out.println("\n");
        System.out.println(hand2.rank);
        System.out.println("\n");

        isWinner = declareWinner(hand1, hand2);

        if(!isWinner){
            System.out.println("There was no winner. Pot split.");
        }

    }
}
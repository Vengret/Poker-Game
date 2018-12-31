import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rank {

    private List<Card> currentHand;

    private List<Integer> tiebreaker = new ArrayList();

    public Rank(List<Card> hand){
        sortValues(hand);
        setRankings(hand);
    }

    private void setRankings(List<Card> handToRank){
        try {
            if (isFlush(handToRank) && isStraight(handToRank)) {
                // Check for straight flush
                this.tiebreaker.add(0,9);
                return;
            } else if (hasFourOfAKind(handToRank)) {
                // Check for four of a kind
                this.tiebreaker.add(0,8);
                setHighest(handToRank, this.tiebreaker.get(1));
                return;
            } else if(isFlush(handToRank) && !isStraight(handToRank)){
                // Check for flush
                this.tiebreaker.add(0,6);
                setHighest(handToRank);
            } else if (isStraight(handToRank) && !isFlush(handToRank)) {
                // Check for straight
                this.tiebreaker.add(0,5);
                return;
            } else if(hasThreeOfaKind(handToRank)) {
                // Check if full house
                // **** This was moved down here to avoid calling hasThreeOfakind method twice
                if(hasNextPair(handToRank, this.tiebreaker.get(0))){
                    this.tiebreaker.add(0, 7);
                    return;
                }
                else {
                    // Check for three of a kind
                    this.tiebreaker.add(0, 4);
                    setHighest(handToRank, this.tiebreaker.get(1));
                    return;
                }
            } else if (hasTwoOfaKind(handToRank)) {
                if (hasNextPair(handToRank, this.tiebreaker.get(0))) {
                    // Check for two pair
                    this.tiebreaker.add(0,3);
                    setHighest(handToRank, this.tiebreaker.get(1), this.tiebreaker.get(2));
                    return;
                } else {
                    // Check for single pair
                    this.tiebreaker.add(0,2);
                    setHighest(handToRank, this.tiebreaker.get(1));
                    return;
                }
            } else {
                // Else high card
                this.tiebreaker.add(0,1);
                setHighest(handToRank);
                return;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    // Sort by value (descending)
    private void sortValues(List<Card> handToSort){
        try {
            Collections.sort(handToSort, Card.sortValue);
            this.currentHand = handToSort;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Is flush
    private boolean isFlush(List<Card> handToCheck){
        boolean isFlush = false;

        try {
            Collections.sort(handToCheck, Card.sortSuite);



            for (int i = 0; i < handToCheck.size() - 4; ++i) {
                if (handToCheck.get(i).getSuite() == handToCheck.get(i + 1).getSuite()
                        && handToCheck.get(i).getSuite() == handToCheck.get(i + 2).getSuite()
                        && handToCheck.get(i).getSuite() == handToCheck.get(i + 3).getSuite()
                        && handToCheck.get(i).getSuite() == handToCheck.get(i + 4).getSuite()) {
                    isFlush = true;
                    return isFlush;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            // Ensures that the hand is back to being sorted by value for all the other checks
            Collections.sort(handToCheck, Card.sortValue);
        }
        return isFlush;
    }

    // Is straight
    private boolean isStraight(List<Card> handToCheck){
        boolean isStraight = false;
        try{
            for(int i = 0; i < handToCheck.size() - 4; ++i){
                if(handToCheck.get(i).getValue() == handToCheck.get(i+1).getValue()+1
                        && handToCheck.get(i).getValue() == handToCheck.get(i+2).getValue()+2
                        && handToCheck.get(i).getValue() == handToCheck.get(i+3).getValue()+3
                        && handToCheck.get(i).getValue() == handToCheck.get(i+4).getValue()+4
                ){
                    isStraight = true;
                    this.tiebreaker.add(handToCheck.get(i).getValue());
                    return isStraight;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return isStraight;
    }

    // Has four
    private boolean hasFourOfAKind(List<Card> handToCheck){
        boolean hasFour = false;
        try{
            for(int i = 0; i < handToCheck.size()-3; ++i){
                if(handToCheck.get(i).getValue() == handToCheck.get(i+1).getValue()
                        && handToCheck.get(i).getValue() == handToCheck.get(i+2).getValue()
                        && handToCheck.get(i).getValue() == handToCheck.get(i+3).getValue()
                ){
                    hasFour = true;

                    // first tie break is the value of the four cards
                    this.tiebreaker.add(handToCheck.get(i).getValue());

                    return hasFour;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return hasFour;
    }

    // Has three
    private boolean hasThreeOfaKind(List<Card> handToCheck){
        boolean hasThree = false;
        try{
            for(int i = 0; i < handToCheck.size()-2; ++i){
                if(handToCheck.get(i).getValue() == handToCheck.get(i+1).getValue()
                        && handToCheck.get(i).getValue() == handToCheck.get(i+2).getValue()
                ){
                    hasThree = true;
                    this.tiebreaker.add(handToCheck.get(i).getValue());
                    return hasThree;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return hasThree;
    }

    // Has pair
    private boolean hasTwoOfaKind(List<Card> handToCheck){
        boolean hasTwo = false;
        try{
            for(int i = 0; i < handToCheck.size()-1; ++i){
                if(handToCheck.get(i).getValue() == handToCheck.get(i+1).getValue()
                ){
                    hasTwo = true;
                    this.tiebreaker.add(handToCheck.get(i).getValue());

                    return hasTwo;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return hasTwo;
    }

    // Have next pair
    private boolean hasNextPair(List<Card> handToCheck, int exception){
        boolean hasNextPair = false;

        try{
            for(int i = 0; i < handToCheck.size()-1; ++i){
                if(handToCheck.get(i).getValue() != exception){
                    if(handToCheck.get(i).getValue() == handToCheck.get(i+1).getValue()){
                        hasNextPair = true;
                        this.tiebreaker.add(handToCheck.get(i).getValue());

                        return hasNextPair;
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return hasNextPair;
    }

    // Get next highest with no exceptions
    private void setHighest(List<Card> handToCheck){
        try{
            for(int i = 0; i < handToCheck.size(); ++i){
                this.tiebreaker.add(handToCheck.get(i).getValue());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Get next highest with one exceptions
    private void setHighest(List<Card> handToCheck, int exception1){
        try{
            for(int i = 0; i < handToCheck.size(); ++i){
                if(handToCheck.get(i).getValue() != exception1){
                    this.tiebreaker.add(handToCheck.get(i).getValue());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Get next highest with two exceptions
    private void setHighest(List<Card> handToCheck, int exception1, int exception2){
        try{
            for(int i = 0; i < handToCheck.size(); ++i){
                if(handToCheck.get(i).getValue() != exception1 && handToCheck.get(i).getValue() != exception2) {
                    this.tiebreaker.add(handToCheck.get(i).getValue());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Rank{" +
                "currentHand=" + currentHand +
                ", tiebreaker=" + tiebreaker +
                '}';
    }

    public List<Integer> getTiebreaker() {
        return tiebreaker;
    }
}

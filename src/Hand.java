public class Hand {

    // Represents a fixed-size hand of cards. Manages insertion,
    // access, and formatted display of cards.

    private Card[] handCards;
    private int index;

    public Hand(int n){
        this.handCards = new Card[n];
        this.index = 0;

    }

    public int capacity() {
        return this.handCards.length;
    }

    public int size() {
        return index;
    }

    public String toString() {
        String[] currentStateArray = new String[index];
        String currentStateString = "";
        for (int i = index - 1; i >= 0; i--) {
            currentStateArray[i] = "Card " + (i + 1) + " is " + this.handCards[i].toString() + " ";
            currentStateString = currentStateString + currentStateArray[i] + "\n";
        }

        return currentStateString;
    }

    public void addCard(Card c) {
        if (size() < capacity()) {
            handCards[index] = c;
            index ++;
        } else {
            System.err.println("The hand is either full or you are trying to add something that isn't a card to the " +
                    "hand.");
            System.exit(1);
        }

    }

    public Card getCard(int i) {
         if(i >= index) {
             System.err.println("This method takes an int:i between 0 and the number of cards in the hand (called " +
                     "index and inclusive) and returns the card from the hand this new index i (zero indexed).");
             System.exit(1);
         }
         return handCards[i];


    }



}

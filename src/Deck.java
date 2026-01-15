public class Deck {

    // Models a standard 52-card deck. Supports shuffling and dealing
    // fixed-size hands while tracking remaining cards.

    private Card[] cards;
    private int index = 0;

    public Deck() {
        this.cards = new Card[52];

        for(int suit = 0; suit < 4; suit++) {
            for (int rank = 1; rank < 14; rank++) {
                this.cards[index] = new Card(suit, rank);
                index++;
            }
        }
    }

    public String toString() {
        String[] currentStateArray = new String[index];
        String currentStateString = "";
        for (int i = index - 1; i >= 0; i--) {
            currentStateArray[i] = "Card " + (i + 1) + " is " + this.cards[i].toString() + " ";
            currentStateString = currentStateString + currentStateArray[i] + "\n";
        }

        return currentStateString;
    }

    public int size() {
        int size = index;
        return size;
    }

    public void shuffle() {
        for(int i = this.cards.length - 1; i > 0; i--) {
            int j = (int)(Math.random() * i + 1);
            Card temp = new Card(cards[i].getSuit(),cards[i].getRank());
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Hand getHand(int n) {

        Hand hand = new Hand(n);

        if( n > size()) {
            System.err.println("There aren't enough cards in the deck for a hand of that size.");
            System.exit(1);
        }
        else{
            for(int i = 0; i < n; i++) {
                hand.addCard(this.cards[index - 1]);
                index--;
            }
        }
        return hand;
    }
}

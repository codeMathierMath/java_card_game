public class Card {

    // Represents an individual playing card with a suit and rank.
    // Provides comparison and display functionality.

    private int suit;
    private int rank;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        String[] suits = {"clubs", "diamonds", "spades", "hearts"};
        String[] ranks = {"non-existing-rank", "A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        return ranks[rank] + " of " + suits[suit];
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public boolean equals(Card other) {
        // Two cards are equal only if they have the same suit and the same rank.
        return suit == other.suit && rank == other.rank;
    }

    public int compareTo(Card other) {
        // First compare the suit, and only if the suit is the same compare the rank.
        if (suit < other.suit) {
            return -1;
        } else if (suit > other.suit) {
            return 1;
        } else {
            if (rank < other.rank) {
                return -1;
            } else if (rank > other.rank) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
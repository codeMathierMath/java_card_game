public class Game {

    // Concepts demonstrated:
    // - Object-oriented composition
    // - Coordination of interacting classes
    // - Rule-based simulation
    // - Control flow and defensive input handling

    // This class coordinates a simple card game simulation. A deck is
    // shuffled, hands are dealt to multiple players, and a deterministic
    // scoring rule is applied to determine a winner. Ties are resolved
    // via randomized selection.

    // The implementation prioritizes clarity and explicit control flow
    // over abstraction or optimization.

    public static void main(String[] args) {

        //Defensive programming for correct number of command line arguments
        if(args.length != 1) {
            System.err.println("This program takes a single command line argument: the number of players entered" +
                    "as a digit.");
            System.exit(1);
        }

        if(Integer.parseInt(args[0]) < 2 || Integer.parseInt(args[0]) > 10) {
            System.err.println("This game requires at least two people, and has a maximum of 10 players. Constrained by" +
                    " 52 card deck and five card hand, and the assumed desire for a non-trivial win.");
            System.exit(1);
        }

        //initializing number of players and cards per player, and creating an array of Hand objects to store the
        // players' hands and creating a card deck.
        int numPlayers = Integer.parseInt(args[0]);
        int cardsPer = 5;
        Hand[] playerHands = new Hand[numPlayers];
        Deck playingDeck = new Deck();
        //System.out.println(playingDeck);

        //Shuffling the deck
        playingDeck.shuffle();
        //System.out.println(playingDeck);

        //Using playingDeck.getHand to deal a hand from the shuffled deck to each player
        for(int i = 0; i < numPlayers; i++) {
            playerHands[i] = playingDeck.getHand(cardsPer);
            System.out.println("Player " + (i+1) + "'s hand: \n" + playerHands[i]);
            System.out.println();
        }

        System.out.println("The remaining cards in the deck are: \n" + playingDeck);
        System.out.println();

        System.out.println("The winner of the game is Player #" + (Game.getWinner(playerHands,playingDeck) + 1) );

    }

    public static int getWinner(Hand[] playerHands, Deck playingDeck) {

        int numPlayers = playerHands.length;
        int cardsPer = 5;
        int[] ruleSum = new int[numPlayers];

        for(int i = 0; i < numPlayers; i++) {
            int rankMax = playerHands[i].getCard(0).getRank();
            int rankMin = playerHands[i].getCard(0).getRank();
            int suitMax = playerHands[i].getCard(0).getSuit();

            for (int j = 1; j < cardsPer; j++) {
                int currentCardRank = playerHands[i].getCard(j).getRank();
                int currentCardSuit = playerHands[i].getCard(j).getSuit();

                //Finds max rank of hand
                if (currentCardRank > rankMax) {
                    rankMax = currentCardRank;
                }
                //Finds min rank of hand
                if (currentCardRank < rankMin) {
                    rankMin = currentCardRank;
                }

                if (currentCardSuit > suitMax) {
                    suitMax = currentCardSuit;
                }
            }

            ruleSum[i] = (rankMax - rankMin) + suitMax;

            //System.out.println(ruleSum[i]);
            //System.out.println();
        }


        //Rules of Game:
        //The following computation is performed on each player's hand...
        //The difference of the highest and lowest rank in the hand is added to the integer representation
        // of the highest suit in the hand.
        //Case 1 (Unique Max Sum Exists): The player with the largest sum under this computation wins.
        //Case 2 (Same Sum/s): Random die roll for winner between same sums.

        int maxSumIndex = 0;
        int maxSum = ruleSum[0];
        int winnerIndex = 0;
        int[] sameMaxSumIndexesPlusNull = new int[numPlayers];
        int sameMaxSumTotal = 1;

        //Finding maxSum
        for(int i = 1; i < numPlayers; i++) {
            if(ruleSum[i] > maxSum) {
                maxSum = ruleSum[i];
                maxSumIndex = i;
            }
        }

        //Debugging sameMaxSumIndexesPlusNull...
        //System.out.println("The sameMaxSumIndexesPlusNull array is: ");
        //for(int i = 0; i < sameMaxSumIndexesPlusNull.length; i++) {
        //    System.out.println(sameMaxSumIndexesPlusNull[i] + "  ");
        //}

        //Checking for Cases and establishing winner.
        for(int i = 0; i < numPlayers; i++) {
            if((ruleSum[i] == maxSum) && (maxSumIndex != i)) {
                sameMaxSumIndexesPlusNull[i] = (i + 1);
                sameMaxSumTotal ++;
            }
            else if((ruleSum[i] == maxSum) && (maxSumIndex == i)) {
                sameMaxSumIndexesPlusNull[i] = (i + 1);
                winnerIndex = i;
            }
            else {
                continue;
            }

        }

        //Debugging sameMaxSumIndexesPlusNull...
        //System.out.println("The sameMaxSumIndexesPlusNull array is: ");
        //for(int i = 0; i < sameMaxSumIndexesPlusNull.length; i++) {
        //    System.out.println(sameMaxSumIndexesPlusNull[i] + "  ");
        //}


        int[] sameMaxSumIndexes = new int[sameMaxSumTotal];
       // System.out.println("There are " + sameMaxSumTotal + " players with the same max sum.");
        int randDistMaxSumIndexes;

        //Debugging sameMaxSumIndexesPlusNull w/ println
       // System.out.println(sameMaxSumIndexesPlusNull.length);

        int debugCounter = 0;

        if(sameMaxSumTotal > 1) {
            int counterOfNonNullEntries = 0;
            for(int i = 0; i < sameMaxSumIndexesPlusNull.length; i++) {
                if(sameMaxSumIndexesPlusNull[i] > 0) {
                    sameMaxSumIndexes[counterOfNonNullEntries] = (sameMaxSumIndexesPlusNull[i] - 1);
                    counterOfNonNullEntries ++;
                }
            }

           randDistMaxSumIndexes= (int)(Math.random()*sameMaxSumTotal);

            //System.out.println("The debug counter is " + debugCounter  + ". Indicating that there are " + debugCounter
             //       + " " + "entries trying to be forced into the array sameMaxSumIndexes, which has size " +
              //      sameMaxSumTotal);

            winnerIndex = sameMaxSumIndexes[randDistMaxSumIndexes];
        }

        return winnerIndex;

    }

}

package main;

import java.io.PrintWriter;
import java.util.Scanner;

public class BridgeHandEvaluator462 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        if ( ! in.hasNextLine()) {
            throw new UnknownError();
        }

        SuitAnalysis[] handAnalysis = new SuitAnalysis[]{
                new SuitAnalysis('S'),
                new SuitAnalysis('H'),
                new SuitAnalysis('D'),
                new SuitAnalysis('C')
        };

        for (int i = 0; i < 13; i++) {
            String card = in.next();
            for (SuitAnalysis suit : handAnalysis) {
                suit.accept(card);
            }
        }
        in.nextLine();

        boolean allSuitsStopped = true;
        int allSeedsBaseStrength = 0;
        int allSeedsFullStrength = 0;
        int maxCards = 0;
        char maxSuit = '\0';
        for (SuitAnalysis suit :
                handAnalysis) {
            allSuitsStopped = allSuitsStopped && suit.isStopped();
            allSeedsBaseStrength += suit.assessStrength(false);
            allSeedsFullStrength += suit.assessStrength(true);

            if (suit.cardCount > maxCards) {
                maxCards = suit.cardCount;
                maxSuit = suit.getSuit();
            }
        }

        if (allSuitsStopped && allSeedsBaseStrength >= 16) {
            out.println("BID NO-TRUMP");
        } else if (allSeedsFullStrength >= 14) {
            out.println("BID " + maxSuit);
        } else {
            out.println("PASS");
        }
    }

    private static final class SuitAnalysis {
        private boolean ace;

        private boolean king;

        private boolean queen;

        private boolean jack;

        private int cardCount = 0;

        private final char suit;

        public SuitAnalysis(char suit) {
            this.suit = suit;
        }

        public char getSuit() {
            return suit;
        }

        public int getCardCount() {
            return cardCount;
        }

        public void accept(final String card) {
            if (card.charAt(1) == suit) {
                this.cardCount++;
                switch (card.charAt(0)) {
                    case 'A':
                        this.ace = true;
                        break;
                    case 'K':
                        this.king = true;
                        break;
                    case 'Q':
                        this.queen = true;
                        break;
                    case 'J':
                        this.jack = true;
                        break;
                }
            }
        }

        public int assessStrength(boolean full) {
            int strength = 0;
            if (ace) {
                strength += 4;
            }
            if (king) {
                strength += 3;
                if (1 == cardCount) {
                    --strength;
                }
            }
            if (queen) {
                strength += 2;
                if (2 >= cardCount) {
                    --strength;
                }
            }
            if (jack) {
                strength += 1;
                if (3 >= cardCount) {
                    --strength;
                }
            }

            if (full) {
                switch (cardCount) {
                    case 2:
                        strength += 1;
                        break;
                    case 1:
                        strength += 2;
                        break;
                    case 0:
                        strength += 2;
                        break;
                }
            }

            return strength;
        }

        public boolean isStopped() {
            return ace
                    || (king && cardCount > 1)
                    || (queen && cardCount > 2);
        }

    }
}

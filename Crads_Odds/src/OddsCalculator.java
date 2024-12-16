// File: OddsCalculator.java
import java.util.List;

public class OddsCalculator {
    private Deck deck;

    public OddsCalculator(Deck deck) {
        this.deck = deck;
    }

    public double calculateProbability(SideBet bet, Card lastCard) {
        List<Card> remaining = deck.getRemainingCards();
        int favorable = 0;

        switch (bet) {
            case FACE_CARD:
                // Jack, Queen, King
                for (Card card : remaining) {
                    if (card.getValue().equals("jack") || card.getValue().equals("queen") || card.getValue().equals("king")) {
                        favorable++;
                    }
                }
                break;
            case ACE:
                for (Card card : remaining) {
                    if (card.getValue().equals("ace")) {
                        favorable++;
                    }
                }
                break;
            case SAME_NUMBER:
                if (lastCard == null) {
                    favorable = 0;
                } else {
                    for (Card card : remaining) {
                        if (card.getValue().equals(lastCard.getValue())) {
                            favorable++;
                        }
                    }
                }
                break;
            case SAME_SUIT:
                if (lastCard == null) {
                    favorable = 0;
                } else {
                    for (Card card : remaining) {
                        if (card.getSuit().equals(lastCard.getSuit())) {
                            favorable++;
                        }
                    }
                }
                break;
            case NEXT_HIGHER:
                if (lastCard == null) {
                    favorable = 0;
                } else {
                    for (Card card : remaining) {
                        if (card.getRank() > lastCard.getRank()) {
                            favorable++;
                        }
                    }
                }
                break;
            default:
                break;
        }

        if (bet == SideBet.SAME_NUMBER || bet == SideBet.SAME_SUIT || bet == SideBet.NEXT_HIGHER) {
            if (lastCard == null) {
                return 0.0;
            }
        }

        return remaining.size() > 0 ? (double) favorable / remaining.size() : 0.0;
    }

    public double determineMultiplier(double probability) {
        if (probability == 0) {
            return 0;
        }
        // Multiplier to achieve EV = 0.7 * p
        double multiplier = (1.0 / probability) * 0.7;
        // Cap the multiplier to prevent excessively high payouts
        if (multiplier > 30) {
            multiplier = 30;
        }
        return Math.round(multiplier * 10.0) / 10.0; // Round to one decimal place
    }
}

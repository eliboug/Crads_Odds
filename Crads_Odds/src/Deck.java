// File: Deck.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = { "hearts", "diamonds", "clubs", "spades" };
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace" };
        cards = new ArrayList<>();

        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(value, suit));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    public void addCard(Card card) {
        cards.add(card);
        shuffle();
    }

    public List<Card> getRemainingCards() {
        return new ArrayList<>(cards);
    }

    public int getRemainingCount() {
        return cards.size();
    }

    public void resetDeck() {
        initializeDeck();
    }
}

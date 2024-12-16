// File: Card.java
public class Card {
    private String value;
    private String suit;
    private int rank; // Numerical value for comparison

    public Card(String value, String suit) {
        this.value = value.toLowerCase();
        this.suit = suit.toLowerCase();
        this.rank = determineRank(value);
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    private int determineRank(String value) {
        switch (value.toLowerCase()) {
            case "ace":
                return 14;
            case "king":
                return 13;
            case "queen":
                return 12;
            case "jack":
                return 11;
            default:
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    // Handle invalid value gracefully
                    return 0;
                }
        }
    }

    // Override equals and hashCode for proper comparison and removal
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        return value.equals(other.value) && suit.equals(other.suit);
    }

    @Override
    public int hashCode() {
        return value.hashCode() * 31 + suit.hashCode();
    }

    @Override
    public String toString() {
        // Capitalize first letter for display
        String displayValue = value.substring(0, 1).toUpperCase() + value.substring(1);
        String displaySuit = suit.substring(0, 1).toUpperCase() + suit.substring(1);
        return displayValue + " of " + displaySuit;
    }
}

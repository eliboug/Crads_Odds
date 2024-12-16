// File: SideBet.java
public enum SideBet {
    FACE_CARD("Next card is a Face Card", 12, 52, 2, 5),
    ACE("Next card is an Ace", 4, 52, 1, 8),
    SAME_NUMBER("Next card is Same Number", 3, 52, 1, 10),
    SAME_SUIT("Same Suit", 12, 52, 1, 3),
    NEXT_HIGHER("Next card is higher than the last card", 0, 0, 1, 10); // Dynamic outcomes

    private String description;
    private int favorableOutcomes; // For static bets
    private int totalOutcomes;     // For static bets
    private int minPayout;
    private int maxPayout;

    SideBet(String description, int favorableOutcomes, int totalOutcomes, int minPayout, int maxPayout) {
        this.description = description;
        this.favorableOutcomes = favorableOutcomes;
        this.totalOutcomes = totalOutcomes;
        this.minPayout = minPayout;
        this.maxPayout = maxPayout;
    }

    public String getDescription() {
        return description;
    }

    public int getFavorableOutcomes() {
        return favorableOutcomes;
    }

    public int getTotalOutcomes() {
        return totalOutcomes;
    }

    public int getMinPayout() {
        return minPayout;
    }

    public int getMaxPayout() {
        return maxPayout;
    }
}

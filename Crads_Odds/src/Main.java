// File: Main.java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize deck and odds calculator
        Deck deck = new Deck();
        OddsCalculator calculator = new OddsCalculator(deck);

        // Initialize GUI
        OddsFrame oddsFrame = new OddsFrame();

        // Initialize scanner for terminal input
        Scanner scanner = new Scanner(System.in);
        Card lastCard = null;

        System.out.println("Welcome to the Craps-Style Card Casino Game Odds Calculator!");
        System.out.println("----------------------------------------------------------");
        System.out.println("Available Commands:");
        System.out.println("1. add [value] of [suit] - Add a played card");
        System.out.println("   Example: add Ace of Spades");
        System.out.println("2. view - View remaining cards in the deck");
        System.out.println("3. reset - Reset the deck to initial state");
        System.out.println("4. exit - Exit the application");
        System.out.println("----------------------------------------------------------");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("view")) {
                viewRemainingCards(deck);
            } else if (input.equalsIgnoreCase("reset")) {
                deck.resetDeck();
                lastCard = null;
                System.out.println("Deck has been reset.");
                // Update multipliers after reset
                displayMultipliers(calculator, deck, lastCard, oddsFrame);
            } else if (input.toLowerCase().startsWith("add ")) {
                // Parse the card
                String cardStr = input.substring(4).trim();
                String[] parts = cardStr.split(" of ");
                if (parts.length != 2) {
                    System.out.println("Invalid format. Please use: add [value] of [suit]");
                    continue;
                }
                String value = parts[0].toLowerCase();
                String suit = parts[1].toLowerCase();
                Card card = new Card(value, suit);
                boolean removed = deck.removeCard(card);
                if (removed) {
                    System.out.println(card + " has been added to played cards.");
                    lastCard = card; // Update last card for certain side bets
                    displayMultipliers(calculator, deck, lastCard, oddsFrame);
                } else {
                    System.out.println("Card not found in the remaining deck or already played.");
                }
            } else {
                System.out.println("Unknown command. Please try again.");
            }
        }

        scanner.close();
        System.exit(0); // Ensure all GUI threads are terminated
    }

    /**
     * Displays the remaining cards in the deck.
     *
     * @param deck The current deck.
     */
    private static void viewRemainingCards(Deck deck) {
        System.out.println("\nRemaining Cards in the Deck (" + deck.getRemainingCount() + "):");
        for (Card card : deck.getRemainingCards()) {
            System.out.println("- " + card);
        }
    }

    /**
     * Calculates multipliers, rounds them, and updates the GUI accordingly.
     *
     * @param calculator The odds calculator.
     * @param deck       The current deck.
     * @param lastCard   The last played card.
     * @param oddsFrame  The GUI frame.
     */
    private static void displayMultipliers(OddsCalculator calculator, Deck deck, Card lastCard, OddsFrame oddsFrame) {
        Map<SideBet, Double> roundedMultipliers = new HashMap<>();
        for (SideBet bet : SideBet.values()) {
            double probability = calculator.calculateProbability(bet, lastCard);
            double multiplier = calculator.determineMultiplier(probability);
            // Round the multiplier to the nearest whole number
            long roundedMultiplier = Math.round(multiplier);
            // Prepare the multiplier value: 0.0 signifies "N/A"
            double displayMultiplier = roundedMultiplier > 0 ? (double) roundedMultiplier : 0.0;
            roundedMultipliers.put(bet, displayMultiplier);
        }
        // Update the GUI with the rounded multipliers
        oddsFrame.updateAllMultipliers(roundedMultipliers);
    }
}

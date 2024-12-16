// File: OddsFrame.java
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OddsFrame extends JFrame {
    private JPanel mainPanel;
    private Map<SideBet, JLabel> multiplierLabels;

    public OddsFrame() {
        setTitle("Casino Game Odds");
        setSize(800, 600); // Increased size for better readability
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(true); // Allow window resizing

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 20, 20)); // 2 columns, variable rows with spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Increased padding

        multiplierLabels = new HashMap<>();

        for (SideBet bet : SideBet.values()) {
            JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + bet.getDescription() + "</div></html>");
            descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Increased font size
            descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel multiplierLabel = new JLabel("N/A");
            multiplierLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Increased font size
            multiplierLabel.setForeground(Color.BLUE);
            multiplierLabel.setHorizontalAlignment(SwingConstants.CENTER);

            multiplierLabels.put(bet, multiplierLabel);

            mainPanel.add(descriptionLabel);
            mainPanel.add(multiplierLabel);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
        setVisible(true);
    }

    /**
     * Updates the multiplier for a specific side bet.
     *
     * @param bet        The side bet to update.
     * @param multiplier The new multiplier value.
     */
    public void updateMultiplier(SideBet bet, double multiplier) {
        JLabel label = multiplierLabels.get(bet);
        if (label != null) {
            if (multiplier > 0) {
                label.setText("<html><div style='font-size:32px;'>" + multiplier + "x</div></html>");
            } else {
                label.setText("<html><div style='font-size:32px;'>N/A</div></html>");
            }
        }
    }

    /**
     * Updates all multipliers based on the provided values.
     *
     * @param multipliers A map of side bets to their respective multipliers.
     */
    public void updateAllMultipliers(Map<SideBet, Double> multipliers) {
        SwingUtilities.invokeLater(() -> {
            for (Map.Entry<SideBet, Double> entry : multipliers.entrySet()) {
                updateMultiplier(entry.getKey(), entry.getValue());
            }
        });
    }
}

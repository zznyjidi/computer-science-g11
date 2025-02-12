package hud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import global.Database;
import global.Settings;

@SuppressWarnings("FieldMayBeFinal")
public class ScoreDisplay extends JPanel implements ActionListener {

    // Components
    private JLabel scoreLabel;
    private JLabel timerLabel;

    // Info
    private int[] timerTime = new int[] { 0, 0, 0 };
    private int score = 0;

    public ScoreDisplay() {
        // Prepare Panel
        setOpaque(false);
        setLayout(null);
        setBounds(0, 0, Database.windowLength, Database.windowWidth);

        // Score Label
        scoreLabel = new JLabel(String.format("%03d", score));
        scoreLabel.setBounds(30, 30, 30, 10);
        scoreLabel.setForeground(Settings.TEXT_COLOR);
        this.add(scoreLabel);

        // Timer Label
        timerLabel = new JLabel("000");
        timerLabel.setBounds(30, 40, 100, 10);
        timerLabel.setForeground(Settings.TEXT_COLOR);
        this.add(timerLabel);

        Database.levelTimer = new Timer(10, this);

        // bugfix: force rerender
        revalidate();
        repaint();
    }

    // Timer & Score Control
    public void startTimer() {
        Database.levelTimer.start();
    }
    public void stopTimer() {
        Database.levelTimer.stop();
    }
    public void reset() {
        timerTime = new int[] {0, 0, 0};
        score = 0;
        scoreLabel.setText(String.format("%03d", score));
    }
    // Getters
    public int getTime() {
        return timerTime[2] + timerTime[1] * 100 + timerTime[0] * 100 * 60;
    }
    public int getScore() {
        return score;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Database.levelTimer)) {
            incrementTimer();
            timerLabel.setText(
                String.format(Settings.timerDisplayFormat, timerTime[0], timerTime[1], timerTime[2])
            );
        }
    }

    private void incrementTimer() {
        timerTime[2] += 1;
        if (timerTime[2] > 99) {
            timerTime[2] = 0;
            timerTime[1] += 1;
        }
        if (timerTime[1] > 59) {
            timerTime[1] = 0;
            timerTime[0] += 1;
        }
    }

    public void incrementScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%03d", score));
    }
}

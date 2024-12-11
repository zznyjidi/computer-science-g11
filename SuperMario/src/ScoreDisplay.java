
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("FieldMayBeFinal")
public class ScoreDisplay extends JPanel implements ActionListener {
    
    private JLabel scoreLabel;
    private JLabel timerLabel;

    private int[] timerTime = new int[] {0, 0, 0};
    private int score = 0;

    public ScoreDisplay() {
        setOpaque(false);
        setLayout(null);
        setBounds(0, 0, Database.windowLength, Database.windowWidth);

        scoreLabel = new JLabel(String.format("%03d", score));
        scoreLabel.setBounds(30, 30, 30, 10);
        scoreLabel.setForeground(Settings.TEXT_COLOR);
        this.add(scoreLabel);
        
        timerLabel = new JLabel();
        timerLabel.setBounds(30, 40, 100, 10);
        timerLabel.setForeground(Settings.TEXT_COLOR);
        this.add(timerLabel);

        Database.levelTimer = new Timer(10, this);
    }

    public void startTimer() {
        Database.levelTimer.start();
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

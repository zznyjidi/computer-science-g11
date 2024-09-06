import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
* Create game frame that shows the game
*/
public class gameFrame extends JFrame {
	JLabel asteroid = new JLabel(new ImageIcon("images/asteroid.gif"));
	JLabel saucer = new JLabel(new ImageIcon("images/saucer.gif"));
	JLabel background = new JLabel(new ImageIcon("images/space.jpg"));
	
	public gameFrame() {
		setSize(500, 500);
		setTitle("Asteroid Game");
		
		background.setLayout(null);
		add(background);
		
		asteroid.setBounds(200, 100, 50, 50);
		background.add(asteroid);
		
		setVisible(true);
	}
}

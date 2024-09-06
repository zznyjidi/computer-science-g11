import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

/**
* Create game frame that shows the game
*/
@SuppressWarnings("serial")
public class gameFrame extends JFrame implements ActionListener {
	// Game Objects
	JLabel asteroid = new JLabel(new ImageIcon("images/asteroid.gif"));
	JLabel saucer = new JLabel(new ImageIcon("images/saucer.gif"));
	JLabel background = new JLabel(new ImageIcon("images/space.jpg"));
	// Frame Timer
	Timer gameTimer = new Timer(10, this);
	// Asteroid Movement Settings
	int asteroidDeltaX = -5;
	int asteroidDeltaY = -2;
	
	public gameFrame() {
		// Window Info
		setSize(500, 500);
		setTitle("Asteroid Game");
		// Add Background
		background.setLayout(null);
		add(background);
		// Add Asteroid
		asteroid.setBounds(200, 100, 50, 50);
		background.add(asteroid);
		// Add Saucer
		saucer.setBounds(300, 200, 75, 60);
		background.add(saucer);
		// Start
		gameTimer.start();
		setVisible(true);
	}

	/**
	 * Handle Timer Events
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		asteroid.setBounds(asteroid.getX() + asteroidDeltaX, asteroid.getY() + asteroidDeltaY, 50, 50);
		
		// Asteroid - Bounce to Wall
		// Left
		if (asteroid.getX() < 0) {
			asteroidDeltaX = -asteroidDeltaX;
		}
		// Right
		if (asteroid.getX() > 450) {
			asteroidDeltaX = -asteroidDeltaX;
		}
		// Top
		if (asteroid.getY() < 0) {
			asteroidDeltaY = -asteroidDeltaY;
		}
		// Bottom
		if (asteroid.getY() > 450) {
			asteroidDeltaY = - asteroidDeltaY;
		}
	}
}

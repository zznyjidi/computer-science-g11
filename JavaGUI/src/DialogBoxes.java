import javax.swing.JOptionPane;

public class DialogBoxes {

	public static void main(String[] args) {
		String name, favoriteFood, inspirePerson;
		name = JOptionPane.showInputDialog("What is your name? ");
		favoriteFood = JOptionPane.showInputDialog("What is your favorite food? ");
		inspirePerson = JOptionPane.showInputDialog("What is the person who inspires you? ");

		JOptionPane.showMessageDialog(null, 
			"Your name is " + name + 
			". Your favorite food is " + favoriteFood + 
			". The person who inspired you is " + inspirePerson + ". "
		);
		System.exit(0);
	}

}

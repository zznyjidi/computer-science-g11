import java.awt.Window;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Calculator extends JFrame implements ActionListener {

	// Reference Variable for Fields
	JLabel num1Label = new JLabel("Number 1: ");
	JLabel num2Label = new JLabel("Number 2: ");
	JLabel answerLabel = new JLabel("Answer: 0");

	JTextArea num1TextArea = new JTextArea("0");
	JTextArea num2TextArea = new JTextArea("0");

	JButton addButton = new JButton("+");
	JButton subtractButton = new JButton("-");
	JButton multiplyButton = new JButton("*");
	JButton divideButton = new JButton("/");

	// Constructor
	public Calculator() {
		setTitle("Basic Calculator");

		// Add object with Absolute Position
		setLayout(null);

		// Input&Output TextArea and Labels
		num1Label.setBounds(30, 30, 100, 50);
		add(num1Label);
		num1TextArea.setBounds(30, 70, 200, 20);
		add(num1TextArea);

		num2Label.setBounds(250, 30, 100, 50);
		add(num2Label);
		num2TextArea.setBounds(250, 70, 200, 20);
		add(num2TextArea);

		answerLabel.setBounds(30, 100, 100, 20);
		add(answerLabel);

		// Buttons
		addButton.setBounds(30, 130, 50, 50);
		addButton.addActionListener(this);
		add(addButton);

		subtractButton.setBounds(90, 130, 50, 50);
		subtractButton.addActionListener(this);
		add(subtractButton);

		multiplyButton.setBounds(150, 130, 50, 50);
		multiplyButton.addActionListener(this);
		add(multiplyButton);

		divideButton.setBounds(210, 130, 50, 50);
		divideButton.addActionListener(this);
		add(divideButton);

		// Setup Frame
		setSize(500, 700);
		setVisible(true);
	}

	/**
	 * Button Action Handler
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		double answer;

		// Math Operations
		if (event.getSource() == addButton) {
			answer = Double.valueOf(num1TextArea.getText()) +
					 Double.valueOf(num2TextArea.getText());
		} else if (event.getSource() == subtractButton) {
			answer = Double.valueOf(num1TextArea.getText()) -
					 Double.valueOf(num2TextArea.getText());
		} else if (event.getSource() == multiplyButton) {
			answer = Double.valueOf(num1TextArea.getText()) *
					 Double.valueOf(num2TextArea.getText());
		} else if (event.getSource() == divideButton) {
			answer = Double.valueOf(num1TextArea.getText()) /
					 Double.valueOf(num2TextArea.getText());
		} else {
			answer = 0;
		}

		// Update Answer Label
		answerLabel.setText("Answer: " + String.valueOf(answer));
	}

	public static void main(String[] args) {
		new Calculator();
	}
}

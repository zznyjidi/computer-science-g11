/**
 * Contribution Calculator
 */
public class ArithmeticOperators {

	public static void main(String[] args) {
		// $6000 Monthly Income
		double monthlyIncome = 6000.0;
		// Target Contributions
		double[] contributionPercents = {0.05, 0.08, 0.1};

		// Contribution Calculation
		double contribution;
		for (double contributionPercent: contributionPercents) {
			contribution = contributionPercent * monthlyIncome;
			System.out.println(contributionPercent*100 + "% Contribution: " + contribution);
		}
	}

}

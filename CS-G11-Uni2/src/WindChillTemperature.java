import java.util.Scanner;

public class WindChillTemperature {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		double temperature, windSpeed;

		while (true) {
			System.out.print("Temperature(°F): ");
			temperature = scan.nextDouble();
			if (temperature >= -58 && temperature <= 41) {
				break;
			} else {
				System.out.println("Temperature out of range. (-58°F to 41°F)");
			}
		}

		while (true) {
			System.out.print("Wind Speed(Mph): ");
			windSpeed = scan.nextDouble();
			if (windSpeed >= 2) {
				break;
			} else {
				System.out.println("Wind Speed out of range. (>=2)");
			}
		}

		scan.close();

		double windChillTemperature = 
			35.74 
			+ (0.6215 * temperature) 
			- (35.75 * Math.pow(windSpeed, 0.16)) 
			+ (0.4275 * temperature * Math.pow(windSpeed, 0.16));
		System.out.println("The wind chill temperature is " + windChillTemperature);
	}

}

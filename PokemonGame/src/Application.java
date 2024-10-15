
public class Application {

	public static void main(String[] args) {
		Attack[] pikachuAttacks = {
			new Attack("", 0, 0, 0), 
			new Attack("", 0, 0, 0)
		};
		Pokemon pikachu = new Pokemon("Pikachu", 40, pikachuAttacks);
		Attack[] bulbasaurAttacks = {
			new Attack("Seed Bomb", 0, 0, 0)
		};
		Pokemon bulbasaur = new Pokemon("Bulbasaur", 40, bulbasaurAttacks);

		System.out.println("A Pikachu and a Bulbasaur meet on a field! ");
		System.out.println(" " + pikachu.getStatus());
		System.out.println(" " + bulbasaur.getStatus());
	}

}

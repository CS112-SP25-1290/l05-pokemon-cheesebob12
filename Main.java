import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int choice = -1;
		boolean tryAgain = true;
		Scanner keyboard = new Scanner(System.in);

		Pokemon[] caught = {
				new Pokemon("Pikachu", "Electric"),
				new Pokemon("Bulbasaur", "Grass", "Poison"),
				new Pokemon("Charmeleon", "Fire"),
				new Pokemon("Squirtle", "Water"),
				new Pokemon("Butterfree", "Bug", "Flying"),
				new Pokemon("Pidgeotto", "Normal", "Flying")
		};

		System.out.println("Preloading Pokemon Box...");
		PokemonBox myBox = new PokemonBox(caught);
		System.out.println("...Done!\n");

		System.out.println("---------------------------");
		System.out.println("| Welcome to Pokemon Box! |");
		System.out.println("---------------------------\n");
		System.out.println(myBox);

		do {
			System.out.println("\nMAIN MENU\nWhat would you like to do?");
			System.out.println("\t1) Add a New Pokemon \n\t2) List All Pokemon \n\t3) Exit Program \n");
			System.out.print("Enter choice number> ");

			try {
				choice = keyboard.nextInt();
				keyboard.nextLine(); // clear newline
				System.out.println();

				if (choice == 1) {
					boolean validInput = false;
					while (!validInput) {
						try {
							System.out.println("Enter Pokemon Info to be added:");
							System.out.print("Enter Pokemon Name> ");
							String name = keyboard.nextLine();
							System.out.print("Enter Pokemon Type #1> ");
							String type1 = keyboard.nextLine();
							System.out.print("Enter Pokemon Type #2 (none if no second type)> ");
							String type2 = keyboard.nextLine();
							type2 = (type2.equalsIgnoreCase("none")) ? null : type2;

							Pokemon p = new Pokemon(name, type1, type2);

							myBox.add(p); // can throw PokemonAlreadyExistsException
							System.out.println("\n" + name + " added!");
							validInput = true;

						} catch (IllegalArgumentException e) {
							System.out.println("Invalid Pokemon data: " + e.getMessage());
							System.out.println("Please re-enter the Pokemon information.\n");
						} catch (PokemonAlreadyExistsException e) {
							System.out.println("Error: " + e.getMessage());
							System.out.println("Please try adding a different Pokemon.\n");
						}
					}

				} else if (choice == 2) {
					System.out.println(myBox);
				} else if (choice == 3) {
					keyboard.close();
					tryAgain = false;
				} else {
					System.out.println("Invalid choice, please pick a valid option from the menu.\n");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number from the menu options.\n");
				keyboard.nextLine(); // clear invalid input
			}
		} while (tryAgain);

		System.out.println("Thank you for using the Pokemon Box program :D see you later!");
	}
}
package tareaJUnit;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Inicializando Maquina...");
		Maquina maquina = new Maquina();
		while (maquina.getStatus() == 0) {
			System.out.print(
					"Ingrese una receta de la forma: \n(Nombre, precio, u. de cafe, u. de chocolate, u. de leche, u. de azucar)\n(Ejemplo: Café con leche, 500, 2, 0, 4, 2): ");
			System.out.println(maquina.addReceta(scan.nextLine()));
		}
		while (true) {
			try {
				System.out.print(
						"Maquina en espera\n -- Menu Principal --\n1.- Agregar inventario \n2.- Verificar inventario\n3.- Comprar bebida\n4.- Cerrar Programa\nOpción: ");
				int opt = Integer.valueOf(scan.nextLine());
				if (opt == 1) {
					while (true) {
						System.out.print("Ingrese cantidad de cafe: ");
						String resp = maquina.addInventory(0, scan.nextLine());
						System.out.println(resp.substring(1));
						if (resp.charAt(0) == '1') {
							break;
						}

					}
					while (true) {
						System.out.print("Ingrese cantidad de chocolate: ");
						String resp = maquina.addInventory(1, scan.nextLine());
						System.out.println(resp.substring(1));
						if (resp.charAt(0) == '1') {
							break;
						}
					}
					while (true) {
						System.out.print("Ingrese cantidad de leche: ");
						String resp = maquina.addInventory(2, scan.nextLine());
						System.out.println(resp.substring(1));
						if (resp.charAt(0) == '1') {
							break;
						}
					}
					while (true) {
						System.out.print("Ingrese cantidad de azucar: ");
						String resp = maquina.addInventory(3, scan.nextLine());
						System.out.println(resp.substring(1));
						if (resp.charAt(0) == '1') {
							break;
						}
					}

				} else if (opt == 2) {
					System.out.println(maquina.verifyInventory());
				} else if (opt == 3) {
					System.out.println(maquina.showRecipes());
					String drink;
					while (true) {
						System.out.print("Opción: ");
						drink = scan.nextLine();
						if (maquina.isValidDrink(drink)) {
							break;
						} else {
							System.out.println("Opción ingresada no válida... pruebe nuevamente");
						}
					}
					System.out.print("Ingrese dinero: $");
					System.out.println(maquina.comprarBebida(scan.nextLine(), drink));

				} else if (opt == 4) {
					System.out.println("Cerrando programa");
					break;
				} else {
					System.out.println("Opción ingresada no válida");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Opción ingresada no válida... intente nuevamente");
			}
		}
		scan.close();
	}

}

package tareaJUnit;

import java.util.ArrayList;

public class Maquina {
	private ArrayList<Receta> recetas = new ArrayList<Receta>();
	private int status = 0; 
	private int unidadCafe;
	private int unidadChocolate;
	private int unidadLeche;
	private int unidadAzucar;
	private int[] limits = { 10, 10, 80, 40 };
	public Maquina() {
	}

	public Maquina(int unidadCafe, int unidadChocolate, int unidadLeche, int unidadAzucar) {
		this.unidadAzucar = unidadAzucar;
		this.unidadCafe = unidadCafe;
		this.unidadChocolate = unidadChocolate;
		this.unidadLeche = unidadLeche;
	}

	public int getUnidadCafe() {
		return unidadCafe;
	}

	public int getUnidadChocolate() {
		return unidadChocolate;
	}

	public int getUnidadLeche() {
		return unidadLeche;
	}

	public int getUnidadAzucar() {
		return unidadAzucar;
	}

	public int getStatus() {
		return status;
	}

	public ArrayList<Receta> getRecetas() {
		return recetas;
	}

	public String addReceta(String receta) {
		String[] allReceta = receta.trim().split("\\s*,\\s*");
		if (allReceta.length != 6) {
			return ("Receta ingresada no válida");
		}
		try {
			String nombre = allReceta[0];
			int precio = Integer.parseInt(allReceta[1]);
			int cafe = Integer.parseInt(allReceta[2]);
			int chocolate = Integer.parseInt(allReceta[3]);
			int leche = Integer.parseInt(allReceta[4]);
			int azucar = Integer.parseInt(allReceta[5]);
			Receta nuevaReceta = new Receta(nombre, precio, cafe, chocolate, leche, azucar);
			if (this.status == 1) {
				return ("Cantidad de recetas máximas permitidas");
			}
			this.recetas.add(nuevaReceta);
			this.status = this.recetas.size() == 3 ? 1 : 0;
			return ("Receta añadida");
		} catch (NumberFormatException ex) {
			return ("Receta ingresada no válida");
		}
	}

	public void setRecetas(ArrayList<Receta> recetas) {
		this.recetas = recetas;
		this.status = this.recetas.size() == 3 ? 1 : 0;
	}

	public String addInventory(int option, String strSize) {
		try {

			float size = Float.parseFloat(strSize);
			if (size % 1 != 0) {
				return ("0Por favor ingresar una cantidad en enteros");
			}
			if (size < 0) {
				return ("0Por favor ingrese una cantidad positiva");
			}
			switch (option) {
			case 0: {
				int newtot = (int) (this.unidadCafe + size);
				if (newtot <= this.limits[option]) {
					this.unidadCafe = newtot;
					return ("1Cafe añadido con exito");
				} else {
					return ("0Cantidad añadida supera al limite establecido");
				}
			}
			case 1: {
				int newtot = (int) (this.unidadChocolate + size);
				if (newtot <= this.limits[option]) {
					this.unidadChocolate = newtot;
					return ("1Chocolate añadido con exito");
				} else {
					return ("0Cantidad añadida supera al limite establecido");
				}
			}
			case 2: {
				int newtot = (int) (this.unidadLeche + size);
				if (newtot <= this.limits[option]) {
					this.unidadLeche = newtot;
					return ("1Leche añadida con exito");
				} else {
					return ("0Cantidad añadida supera al limite establecido");
				}
			}
			case 3: {
				int newtot = (int) (this.unidadAzucar + size);
				if (newtot <= this.limits[option]) {
					this.unidadAzucar = newtot;
					return ("1Azucar añadida con exito");
				} else {
					return ("0Cantidad añadida supera al limite establecido");
				}
			}
			default: {
				return ("0Opción ingresada no válida");
			}
			}
		} catch (NumberFormatException ex) {
			return ("0Valor ingregado no válido");
		}

	}

	private boolean verifyInventory(int opt) {
		Receta receta = this.recetas.get(opt);
		if (this.unidadAzucar >= receta.getUnidadAzucar() && this.unidadCafe >= receta.getUnidadCafe()
				&& this.unidadChocolate >= receta.getUnidadChocolate() && this.unidadLeche >= receta.getUnidadLeche()) {
			return true;
		} else {
			return false;
		}
	}

	public String verifyInventory() {
		return ("Azucar: " + this.unidadAzucar + "\nCafe: " + this.unidadCafe + "\nChocolate: " + this.unidadChocolate
				+ "\nLeche: " + this.unidadLeche);
	}

	public String showRecipes() {
		String resp="";
		for (int i = 1; i <= this.recetas.size(); i++) {
			resp+=(i + " " + this.recetas.get(i - 1).toString()+"\n");
		}
		return resp;
	}

	private void makeDrink(int opt) {
		Receta receta = this.recetas.get(opt);
		this.unidadAzucar -= receta.getUnidadAzucar();
		this.unidadCafe -= receta.getUnidadCafe();
		this.unidadChocolate -= receta.getUnidadChocolate();
		this.unidadLeche -= receta.getUnidadLeche();
	}

	public boolean isValidDrink(String strOption) {
		try {
			int option = Integer.parseInt(strOption);
			if (option > 0 && option <= this.recetas.size()) {
				return true;
			} else {
				return false;
			}

		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public String comprarBebida(String strMonto, String strOption) {
		try {
			int monto = Integer.parseInt(strMonto);
			int option = Integer.parseInt(strOption)-1;
			if (!this.verifyInventory(option)) {
				return ("No hay suficientes ingredientes para realizar la bebida... retornando dinero");
			}
			int vuelto = monto - this.recetas.get(option).getPrecio();
			if (vuelto < 0) {
				return ("Monto ingresado insuficiente... retornando dinero");
			}
			this.makeDrink(option);
			return ("Entregando bebida... Retornando vuelto: " + vuelto);
		} catch (NumberFormatException ex) {
			return ("Valor ingregado no válido... regresando a menú principal");
		}

	}
}

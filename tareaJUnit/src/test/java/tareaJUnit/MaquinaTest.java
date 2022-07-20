package tareaJUnit;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class MaquinaTest {
	private Maquina newMaquina;
	private Maquina fullMaquina;
	private Receta receta1;
	private Receta receta2;
	private Receta receta3;
	private ArrayList<Receta> recetas;

	@Before
	public void setUp() throws Exception {
		newMaquina = new Maquina();
		fullMaquina = new Maquina(10, 10, 80, 40);
		receta1 = new Receta("Latte", 500, 3, 1, 1, 2);
		receta2 = new Receta("Mocca", 1000, 3, 2, 5, 4);
		receta3 = new Receta("Expresso", 200, 1, 1, 1, 1);
		recetas = new ArrayList<Receta>();
		recetas.add(receta1);
		recetas.add(receta2);
		recetas.add(receta3);

	}

	@Test
	public void createNewMaquina() throws Exception {
		Assert.assertEquals(newMaquina.getRecetas().size(), 0);
		Assert.assertEquals(newMaquina.getStatus(), 0);
		Assert.assertEquals(newMaquina.getUnidadCafe(), 0);
		Assert.assertEquals(newMaquina.getUnidadChocolate(), 0);
		Assert.assertEquals(newMaquina.getUnidadLeche(), 0);
		Assert.assertEquals(newMaquina.getUnidadAzucar(), 0);
	}

	@Test
	public void createCustomMaquina() throws Exception {
		Assert.assertEquals(fullMaquina.getRecetas().size(), 0);
		Assert.assertEquals(fullMaquina.getStatus(), 0);
		Assert.assertEquals(fullMaquina.getUnidadCafe(), 10);
		Assert.assertEquals(fullMaquina.getUnidadChocolate(), 10);
		Assert.assertEquals(fullMaquina.getUnidadLeche(), 80);
		Assert.assertEquals(fullMaquina.getUnidadAzucar(), 40);
	}

	@Test
	public void addRecipes() throws Exception {
		Assert.assertEquals(newMaquina.addReceta("Latte,500,3,1,1,2"), "Receta añadida");
		Assert.assertEquals(newMaquina.getRecetas().size(), 1);
		Assert.assertEquals(newMaquina.addReceta("Latte,500,3,1,1,2"), "Receta añadida");
		Assert.assertEquals(newMaquina.getRecetas().size(), 2);
		Assert.assertEquals(newMaquina.addReceta("Latte,500,3,1,1,2"), "Receta añadida");
		Assert.assertEquals(newMaquina.getRecetas().size(), 3);
	}

	@Test
	public void addBadRecipes() throws Exception {
		Assert.assertEquals(newMaquina.addReceta("Latte 500 3 1 1 2"), "Receta ingresada no válida");
		Assert.assertEquals(newMaquina.addReceta("Latte-500-3-1-1-2"), "Receta ingresada no válida");
		Assert.assertEquals(newMaquina.addReceta("Latte,500,3,1,1"), "Receta ingresada no válida");
		Assert.assertEquals(newMaquina.addReceta("Latte,500,3,1,1,2,3"), "Receta ingresada no válida");
		Assert.assertEquals(newMaquina.addReceta("Latte,a,b,c,d,e"), "Receta ingresada no válida");
		newMaquina.setRecetas(recetas);
		Assert.assertEquals(newMaquina.addReceta("Latte,500,3,1,1,2"), "Cantidad de recetas máximas permitidas");
		;

	}

	@Test
	public void getStatus() throws Exception {
		recetas.remove(0);
		newMaquina.setRecetas(recetas);
		Assert.assertEquals(newMaquina.getStatus(), 0);
		newMaquina.addReceta("Latte,500,3,1,1,2");
		Assert.assertEquals(newMaquina.getStatus(), 1);
	}

	@Test
	public void addInventory() throws Exception {
		Assert.assertEquals(newMaquina.addInventory(0, "1"), "1Cafe añadido con exito");
		Assert.assertEquals(newMaquina.addInventory(1, "1"), "1Chocolate añadido con exito");
		Assert.assertEquals(newMaquina.addInventory(2, "1"), "1Leche añadida con exito");
		Assert.assertEquals(newMaquina.addInventory(3, "1"), "1Azucar añadida con exito");
	}

	@Test
	public void addtoFullInventory() throws Exception {
		Assert.assertEquals(fullMaquina.addInventory(0, "1"), "0Cantidad añadida supera al limite establecido");
		Assert.assertEquals(fullMaquina.addInventory(1, "1"), "0Cantidad añadida supera al limite establecido");
		Assert.assertEquals(fullMaquina.addInventory(2, "1"), "0Cantidad añadida supera al limite establecido");
		Assert.assertEquals(fullMaquina.addInventory(3, "1"), "0Cantidad añadida supera al limite establecido");
	}

	@Test
	public void addBaInventory() throws Exception {
		Assert.assertEquals(newMaquina.addInventory(0, "1.1"), "0Por favor ingresar una cantidad en enteros");
		Assert.assertEquals(newMaquina.addInventory(1, "-10"), "0Por favor ingrese una cantidad positiva");
		Assert.assertEquals(newMaquina.addInventory(2, "vsakhad"), "0Valor ingregado no válido");
		Assert.assertEquals(newMaquina.addInventory(3, "1,1"), "0Valor ingregado no válido");
		Assert.assertEquals(newMaquina.addInventory(4, "1"), "0Opción ingresada no válida");
	}

	@Test
	public void ShowRecetas() throws Exception {
		fullMaquina.setRecetas(recetas);
		String fullrecipes = "";
		for (int i = 1; i <= recetas.size(); i++) {
			fullrecipes += i + " " + recetas.get(i - 1).toString() + "\n";
		}
		Assert.assertEquals(fullMaquina.showRecipes(), fullrecipes);
	}

	@Test
	public void showInventory() throws Exception {
		String voidInventory = ("Azucar: " + 0 + "\nCafe: " + 0 + "\nChocolate: " + 0 + "\nLeche: " + 0);
		;
		String fullInventory = ("Azucar: " + 40 + "\nCafe: " + 10 + "\nChocolate: " + 10 + "\nLeche: " + 80);
		;
		Assert.assertEquals(newMaquina.verifyInventory(), voidInventory);
		Assert.assertEquals(fullMaquina.verifyInventory(), fullInventory);
	}

	@Test
	public void selectValidDrink() throws Exception {
		fullMaquina.setRecetas(recetas);
		Assert.assertTrue(fullMaquina.isValidDrink("1"));
		Assert.assertTrue(fullMaquina.isValidDrink("2"));
		Assert.assertTrue(fullMaquina.isValidDrink("3"));
	}

	@Test
	public void selectNotValidDrink() throws Exception {
		fullMaquina.setRecetas(recetas);
		Assert.assertFalse(fullMaquina.isValidDrink("0"));
		Assert.assertFalse(fullMaquina.isValidDrink("-1"));
		Assert.assertFalse(fullMaquina.isValidDrink("10"));
		Assert.assertFalse(fullMaquina.isValidDrink("a"));
	}

	@Test
	public void goodBuy() throws Exception {
		fullMaquina.setRecetas(recetas);
		String fullInventory = ("Azucar: " + 40 + "\nCafe: " + 10 + "\nChocolate: " + 10 + "\nLeche: " + 80);
		;
		Assert.assertEquals(fullMaquina.verifyInventory(), fullInventory);
		Assert.assertEquals(fullMaquina.comprarBebida("10000", "1"), "Entregando bebida... Retornando vuelto: 9500");
		fullInventory = ("Azucar: " + 38 + "\nCafe: " + 7 + "\nChocolate: " + 9 + "\nLeche: " + 79);
		Assert.assertEquals(fullMaquina.verifyInventory(), fullInventory);
		Assert.assertEquals(fullMaquina.comprarBebida("5000", "2"), "Entregando bebida... Retornando vuelto: 4000");
		fullInventory = ("Azucar: " + 34 + "\nCafe: " + 4 + "\nChocolate: " + 7 + "\nLeche: " + 74);
		Assert.assertEquals(fullMaquina.verifyInventory(), fullInventory);
		Assert.assertEquals(fullMaquina.comprarBebida("200", "3"), "Entregando bebida... Retornando vuelto: 0");
		fullInventory = ("Azucar: " + 33 + "\nCafe: " + 3 + "\nChocolate: " + 6 + "\nLeche: " + 73);
		Assert.assertEquals(fullMaquina.verifyInventory(), fullInventory);

	}

	@Test
	public void badBuys() throws Exception {
		newMaquina.setRecetas(recetas);
		Assert.assertEquals(newMaquina.comprarBebida("10000", "1"),
				"No hay suficientes ingredientes para realizar la bebida... retornando dinero");
		fullMaquina.setRecetas(recetas);
		Assert.assertEquals(fullMaquina.comprarBebida("100", "3"), "Monto ingresado insuficiente... retornando dinero");
		Assert.assertEquals(fullMaquina.comprarBebida("asd", "d"),
				"Valor ingregado no válido... regresando a menú principal");

	}

}

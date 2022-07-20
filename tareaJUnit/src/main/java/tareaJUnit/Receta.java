package tareaJUnit;

public class Receta {
	@Override
	public String toString() {
		return  nombre + " $" + precio;
	}


	private String nombre;
	private int precio;
	private int unidadCafe;
	private int unidadChocolate;
	private int unidadLeche;
	private int unidadAzucar;

	public Receta(String nombre, int precio, int unidadCafe, int unidadChocolate, int unidadLeche,
			int unidadAzucar) {
		this.nombre = nombre;
		this.precio = precio;
		this.unidadCafe = unidadCafe;
		this.unidadChocolate = unidadChocolate;
		this.unidadLeche = unidadLeche;
		this.unidadAzucar = unidadAzucar;
	}

	            

	public int getPrecio() {
		return precio;
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

}

package model;

public class Package<T> {
	private static final long serialVersionUID = 1L;

	int option;
	T object;
	double balance;
	Boolean result;


	public Boolean getResultado() {
		return result;
	}

	public void setResultado(Boolean resultado) {
		this.result = resultado;
	}

	public double getCantidad() {
		return balance;
	}

	public void setCantidad(double cantidad) {
		this.balance = cantidad;
	}

	public int getOpcion() {
		return option;
	}

	public void setOpcion(int opcion) {
		this.option = opcion;
	}

	public T getObjeto() {
		return object;
	}

	public void setObjeto(T objeto) {
		this.object = objeto;
	}

	@Override
	public String toString() {
		return "Paquete [opcion=" + option + ", objeto=" + object + ", cantidad=" + balance + "]";
	}

}

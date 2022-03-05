package model;

public class Package<T> {
	private static final long serialVersionUID = 1L;

	int option;
	T object;
	double balance;
	Boolean result;

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Paquete [opcion=" + option + ", objeto=" + object + ", cantidad=" + balance + "]";
	}

}

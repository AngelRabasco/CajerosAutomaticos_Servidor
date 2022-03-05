package model;

import java.io.Serializable;

public class Package<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer option;
	T object;
	Double balance;
	Boolean result;

	public Integer getOption() {
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

	public Double getBalance() {
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

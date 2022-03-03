package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "number")
	private String number;
	@Column(name = "balance")
	private Double balance;
	@ManyToOne()
	@JoinColumn(name = "clientId")
	private Client client;

	public Account(String number, Double balance, Client client) {
		this.id = -1L;
		this.number = number;
		this.balance = balance;
		this.client = client;
	}

	public Account() {
		this("", Double.MIN_VALUE, new Client());
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", balance=" + balance + ", client=" + client + "]";
	}

}

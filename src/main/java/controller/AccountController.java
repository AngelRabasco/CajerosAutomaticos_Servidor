package controller;

import java.util.List;

import model.Account;
import model.dao.AccountDAO;

public class AccountController {
	public synchronized void createAccount(Account account) {
		new AccountDAO().save(account);
	}

	public synchronized void editAccount(Account account) {
		new AccountDAO().edit(account);
	}

	public synchronized void deleteAccount(Account account) {
		new AccountDAO().delete(account);
	}

	public synchronized Account getAccountById(Long id) {
		return new AccountDAO().getById(id);
	}

	public synchronized List<Account> getAllAccounts() {
		return new AccountDAO().getAll();
	}

	public synchronized void subtractFromAccount(Account account, Double cantidad) {
		try {
			new AccountDAO().subtractBalance(account, cantidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void addToAccount(Account account, Double cantidad) {
		new AccountDAO().addBalance(account, cantidad);
	}
}

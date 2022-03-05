package controller;

import java.util.List;

import model.Account;
import model.dao.AccountDAO;



public class AccountController {
	public void CreateAccount(Account account) {
		AccountDAO aDAO= new AccountDAO();
		aDAO.save(account);
	
	}
	public void DeleteAccount(Account account) {
		AccountDAO aDAO= new AccountDAO();
		aDAO.delete(account);
	
	}
	public Account ShowAccount(Long id) {

			return new AccountDAO().getById(id);
	
	}
	public List<Account> mostrarAccounts() {
	
			return new AccountDAO().getAll();
		
	}

	public void extraerDinero(Account account, Double cantidad) {
		try {
			new AccountDAO().subtractBalance(account, cantidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ingresarDinero(Account account, Double cantidad) {
		new AccountDAO().addBalance(account, cantidad);
	}
}

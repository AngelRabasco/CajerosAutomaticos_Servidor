package controller;

import java.util.List;

import model.Account;
import model.dao.AccountDAO;



public class AccountController {
	public void CreateAccount(Account account) {
		AccountDAO aDAO= new AccountDAO();
		aDAO.save(account);
	
	}
	public void BorrarAccount(Account account) {
		AccountDAO aDAO= new AccountDAO();
		aDAO.delete(account);
	
	}
	public Account mostrarAccount(Long id) {

			return new AccountDAO().getById(id);
	
	}
	public List<Account> mostrarAccounts() {
	
			return new AccountDAO().getAll();
		
	}

	public void extraerDinero(Account account, double cantidad) {
			new AccountDAO().extraerDinero(account, cantidad);
	
	}
	
	public void ingresarDinero(Account account, double cantidad) {
		try {
			new AccountDAO().ingresarDinero(account, cantidad);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

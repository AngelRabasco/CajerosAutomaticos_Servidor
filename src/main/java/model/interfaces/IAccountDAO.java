package model.interfaces;

import java.util.List;

import model.Account;
import model.Client;

public interface IAccountDAO extends IDAO<Account, Long> {
	
	/**
	 * Gets an account based on it's number
	 * @param number The number of the account
	 * @return The account with the matching number
	 */
	public Account getAccountByNumber(String number);

	/**
	 * Gets all accounts from the specified client
	 * @param client The client who owns the accounts
	 * @return All the accounts of the given client
	 */
	public List<Account> getAccountsByClient(Client client);
}

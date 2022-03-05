package model.interfaces;

import java.util.List;

import model.Admin;
import model.Client;

public interface IClientDAO extends IDAO<Client, Long> {

	/**
	 * Gets a client by its user name
	 * @param username The client's user name
	 * @return The client with the matching user name
	 */
	public Client getByUsername(String username);

	/**
	 * Get a client who's user name and password match the submitted values
	 * @param username The client's user name
	 * @param password The client's password
	 * @return The client with matching values
	 */
	public Client getByUsernamePassword(String username, String password);

	/**
	 * Get all clients who's administrator matches the submitted one
	 * @param admin The client's administrator
	 * @return The client's with matching administrator
	 */
	public List<Client> getByAdmin(Admin admin);
}

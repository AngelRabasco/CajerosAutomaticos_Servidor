package model.interfaces;

import model.Admin;

public interface IAdminDAO extends IDAO<Admin, Long> {

	/**
	 * Gets an administrator by its user name
	 * @param username The administrator's user name
	 * @return The administrator with the matching user name
	 */
	public Admin getByUsername(String username);

	/**
	 * Get a administrator who's user name and password match the submitted values
	 * @param username The administrator's user name
	 * @param password The administrator's password
	 * @return The administrator with matching values
	 */
	public Admin getByUsernamePassword(String username, String password);
}

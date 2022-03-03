package model.interfaces;

import java.util.List;

public interface IDAO<T, K> {

	/**
	 * Saves object into database
	 * @param val The object to be saved
	 */
	public void save(T val);

	/**
	 * Modifies object in the database
	 * @param val The object to be modified
	 */
	public void edit(T val);

	/**
	 * Removes object from the database
	 * @param val The object to be removed
	 */
	public void delete(T val);

	/**
	 * Gets an object from the database based on their id
	 * @param id The id of the object
	 * @return The object that matches the id
	 */
	public T getById(K id);

	/**
	 * Gets all objects from the database
	 * @return
	 */
	public List<T> getAll();
}

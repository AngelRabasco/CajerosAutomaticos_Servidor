package controller;

import java.util.List;

import model.Admin;
import model.Client;
import model.dao.ClientDAO;

public class ClientController {
	public synchronized void createClient(Client client) {
		new ClientDAO().save(client);
	}

	public synchronized void editClient(Client c) {
		new ClientDAO().edit(c);
	}

	public synchronized void deleteClient(Client client) {
		new ClientDAO().delete(client);
	}

	public synchronized Client getClientById(Long id) {
		return new ClientDAO().getById(id);
	}

	public synchronized List<Client> getAccountsByAdmin(Admin admin) {
		return new ClientDAO().getByAdmin(admin);
	}

	public synchronized List<Client> getAllClients() {
		return new ClientDAO().getAll();
	}

	public synchronized Client logInUser(Client client) {
		return new ClientDAO().getByUsernamePassword(client.getUsername(), client.getPassword());
	}

}

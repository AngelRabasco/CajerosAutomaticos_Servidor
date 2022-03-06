package controller;

import java.util.List;

import model.Admin;
import model.Client;
import model.dao.ClientDAO;

public class ClientController {
	public synchronized void createClient(Client client) {
		new ClientDAO().save(client);
	}

	public synchronized List<Client> getAllClients() {
		return new ClientDAO().getAll();
	}

	public synchronized Client getClientById(Long id) {
		return new ClientDAO().getById(id);
	}

	public synchronized void deleteClient(Client client) {
		new ClientDAO().delete(client);
	}

	public synchronized Client logUser(Client client) {
		return new ClientDAO().getByUsernamePassword(client.getUsername(), client.getPassword());
	}

	public synchronized List<Client> ShowAccountByAdmin(Admin admin) {

		return new ClientDAO().getByAdmin(admin);

	}

	public synchronized void editClient(Client c) {
		new ClientDAO().edit(c);

	}
}

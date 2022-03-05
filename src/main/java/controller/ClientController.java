package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.dao.ClientDAO;

public class ClientController {
	public synchronized boolean createClient(Client client) {
		new ClientDAO().save(client);
		return false;
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

	public synchronized List<Client> mostarClientsPorAdmin(Long id) {
		try {
			return new ClientDAO().get
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<Client>();
		}
	}
}

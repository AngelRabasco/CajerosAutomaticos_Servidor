package controller;

import java.net.ServerSocket;
import java.util.List;

import model.Account;
import model.Admin;
import model.Client;
import model.GesConect;
import model.Package;

public class ServerController implements Runnable {
	ServerSocket server;
	GesConect con = new GesConect(9999);

	public void serverController(Object action) {
		Package<?> paquete = (Package<?>) action;

		switch (paquete.getOption()) {

		case 1: // Creates a client
			@SuppressWarnings("unchecked")
			Package<Client> clientCreationPackage = (Package<Client>) paquete;
			new ClientController().createClient(clientCreationPackage.getObject());
			break;

		case 2: // Modifies a client
			@SuppressWarnings("unchecked")
			Package<Client> clientEditionPackage = (Package<Client>) paquete;
			new ClientController().editClient(clientEditionPackage.getObject());
			break;

		case 3: // Removes a client
			@SuppressWarnings("unchecked")
			Package<Client> clienRemovalPackage = (Package<Client>) paquete;
			new ClientController().deleteClient(clienRemovalPackage.getObject());
			break;

		case 4: // Gets client by their id
			@SuppressWarnings("unchecked")
			Package<Client> clientGetByIdPackage = (Package<Client>) paquete;
			try {
				Client recievedClient = new ClientController().getClientById(clientGetByIdPackage.getObject().getId());
				if (recievedClient != null) {
					clientGetByIdPackage.setResult(true);
					clientGetByIdPackage.setObject(recievedClient);
				} else {
					clientGetByIdPackage.setResult(false);
				}
				this.con.sendObjectToServer(clientGetByIdPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		// Opci�n 5 Inicia la sesi�n
		case 5:
			Package<Client> clientpackage5 = (Package<Client>) paquete;
			new ClientController().logUser(clientpackage5.getObject());
			break;

		case 6: // Gets clients of a given administrator
			@SuppressWarnings("unchecked")
			Package<Admin> clientAdminPackage = (Package<Admin>) paquete;
			Package<List<Client>> clientGetByAdminPackage = new Package<List<Client>>();
			try {
				List<Client> recievedClients = new ClientController().ShowAccountByAdmin(clientAdminPackage.getObject());
				if (!recievedClients.isEmpty()) {
					clientGetByAdminPackage.setResult(true);
					clientGetByAdminPackage.setObject(recievedClients);
				} else {
					clientGetByAdminPackage.setResult(false);
				}
				this.con.sendObjectToServer(clientGetByAdminPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 7: // Gets all clients
			@SuppressWarnings("unchecked")
			Package<List<Client>> clientGetAllPackage = (Package<List<Client>>) paquete;
			try {
				List<Client> recievedClients = new ClientController().getAllClients();
				if (!recievedClients.isEmpty()) {
					clientGetAllPackage.setResult(true);
					clientGetAllPackage.setObject(recievedClients);
				} else {
					clientGetAllPackage.setResult(false);
				}
				this.con.sendObjectToServer(clientGetAllPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 8: // Get administrator by id
			@SuppressWarnings("unchecked")
			Package<Admin> adminGetByIdPackage = (Package<Admin>) paquete;
			try {
				Admin recievedAdmin = new AdminController().getAdminById(adminGetByIdPackage.getObject().getId());
				if (recievedAdmin != null) {
					adminGetByIdPackage.setResult(true);
					adminGetByIdPackage.setObject(recievedAdmin);
				} else {
					adminGetByIdPackage.setResult(false);
				}
				this.con.sendObjectToServer(adminGetByIdPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 9: // Administrator login
			@SuppressWarnings("unchecked")
			Package<Admin> adminLogInPackage = (Package<Admin>) paquete;
			try {
				Admin logedAdmin = new AdminController().logAdministrador(adminLogInPackage.getObject());
				if (logedAdmin != null) {
					adminLogInPackage.setResult(true);
					adminLogInPackage.setObject(logedAdmin);
				} else {
					adminLogInPackage.setResult(false);
				}
				this.con.sendObjectToServer(adminLogInPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 10: // Creates an account
			@SuppressWarnings("unchecked")
			Package<Account> accountCreationPackage = (Package<Account>) paquete;
			new AccountController().CreateAccount(accountCreationPackage.getObject());
			break;

		case 11: // Edits an account
			@SuppressWarnings("unchecked")
			Package<Account> accountEditionPackage = (Package<Account>) paquete;
			new AccountController().EditAccount(accountEditionPackage.getObject());
			break;

		case 12: // Removes an account
			@SuppressWarnings("unchecked")
			Package<Account> accountDeletionPackage = (Package<Account>) paquete;
			new AccountController().DeleteAccount(accountDeletionPackage.getObject());
			break;

		case 13: // Gets account by its id
			@SuppressWarnings("unchecked")
			Package<Account> accountGetByIdPackage = (Package<Account>) paquete;
			try {
				Account recievedAccount = new AccountController().ShowAccount(accountGetByIdPackage.getObject().getId());
				if (recievedAccount != null) {
					accountGetByIdPackage.setResult(true);
					accountGetByIdPackage.setObject(recievedAccount);
				} else {
					accountGetByIdPackage.setResult(false);
				}
				this.con.sendObjectToServer(accountGetByIdPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 14: // Subtracts from an account
			@SuppressWarnings("unchecked")
			Package<Account> accountSubtractionPackage = (Package<Account>) paquete;
			new AccountController().extraerDinero(accountSubtractionPackage.getObject(), accountSubtractionPackage.getBalance());
			break;

		case 15: // Adds to an account
			@SuppressWarnings("unchecked")
			Package<Account> accountAdditionPackage = (Package<Account>) paquete;
			new AccountController().ingresarDinero(accountAdditionPackage.getObject(), accountAdditionPackage.getBalance());
			break;
		default:
			break;
		}

	}

	public void run() {
		try {
			System.out.println("INICIANDO SERVIDOR");
			while (true) {
				System.out.println("Un nuevo cliente esta conectado al servidor, la informacion es: \n ");
				Object action = con.getObjectFromClient();
				serverController(action);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Account> removeUserFromAccounts(List<Account> Accounts) {
		for (Account Account : Accounts) {
			Account.setClient(null);
		}
		return Accounts;
	}

}

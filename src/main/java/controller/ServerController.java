package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.List;

import model.Account;
import model.Admin;
import model.Client;
import model.GesConect;
import model.dao.AdminDAO;
import model.dao.ClientDAO;
import model.Package;


public class ServerController implements Runnable {
	ServerSocket servidor;
	GesConect con = new GesConect(9999);
	
	public void serverController(Object action) {

		Package<?> paquete = (Package<?>) action;
		
		switch (paquete.getOption()) {
		//Opción1 1 Crea un nuevo cliente
		case 1:
			Package<Client> clientpackage1 = (Package<Client>) paquete;
			new ClientController().createClient(clientpackage1.getObject());
			break;
		//Opción 2 Edita un cliente
		case 2:
			Package<Client> clientpackage2 = (Package<Client>) paquete;
			new ClientController().editClient(clientpackage2.getObject());
			
			break;
		//Opción 3 Borra un cliente
		case 3:
			Package<Client> clientpackage3 = (Package<Client>) paquete;
			new ClientController().deleteClient(clientpackage3.getObject());
			break;
		//Opción 4 Traer un cliente por id
		case 4:
			Package<Client> clientpackage4 = (Package<Client>) paquete;
			new ClientController().getClientById(clientpackage4.getObject().getId());
			break;
		//Opción 5 Inicia la sesión
		case 5:
			@SuppressWarnings("unchecked")
			Package<Client> clientLogInPackage = (Package<Client>) paquete;
			try {
				Client logedClient = new ClientController().logUser(clientLogInPackage.getObject());
				if (logedClient != null) {
					clientLogInPackage.setResult(true);
					clientLogInPackage.setObject(logedClient);
					this.con.sendObjectToServer(clientLogInPackage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		//Opción 6 Muestra los clientes de un administrador
		case 6:
			Package<Client> clientpackage6 = (Package<Client>) paquete;
			new ClientController().ShowAccountByAdmin(clientpackage6.getObject().getAdmin());
			break;
		//Opción 7 Devuelve todos los clientes
		case 7:
			Package<Client> clientpackage7 = (Package<Client>) paquete;
			new ClientController().getAllClients();
			break;
		//Opción 8 Devuelve un administrador por su id
		case 8:
			Package<Admin> adminpackage1 = (Package<Admin>) paquete;
			new AdminController().getAdminById(adminpackage1.getObject().getId());
			break;
		// Opción 9 Inicia la sesión como administrador
		case 9:
			@SuppressWarnings("unchecked")
			Package<Admin> adminLogInPackage = (Package<Admin>) paquete;
			try {
				Admin logedAdmin = new AdminController().logAdministrador(adminLogInPackage.getObject());
				if (logedAdmin != null) {
					adminLogInPackage.setResult(true);
					adminLogInPackage.setObject(logedAdmin);
					this.con.sendObjectToServer(adminLogInPackage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			new AdminController().logAdministrador(adminLogInPackage.getObject());
			break;
		//Opción 10 crea una nueva cuenta
		case 10:
			Package<Account> accountpackage1 = (Package<Account>) paquete;
			new AccountController().CreateAccount(accountpackage1.getObject());;
			break;
		//Opción 11 edita una cuenta 
		case 11:
			Package<Account> accountpackage2 = (Package<Account>) paquete;
			new AccountController().DeleteAccount(accountpackage2.getObject());;
			break;
		//Opción 12 Muestra una cuenta
		case 12:
			Package<Account> accountpackage3 = (Package<Account>) paquete;
			new AccountController().ShowAccount(accountpackage3.getObject().getId());
			break;
		//Opción 13 Muestra una cuenta
		case 13:
			Package<Account> accountpackage4 = (Package<Account>) paquete;
			new AccountController().extraerDinero(accountpackage4.getObject(), accountpackage4.getBalance());
			break;
		//Opción 14 Muestra una cuenta
		case 14:
			Package<Account> accountpackage5 = (Package<Account>) paquete;
			new AccountController().ingresarDinero(accountpackage5.getObject(), accountpackage5.getBalance());
			break;
		default:
			break;
		}
		
		/*switch (paquete.getOption()) {

		case 1:
			Package<Client> paqueteClient = (Package<Client>) paquete;
			new ClientController().createClient(paqueteClient.getObject());
			break;

		case 2:
			/*Package<Object> respuestPackageAccount5 = new Package<Object>();
			ObjectOutputStream salidaObjetoAccount5;
			try {
				salidaObjetoAccount5 = new ObjectOutputStream(cliente.getOutputStream());
				new AccountController().mostrarAccounts();
				respuestPackageAccount5.setResultado(true);
				salidaObjetoAccount5.writeObject(respuestPackageAccount5);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case 3: //UPDATEAR Client
			Package<Client> paqueteAccount = (Package<Client>) paquete;
			try {
				paqueteAccount.setObject(new ClientDAO().edit(paqueteAccount.getObject()));
				paqueteAccount.setResult(true);
				this.sm.sendObjectToServer(paqueteAccount);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case 4:
			Package<Account> paqueteAccount1 = (Package<Account>) paquete;
			new AccountController().ingresarDinero(paqueteAccount1.getObject(), paqueteAccount1.getBalance());
			break;

		case 5:
			Package<Client> paqueteClient2 = (Package<Client>) paquete;
			System.out.println(paqueteClient2.getObject().toString());
				try {
					if(new ClientDAO().edit(paqueteClient2.getObject().getAdmin()!= null)){
						paqueteClient2.setResult(true);
						this.sm.sendObjectToServer(paqueteClient2);
					}else{
						paqueteClient2.setResult(false);
						this.sm.sendObjectToServer(paqueteClient2);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				};
			break;

		case 6:
			Package<Account> paqueteAccount2 = (Package<Account>) paquete;
			System.out.println("hola");
			System.out.println(paqueteAccount2.getObject().getClient().toString());
			if(new AccountController().CreateAccount(paqueteAccount2.getObject())){
				paqueteAccount2.setResult(true);
				this.sm.sendObjectToServer(paqueteAccount2);
			}else{
				paqueteAccount2.setResult(false);

				this.sm.sendObjectToServer(paqueteAccount2);
			}
			break;

		case 7: //Mostramos Clients del Admin
		Package<Admin> paqueteAdmin = (Package<Admin>) paquete;
		Package<List<Client>> paqueteClients = new Package<List<Client>>();
			paqueteClients.setObjeto(new ClientController().ShowAccountByAdmin(paqueteAdmin.getObject().getId()));
			this.sm.sendObjectToServer(paqueteClients);
			break;

		case 8:
			Package<Account> paqueteAccount3 = (Package<Account>) paquete;
			new AccountController().ShowAccount(paqueteAccount3.getObject().getId());
			break;

		case 9:
			Package<Long> paqueteAccount4 = (Package<Long>) paquete;
			System.out.println(paqueteAccount4.getObject());
			if(new AccountController().DeleteAccount(paqueteAccount4.getObject())){
				paqueteAccount4.setResult(true);
				this.sm.sendObjectToServer(paqueteAccount4);
			}else{
				paqueteAccount4.setResult(false);
				this.sm.sendObjectToServer(paqueteAccount4);
			}
			break;

		case 10:
			Package<List<Account>> paqueteAccount5L = new Package<List<Account>>();
			paqueteAccount5L.setObject(new AccountController().mostrarAccounts());
			this.sm.sendObjectToServer(paqueteAccount5L);

			break;

		case 11: // LOGIN Client, DEVUELVE TRUE SI ESTA EN LA BD Y FALSE SI NO.
			Package<Client> paqueteClient11 = (Package<Client>) paquete;
			try {
				Boolean bool = new ClientController().logUser(paqueteClient11.getObject());
				if (bool) {
					paqueteClient11.setResult(true);

					Client u=new ClientDAO().getClientByNamePassword(paqueteClient11.getObject());
				
					paqueteClient11.setObject(u);
					this.sm.sendObjectToServer(paqueteClient11);
				} else {
					paqueteClient11.setResult(false);
					this.sm.sendObjectToServer(paqueteClient11);
				}
			} catch (Exception e) {
			}
			break;

		case 12:
			Package<Admin> paqueteAdmin4 = (Package<Admin>) paquete;
			try {
				if (new AdminController().logAdministrador(paqueteAdmin4.getObject());
					Admin a = new AdminDAO().getByUsernamePassword(paqueteAdmin4.getObject());
					paqueteAdmin4.setResult(true);
					paqueteAdmin4.setObject(a);
					this.sm.sendObjectToServer(paqueteAdmin4);
				} else {
					paqueteAdmin4.setResult(false);
					this.sm.sendObjectToServer(paqueteAdmin4);
				}
			} catch (Exception e) {
			}
			break;
		default:
			break; 
		}*/
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

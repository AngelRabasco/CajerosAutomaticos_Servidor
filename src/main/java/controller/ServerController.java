package controller;

import java.net.ServerSocket;
import java.util.List;

import model.Account;
import model.Admin;
import model.GesConect;
import model.dao.AdminDAO;
import model.Package;


public class ServerController implements Runnable {
	ServerSocket servidor;
	GesConect sm = new GesConect(1234);
	Package a;
	public void serverController(Object action) {

		Package<?> paquete = (Package<?>) action;
		switch (paquete.getOpcion()) {

		case 1:
			Package<Client> paqueteClient = (Package<Client>) paquete;
			new ClientController().createClient(paqueteClient.getObjeto());
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
			}*/
			break;

		case 3: //UPDATEAR Client
			Package<Client> paqueteAccount = (Package<Client>) paquete;
			try {
				paqueteAccount.setObjeto(new ClientDAO().updateClient(paqueteAccount.getObjeto()));
				paqueteAccount.setResultado(true);
				this.sm.sendObjectToServer(paqueteAccount);
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case 4:
			Package<Account> paqueteAccount1 = (Package<Account>) paquete;
			new AccountController().ingresarDinero(paqueteAccount1.getObjeto(), paqueteAccount1.getCantidad());
			break;

		case 5:
			Package<Client> paqueteClient2 = (Package<Client>) paquete;
			System.out.println(paqueteClient2.getObjeto().toString());
				try {
					if(new ClientDAO().updateClient(paqueteClient2.getObjeto())!= null){
						paqueteClient2.setResultado(true);
						this.sm.sendObjectToServer(paqueteClient2);
					}else{
						paqueteClient2.setResultado(false);
						this.sm.sendObjectToServer(paqueteClient2);
					}
				} catch (DAOException e1) {
					e1.printStackTrace();
				};
			break;

		case 6:
			Package<Account> paqueteAccount2 = (Package<Account>) paquete;
			System.out.println("hola");
			System.out.println(paqueteAccount2.getObjeto().getClient().toString());
			if(new AccountController().CreateAccount(paqueteAccount2.getObjeto())){
				paqueteAccount2.setResultado(true);
				this.sm.sendObjectToServer(paqueteAccount2);
			}else{
				paqueteAccount2.setResultado(false);

				this.sm.sendObjectToServer(paqueteAccount2);
			}
			break;

		case 7: //Mostramos Clients del Admin
		Package<Admin> paqueteAdmin = (Package<Admin>) paquete;
		Package<List<Client>> paqueteClients = new Package<List<Client>>();
			paqueteClients.setObjeto(new ClientController().mostarClientsPorAdmin(paqueteAdmin.getObjeto().getId()));
			this.sm.sendObjectToServer(paqueteClients);
			break;

		case 8:
			Package<Account> paqueteAccount3 = (Package<Account>) paquete;
			new AccountController().mostrarAccount(paqueteAccount3.getObjeto().getId());
			break;

		case 9:
			Package<Long> paqueteAccount4 = (Package<Long>) paquete;
			System.out.println(paqueteAccount4.getObjeto());
			if(new AccountController().BorrarAccount(paqueteAccount4.getObjeto())){
				paqueteAccount4.setResultado(true);
				this.sm.sendObjectToServer(paqueteAccount4);
			}else{
				paqueteAccount4.setResultado(false);
				this.sm.sendObjectToServer(paqueteAccount4);
			}
			break;

		case 10:
			Package<List<Account>> paqueteAccount5L = new Package<List<Account>>();
			paqueteAccount5L.setObjeto(new AccountController().mostrarAccounts());
			this.sm.sendObjectToServer(paqueteAccount5L);

			break;

		case 11: // LOGIN Client, DEVUELVE TRUE SI ESTA EN LA BD Y FALSE SI NO.
			Package<Client> paqueteClient11 = (Package<Client>) paquete;
			try {
				Boolean bool = new ClientController().logUser(paqueteClient11.getObjeto());
				if (bool) {
					paqueteClient11.setResultado(true);

					Client u=new ClientDAO().getClientByNamePassword(paqueteClient11.getObjeto());
				
					paqueteClient11.setObjeto(u);
					this.sm.sendObjectToServer(paqueteClient11);
				} else {
					paqueteClient11.setResultado(false);
					this.sm.sendObjectToServer(paqueteClient11);
				}
			} catch (Exception e) {
			}
			break;

		case 12:
			Package<Admin> paqueteAdmin4 = (Package<Admin>) paquete;
			try {
				if (new AdminController().logAdministrador(paqueteAdmin4.getObjeto());
					Admin a = new AdminDAO().getAdminByNamePassword(paqueteAdmin4.getObjeto());
					paqueteAdmin4.setResultado(true);
					paqueteAdmin4.setObjeto(a);
					this.sm.sendObjectToServer(paqueteAdmin4);
				} else {
					paqueteAdmin4.setResultado(false);
					this.sm.sendObjectToServer(paqueteAdmin4);
				}
			} catch (Exception e) {
			}
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
				Object action = sm.getObjectFromClient();
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
